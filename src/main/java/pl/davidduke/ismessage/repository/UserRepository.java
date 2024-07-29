package pl.davidduke.ismessage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.davidduke.ismessage.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
