package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Client;

import mk.ukim.finki.employees.model.exceptions.EmailAssociatedWithUserException;
import mk.ukim.finki.employees.model.exceptions.PasswordNotValidatedException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface ClientCatalogueService {

    // creates new Client from this data and adds timestamp, token, code
    Client catalogueNewClient(String username, String email, String password)
            throws UsernameAlreadyExistsException, EmailAssociatedWithUserException, PasswordNotValidatedException;

    Boolean validateClientData(String username, String email);

    Boolean validatePassword(String password);

    String generateActivationCode();

    String generateActivationToken();

    // exceptions from SimpleMailMessage
    SimpleMailMessage sendEmailMessage(String email, String activationCode, String activationToken);

    List<Client> removeAllPostExpiration();
}
