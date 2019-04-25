import { Component, OnInit } from '@angular/core';
import { User, AuthService, Tweet } from '../shared';
import { TweetApiService } from '../shared/services/tweet-api.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user = new User();
  recentTweets: Tweet[];

  constructor(private tweetApiService: TweetApiService, private auth: AuthService) { }

  ngOnInit() {
    this.user = this.auth.getUser();
    this.getRecentTweets();
  }

  getRecentTweets() {
    this.tweetApiService.getRecentTweets()
      .subscribe((data: Tweet[]) => this.recentTweets = data);
  }
}
