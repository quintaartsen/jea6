import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { RestApiService, AuthService, User } from '../../shared';
import { retry, catchError } from 'rxjs/operators';
import { JwtToken } from '../models/jwtToken.model';


@Injectable({
  providedIn: 'root'
})
export class UserApiService {

    constructor(private restApi: RestApiService, private auth: AuthService, private http: HttpClient) { }

    getUsers() {
        return this.http.get<User[]>(this.restApi.apiUrl + '/users')
          .pipe(
            retry(1),
            catchError(this.restApi.handleError)
          );
      }

      searchUsers(username: string) {
        return this.http.get<User[]>(this.restApi.apiUrl + '/users', {
          params: new HttpParams().set('username', username)
        })
          .pipe(
            retry(1),
            catchError(this.restApi.handleError)
          );
      }

      register(user: User) {
        user.role = 'ROLE_USER';
        return this.http.post(this.restApi.apiUrl + '/users', user, this.restApi.httpOptions)
          .pipe(
            retry(1),
            catchError(this.restApi.handleError)
          );
      }
}
