import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { JwtToken } from '../models/jwtToken.model';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  apiUrl = 'http://localhost:9090';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    observe: 'response' as 'response'
  };

  constructor(private http: HttpClient) { }

  public getJwtToken(token: JwtToken) {
    const loginUrl = this.apiUrl + '/token';
    return this.http.post(loginUrl, token, this.httpOptions);
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
