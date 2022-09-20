package tanulmanyitervezo.tervezo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

