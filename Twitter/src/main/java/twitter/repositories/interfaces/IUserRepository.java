package twitter.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import twitter.models.User;

import java.util.UUID;

public interface IUserRepository extends CrudRepository<User, UUID> {
}
