package mk.ukim.finki.employees.repository.jpa;

import mk.ukim.finki.employees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByIdOAuth(String idOAuth);

    List<User> findAllByDepartmentId(Long department);

    List<User> findAllByUsernameContainingIgnoreCase(String s);

    List<User> findAllByNameContainingIgnoreCase(String s);

    List<User> findAllBySurnameContainingIgnoreCase(String s);

    List<User> findAllBySalaryLessThanEqual(Integer salary);

}
