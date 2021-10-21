package teoresiGroup.web.Repository;

import java.util.Optional;

import teoresiGroup.web.model.User;

public interface UserRepo {
Optional<User> findByUsername(String username);
}
