package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Gender;
import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;

import java.time.LocalDate;

public interface UserRegistrationService {

    User completeOAuthRegistration(String username, String idOAuth);

    Boolean validateOAuthRegistration(String username);

    User completeClientRegistration(String username, String email, String password);

    Boolean validateClientActivation(String activationToken, String activationCode);

    String encodeUserPassword(String password);

    User completeUserInformation(String name, String surname, String ssn, LocalDate dateOfBirth, Integer salary,
                                 Gender gender, Role role, String departmentName);
}
