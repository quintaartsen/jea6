package twitter.services.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.models.Tweet;
import twitter.models.User;
import twitter.repositories.interfaces.ITweetRepository;
import twitter.services.interfaces.ITweetService;

import java.util.List;
import java.util.UUID;

/**
 * The type Tweet service.
 */
@Service
public class TweetService implements ITweetService {

    private final ITweetRepository repository;

    /**
     * Instantiates a new Tweet service.
     *
     * @param repository the repository
     */
    @Autowired
    public TweetService(ITweetRepository repository) {
        this.repository = repository;
    }

    public List<Tweet> getTweets() {
        return (List<Tweet>) repository.findAll();
    }

    public Tweet getTweetById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Tweet createTweet(Tweet tweet) {
        repository.save(tweet);
        return tweet;
    }


    public void deleteTweetById(UUID id) {
        repository.deleteById(id);
    }

    /**
     * Add like.
     *
     * @param tweet the tweet
     * @param user  the user
     */
    public void addLike(Tweet tweet, User user) {
        tweet.addLike(user);
        repository.save(tweet);
    }


    /**
     * Remove like.
     *
     * @param tweet the tweet
     * @param user  the user
     */
    public void removeLike(Tweet tweet, User user) {
        tweet.removeLike(user);
        repository.save(tweet);
    }
}


