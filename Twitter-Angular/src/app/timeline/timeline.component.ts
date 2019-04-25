import { Component, OnInit } from '@angular/core';
import { RestApiService, Tweet, AuthService } from '../shared';
import { HttpClient } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { TweetApiService } from '../shared/services/tweet-api.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {

  tweets: Tweet[];
  message = new Tweet();

  constructor(private tweetApiService: TweetApiService) { }

  ngOnInit() {
    this.getTweets();
  }

  getTweets() {
      this.tweetApiService.getTweets()
      .subscribe((data: Tweet[]) => this.tweets = data);
  }
}
