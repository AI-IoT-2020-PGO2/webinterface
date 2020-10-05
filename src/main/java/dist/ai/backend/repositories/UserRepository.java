package dist.ai.backend.repositories;

import dist.ai.backend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findAll();

    @Query("select user from User user where user.total_votes = (select max(user.total_votes) from User user)")
    User findHighestVoter();
}
