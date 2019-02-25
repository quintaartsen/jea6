package twitter.services.interfaces;

import twitter.models.Tweet;

import java.util.List;
import java.util.UUID;

public interface ITweetService {
    List<Tweet> getTweets();
    Tweet getTweetById(UUID id);
    Tweet createTweet(Tweet tweet);
    void deleteTweetById(UUID id);
}
