import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { RestApiService, AuthService, Tweet } from '../../shared';
import { retry, catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class TweetApiService {

    constructor(private restApi: RestApiService, private auth: AuthService, private http: HttpClient) { }

    createTweet(message: Tweet) {
        return this.http.post(this.restApi.apiUrl + '/users/' + this.auth.getUserId() + '/tweets', message, this.restApi.httpOptions)
          .pipe(
            retry(1),
            catchError(this.restApi.handleError)
          );
      }

    getTweets() {
        return this.http.get<Tweet[]>(this.restApi.apiUrl + '/tweets')
          .pipe(
            retry(1),
            catchError(this.restApi.handleError)
          );
      }

    getRecentTweets() {
        return this.http.get<Tweet[]>(this.restApi.apiUrl + '/users/' + this.auth.getUserId() + '/tweets')
            .pipe(
            retry(1),
            catchError(this.restApi.handleError)
            );
    }
}
