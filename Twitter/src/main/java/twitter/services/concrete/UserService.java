package twitter.services.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter.models.User;
import twitter.repositories.interfaces.IUserRepository;
import twitter.services.interfaces.IUserService;

import java.util.List;
import java.util.UUID;

/**
 * The type User service.
 */
@Service
public class UserService implements IUserService {

    private final IUserRepository repository;

    /**
     * Instantiates a new User service.
     *
     * @param repository the repository
     */
    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return (List<User>) repository.findAll();
    }

    public User getUserById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        repository.save(user);
        return user;
    }

    public void deleteUserById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void updateUsername(String username, User user) {
        if (!username.isEmpty()) {
            user.setUserName(username);
            repository.save(user);
        }
    }

    /**
     * Add following user.
     *
     * @param followingId the following id
     * @param id          the id
     * @return the user
     */
    public User addFollowing(UUID followingId, UUID id) {
        if(followingId != id) {
            User user = repository.findById(id).orElse(null);
            User followingUser = repository.findById(followingId).orElse(null);
            if(user != null && followingUser != null) {

                user.addFollowing(followingUser);
                followingUser.addFollower(user);

                repository.save(user);
                repository.save(followingUser);
                return user;
            }
        }
        return null;
    }

    /**
     * Remove following user.
     *
     * @param followingId the following id
     * @param id          the id
     * @return the user
     */
    public User removeFollowing(UUID followingId, UUID id) {
        User user = repository.findById(id).orElse(null);
        User followingUser = repository.findById(followingId).orElse(null);
        if(user != null && followingUser != null) {
            user.removeFollowing(followingUser);
            followingUser.removeFollower(user);
            repository.save(user);
            repository.save(followingUser);
            return user;
        }
        return null;
    }
}


