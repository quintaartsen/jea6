package twitter.modelTest;

import org.junit.Assert;
import org.junit.Test;
import twitter.models.Role;
import twitter.models.Tweet;
import twitter.models.User;

import static org.junit.Assert.assertEquals;

public class ModelTest {

    @Test
    public void userTest() {
        User user = new User(Role.User, "user@mail.nl", "username", "password");
        Tweet tweet = new Tweet("Tweet message", user);

        assertEquals(user, tweet.getOwner());
    }


    @Test
    public void ConstructorTest() {
        User user = new User(Role.User, "testUser@mail.com", "testUser", "password");
        Tweet tweet = new Tweet("Message #test #test2", user);

        assertEquals(2, tweet.getTrends().size());
        Assert.assertEquals(0, tweet.getMentions().size());

        Tweet tweet2 = new Tweet("Message @test @test2", user);

        Assert.assertEquals(2, tweet2.getMentions().size());
        assertEquals(0, tweet2.getTrends().size());

        Tweet tweetEmpty = new Tweet("Message", user);

        assertEquals(0, tweetEmpty.getTrends().size());
        Assert.assertEquals(0, tweetEmpty.getMentions().size());
    }
}
