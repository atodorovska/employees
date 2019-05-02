package mk.ukim.finki.employees.repository.jpa;

import mk.ukim.finki.employees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
