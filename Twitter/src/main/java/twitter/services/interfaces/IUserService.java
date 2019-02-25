package twitter.services.interfaces;

import twitter.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> getUsers();
    User getUserById(UUID id);
    User createUser(User user);
    void deleteUserById(UUID id);

    void updateUsername(String newUsername, User user);
}
