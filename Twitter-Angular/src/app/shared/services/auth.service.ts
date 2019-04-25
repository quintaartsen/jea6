import { Injectable } from '@angular/core';
import { RestApiService } from './rest-api.service';
import { Router } from '@angular/router';
import { HttpResponse, HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../models/user.model';
import { retry, catchError } from 'rxjs/operators';
import { JwtToken } from '../models/jwtToken.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly TOKEN_STORAGE_KEY = 'token';
  static readonly USER_ID = 'userId';
  private user = new User();
  redirectToUrl = '/home';

  constructor(private router: Router, private restApi: RestApiService, private http: HttpClient) { }

  public login(token: JwtToken): void {
    this.restApi.getJwtToken(token)
      .subscribe((res: HttpResponse<any>) => {
        this.saveToken(res.body.accessToken);
        this.router.navigate([this.redirectToUrl]);
        this.getUserByUsername(token.userName);
      });
  }

  private getUserByUsername(username: string): void {
    this.http.get<User[]>(this.restApi.apiUrl + '/users', {
      params: new HttpParams().set('username', username)
    })
      .pipe(
        retry(1),
        catchError(this.restApi.handleError)
      )
      .subscribe(data => {
        localStorage.setItem(AuthService.USER_ID, data[0].id);
        this.saveMe();
      });
  }

  private saveMe(): void {
    this.http.get<User>(this.restApi.apiUrl + '/users/' + this.getUserId())
      .pipe(
        retry(1),
        catchError(this.restApi.handleError)
      )
      .subscribe(data => this.user = data);
  }

  public getUser(): User {
    return this.user;
  }

  public getUserId(): string {
    return localStorage.getItem(AuthService.USER_ID);
  }

  private saveToken(token: string) {
    localStorage.setItem(AuthService.TOKEN_STORAGE_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(AuthService.TOKEN_STORAGE_KEY);
  }

  public logout(): void {
    localStorage.removeItem(AuthService.TOKEN_STORAGE_KEY);
    localStorage.removeItem(AuthService.USER_ID);
    this.router.navigate(['/login']);
  }

  public isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
