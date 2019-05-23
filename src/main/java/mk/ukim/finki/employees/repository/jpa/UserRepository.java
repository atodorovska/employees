package mk.ukim.finki.employees.repository.jpa;

import mk.ukim.finki.employees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByIdOAuth(String idOAuth);

    Optional<List<User>> findAllByDepartmentId(Long department);

    Optional<List<User>> findAllByUsernameContainingIgnoreCase(String s);

    Optional<List<User>> findAllByNameContainingIgnoreCase(String s);

    Optional<List<User>> findAllBySurnameContainingIgnoreCase(String s);

    Optional<List<User>> findAllBySalaryLessThanEqual(Integer salary);

}
