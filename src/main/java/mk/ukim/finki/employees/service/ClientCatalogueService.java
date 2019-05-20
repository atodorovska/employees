package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Client;

import mk.ukim.finki.employees.model.exceptions.EmailAssociatedWithUserException;
import mk.ukim.finki.employees.model.exceptions.PasswordNotValidatedException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;


import java.util.List;
import java.util.Optional;


public interface ClientCatalogueService {

    // creates new Client from this data and adds timestamp, token, code
    Optional<Client> catalogueNewClient(String username, String email, String password)
            throws UsernameAlreadyExistsException, EmailAssociatedWithUserException, PasswordNotValidatedException;

    Boolean validateUsername(String username);

    Boolean validateEmail(String email);

    Boolean validatePassword(String password);

    String generateActivationCode();

    String generateActivationToken();

    void removeAllPostExpiration();

    Optional<List<Client>> getAll();

}
