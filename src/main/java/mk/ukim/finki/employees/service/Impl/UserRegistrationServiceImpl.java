package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Gender;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.model.exceptions.InsufficientUserDataEnteredException;
import mk.ukim.finki.employees.model.exceptions.InvalidActivationCodeException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.employees.service.UserRegistrationService;

import java.time.LocalDate;

public class UserRegistrationServiceImpl implements UserRegistrationService {


    @Override
    public User completeOAuthRegistration(String username, String idOAuth) throws UsernameAlreadyExistsException {
        return null;
    }

    @Override
    public Boolean validateOAuthRegistration(String username) {
        return null;
    }

    @Override
    public User completeClientRegistration(String username, String email, String password) throws InvalidActivationCodeException {
        return null;
    }

    @Override
    public Boolean validateClientActivation(String activationToken, String activationCode) {
        return null;
    }

    @Override
    public String encodeUserPassword(String password) {
        return null;
    }

    @Override
    public User completeUserInformation(String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender) throws InsufficientUserDataEnteredException {
        return null;
    }
}
