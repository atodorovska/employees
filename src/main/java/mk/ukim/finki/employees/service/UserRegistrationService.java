package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Client;
import mk.ukim.finki.employees.model.Gender;
import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;

import java.time.LocalDate;
import java.util.Optional;


public interface UserRegistrationService {

    // a user is registered with username, email, password
    // or with idOAuth

    // then they are redirected to a page where they must enter additional info, so that all that is kept

    // but if you chose to register with email, you sign in like that

    // else you sing in with oauth

    Optional<User> completeOAuthRegistration(String idOAuth);

    Boolean validateUsername(String username);

    Boolean validateOAuth(String idOAuth);

    Optional<User> completeClientRegistration(String activationToken, String activationCode);

    Boolean validateClientActivation(String username, String activationCode);

    String encodeUserPassword(String password);

    Client validateClientToken(String token);

    Optional<User> detailsOAuth(String idOAuth, String username, String name, String surname, String ssn,
                                LocalDate dateOfBirth, Gender gender, Integer salary, Role role, Long departmentId);

    Optional<User> detailsAuthentication(String username, String name, String surname, String ssn,
                                         LocalDate dateOfBirth, Gender gender, Integer salary, Role role, Long departmentId);
}
