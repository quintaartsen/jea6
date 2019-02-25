package twitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter.models.Tweet;
import twitter.services.interfaces.ITweetService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "tweets")
public class TweetController {

    private final ITweetService service;

    @Autowired
    public TweetController(ITweetService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    List<Tweet> getTweets() {
        return service.getTweets();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public @ResponseBody
    Tweet getTweetById(@PathVariable UUID id) {
        return service.getTweetById(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Tweet CreateTweet(@RequestBody Tweet tweet) {
        return service.createTweet(tweet);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweetById(@PathVariable UUID id) {
        service.deleteTweetById(id);
    }
}