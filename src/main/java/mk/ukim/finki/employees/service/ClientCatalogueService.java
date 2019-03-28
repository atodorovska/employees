package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Client;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface ClientCatalogueService {

    // creates new Client from this data and adds timestamp, token, code
    Client catalogueNewClient(String username, String email, String password);

    Boolean validateClientData(String username, String email);

    String generateActivationCode();

    String generateActivationToken();

    SimpleMailMessage sendEmailMessage(String email, String activationCode, String activationToken);

    List<Client> removeAllPostExpiration();
}
