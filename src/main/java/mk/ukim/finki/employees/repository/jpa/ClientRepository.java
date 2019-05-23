package mk.ukim.finki.employees.repository.jpa;

import mk.ukim.finki.employees.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByActivationToken(String token);

    Optional<Client> findByUsername(String username);

    Optional<Client> findByEmail(String email);
}
