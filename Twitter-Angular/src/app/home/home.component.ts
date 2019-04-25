import { Component, OnInit } from '@angular/core';
import { RestApiService, Tweet, AuthService } from '../shared';
import { HttpClient } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { TweetApiService } from '../shared/services/tweet-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  tweets: Tweet[];
  message = new Tweet();

  constructor(private tweetApiService: TweetApiService) { }

  ngOnInit() {
  }

  createTweet() {
    this.tweetApiService.createTweet(this.message)
    .subscribe();
  }
}
