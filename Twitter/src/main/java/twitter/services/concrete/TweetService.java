package twitter.services.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.models.Tweet;
import twitter.repositories.interfaces.ITweetRepository;
import twitter.services.interfaces.ITweetService;

import java.util.List;
import java.util.UUID;

@Service
public class TweetService implements ITweetService {

    private final ITweetRepository repository;

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
        return tweet;    }

    public void deleteTweetById(UUID id) {
        repository.deleteById(id);
    }
}


