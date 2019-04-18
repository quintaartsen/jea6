package twitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import twitter.models.Tweet;
import twitter.models.User;
import twitter.repositories.interfaces.ITweetRepository;
import twitter.repositories.interfaces.IUserRepository;

import java.util.List;

@Scope(value = "session")
@Component(value = "adminController")
public class AdminController {

    private final IUserRepository userRepository;
    private final ITweetRepository tweetRepository;

    @Autowired
    public AdminController(IUserRepository userRepository, ITweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public List<Tweet> getAllTweets() {
        return (List<Tweet>) tweetRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    public void deleteTweet(Tweet tweet) {
        tweetRepository.deleteById(tweet.getId());
    }
}
