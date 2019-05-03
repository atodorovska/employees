package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Client;

import mk.ukim.finki.employees.model.exceptions.EmailAssociatedWithUserException;
import mk.ukim.finki.employees.model.exceptions.PasswordNotValidatedException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;


import java.util.List;
import java.util.Optional;


public interface ClientCatalogueService {

    // creates new Client from this data and adds timestamp, token, code
    Client catalogueNewClient(String username, String email, String password)
            throws UsernameAlreadyExistsException, EmailAssociatedWithUserException, PasswordNotValidatedException;

    Boolean validateUsername(String username);

    Boolean validateEmail(String email);

    Boolean validatePassword(String password);

    String generateActivationCode();

    String generateActivationToken();

    // exceptions from SimpleMailMessage
    // SimpleMailMessage sendEmailMessage(String email, String activationCode, String activationToken);

    void removeAllPostExpiration();

    Optional<Client> getClientWithToken(String token);


//    Optional<List<Client>> getAll();

}
