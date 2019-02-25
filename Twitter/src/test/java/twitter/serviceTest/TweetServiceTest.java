package twitter.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import twitter.models.Role;
import twitter.models.Tweet;
import twitter.models.User;
import twitter.repositories.interfaces.ITweetRepository;
import twitter.repositories.interfaces.IUserRepository;
import twitter.services.concrete.TweetService;

import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TweetServiceTest {


    @InjectMocks
    private TweetService tweetService;

    @Mock
    private ITweetRepository tweetRepo;

    @Mock
    private IUserRepository userRepo;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void ServiceTest() {
        assertNotNull(tweetService);
    }

    @Test
    public void createTest() throws Exception {
        UUID uuid1 = new UUID(1,1);

        User user = new User(Role.User, "email@mail.com", "username", "password");
        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user));
        user.setId(uuid1);
        tweetService.createTweet(new Tweet("message", user));
        verify(userRepo, atLeastOnce()).save(user);
    }

    @Test
    public void createTest2() throws Exception {
        UUID uuid1 = new UUID(1,1);

        User user = new User(Role.User, "email@mail.com", "username", "password");
        when(userRepo.findById(uuid1)).thenReturn(null);
        user.setId(uuid1);
        tweetService.createTweet(new Tweet("message", user));
        verify(userRepo, never()).save(user);
    }

    @Test
    public void deleteTest() throws Exception {
        UUID uuid1 = new UUID(1,1);

        User user = new User(Role.User, "email@mail.com", "username", "password");
        Tweet tweet = new Tweet("message", user);
        user.setId(uuid1);
        tweet.setId(uuid1);

        when(tweetRepo.findById(tweet.getId())).thenReturn(java.util.Optional.of(tweet));
        tweetService.deleteTweetById(uuid1);
        verify(tweetRepo, atLeastOnce()).delete(tweet);
    }


    @Test(expected= Exception.class)
    public void deleteTest2() throws Exception {
        UUID uuid1 = new UUID(1,1);

        User user = new User(Role.User, "email@mail.com", "username", "password");
        Tweet tweet = new Tweet("message", user);
        user.setId(uuid1);
        tweet.setId(uuid1);

        when(tweetRepo.findById(tweet.getId())).thenReturn(null);
        tweetService.deleteTweetById(uuid1);
        verify(tweetRepo, never()).delete(tweet);
    }
}
