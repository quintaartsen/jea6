package twitter.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import twitter.models.Role;
import twitter.models.User;
import twitter.repositories.interfaces.IUserRepository;
import twitter.services.concrete.UserService;

import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepo;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void ServiceTest() {
        assertNotNull(userService);
    }

    @Test
    public void createTest1() throws Exception {
        UUID uuid1 = new UUID(1,1);

        User user = new User(Role.User, "email@mail.com", "username", "password");
        user.setId(uuid1);

        when(userRepo.findById(user.getId())).thenReturn(null);

        userService.createUser(user);
        verify(userRepo, atLeastOnce());
    }

    @Test
    public void createTest2() throws Exception {
        UUID uuid1 = new UUID(1,1);

        User user = new User(Role.User, "email@mail.com", "username", "password");
        user.setId(uuid1);

        when(userRepo.findById(user.getId())).thenReturn(null);


        userService.createUser(user);
        verify(userRepo, never());
    }

    @Test
    public void createTest3() throws Exception {
        UUID uuid1 = new UUID(1,1);
        User user = new User(Role.User, "email@mail.com", "username", "password");
        user.setId(uuid1);

        when(userRepo.findById(user.getId())).thenReturn(null);

        userService.createUser(user);
        verify(userRepo, never());
    }

    @Test
    public void updateUsernameTest1() throws Exception {
        UUID uuid1 = new UUID(1,1);
        User user = new User(Role.User, "user113@mail.com", "user113", "password");
        user.setId(uuid1);
        String newUsername = "username2";

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user));

        userService.updateUsername(newUsername, user);
        verify(userRepo, atLeastOnce()).save(user);
    }

    @Test
    public void updateUsernameTest2() throws Exception {
        UUID uuid1 = new UUID(1,1);
        User user = new User(Role.User, "user113@mail.com", "user113", "password");
        user.setId(uuid1);
        String newUsername = "";

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user));

        userService.updateUsername(newUsername, user);
        verify(userRepo, never()).save(user);
    }

    @Test
    public void updateUsernameTest3() throws Exception {
        UUID uuid1 = new UUID(1,1);
        User user = new User(Role.User, "user113@mail.com", "user113", "password");
        user.setId(uuid1);
        String newUsername = "";

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user));

        userService.updateUsername(newUsername, user);
        verify(userRepo, never()).save(user);
    }

    @Test
    public void addFollowingTest1() throws Exception {
        UUID uuid1 = new UUID(1,1);
        UUID uuid2 = new UUID(2,2);
        // Case 1 - Add another user to your following list
        User user1 = new User(Role.User, "user500@mail.com", "user500", "password");
        User user2 = new User(Role.User, "user510@mail.com", "user510", "password");
        user1.setId(uuid1);
        user2.setId(uuid2);

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user1));
        when(userRepo.findById(uuid2)).thenReturn(java.util.Optional.of(user2));


        userService.addFollowing(user1.getId(), user2.getId());

        verify(userRepo, atLeastOnce()).save(user1);
        verify(userRepo, atLeastOnce()).save(user2);
    }

    @Test
    public void addFollowingTest2() throws Exception {
        UUID uuid1 = new UUID(1,1);
        User user1 = new User(Role.User, "user500@mail.com", "user500", "password");
        user1.setId(uuid1);

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user1));

        userService.addFollowing(user1.getId(), user1.getId());
        verify(userRepo, never()).save(user1);
    }

    @Test
    public void addFollowingTest3() throws Exception {
        UUID uuid1 = new UUID(1,1);
        UUID uuid3 = new UUID(3,3);

        User user1 = new User(Role.User, "user500@mail.com", "user500", "password");
        user1.setId(uuid1);

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user1));
        when(userRepo.findById(uuid3)).thenReturn(null);

        userService.addFollowing(user1.getId(), new UUID(3,3));
        verify(userRepo, never()).save(user1);
    }

    @Test
    public void removeFollowingTest1() throws Exception {
        UUID uuid1 = new UUID(1,1);
        UUID uuid2 = new UUID(2,2);

        User user1 = new User(Role.User, "user500@mail.com", "user500", "password");
        User user2 = new User(Role.User, "user510@mail.com", "user510", "password");
        user1.setId(uuid1);
        user2.setId(uuid2);

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user1));
        when(userRepo.findById(uuid2)).thenReturn(java.util.Optional.of(user2));

        userService.removeFollowing(user1.getId(), user2.getId());
        verify(userRepo, atLeastOnce()).save(user1);
    }

    @Test
    public void removeFollowingTest2() throws Exception {
        UUID uuid1 = new UUID(1,1);
        User user1 = new User(Role.User, "user500@mail.com", "user500", "password");
        user1.setId(uuid1);

        when(userRepo.findById(new UUID(1,1))).thenReturn(java.util.Optional.of(user1));

        userService.removeFollowing(user1.getId(), user1.getId());
        verify(userRepo, never()).save(user1);
    }

    @Test
    public void removeFollowingTest3() throws Exception {
        UUID uuid1 = new UUID(1,1);
        UUID uuid3 = new UUID(3,3);
        User user1 = new User(Role.User, "user500@mail.com", "user500", "password");
        user1.setId(new UUID(1,1));

        when(userRepo.findById(uuid1)).thenReturn(java.util.Optional.of(user1));
        when(userRepo.findById(uuid3)).thenReturn(null);

        userService.removeFollowing(user1.getId(), uuid3);
        verify(userRepo, never()).save(user1);
    }
}
