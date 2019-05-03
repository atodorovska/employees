package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Gender;
import mk.ukim.finki.employees.model.exceptions.InsufficientUserDataEnteredException;
import mk.ukim.finki.employees.model.exceptions.InvalidActivationCodeException;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;

import java.time.LocalDate;


public interface UserRegistrationService {

    User completeOAuthRegistration(String username, String idOAuth) throws UsernameAlreadyExistsException;

    Boolean validateOAuthRegistration(String username);

    Boolean validateUsername(String username);

    User completeClientRegistration(String username, String email, String password, String activationToken, String activationCode );

    Boolean validateClientActivation(String username, String activationToken, String activationCode);

    String encodeUserPassword(String password);

    User completeUserInformation(String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender)
            throws InsufficientUserDataEnteredException;
}
