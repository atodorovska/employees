package mk.ukim.finki.employees.repository.jpa;

import mk.ukim.finki.employees.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByActivationToken(String token);

    Client findByUsername(String username);

    Client findByEmail(String email);
}
