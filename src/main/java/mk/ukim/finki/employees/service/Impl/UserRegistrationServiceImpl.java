package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Client;
import mk.ukim.finki.employees.model.Gender;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.model.exceptions.InsufficientUserDataEnteredException;
import mk.ukim.finki.employees.model.exceptions.InvalidActivationCodeException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.employees.repository.jpa.ClientRepository;
import mk.ukim.finki.employees.repository.jpa.UserRepository;
import mk.ukim.finki.employees.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public User completeOAuthRegistration(String username, String idOAuth) throws UsernameAlreadyExistsException {
        return null;
    }

    @Override
    public Boolean validateOAuthRegistration(String username) {
        return null;
    }

    @Override
    public Boolean validateUsername(String username) {
        if(this.userRepository.findByUsername(username) == null) return false;
        return true;
    }

    @Override
    public User completeClientRegistration(String username, String email, String password) throws InvalidActivationCodeException {
        return null;
    }

    @Override
    public Boolean validateClientActivation(String username, String activationToken, String activationCode) {
        if(validateUsername(username)) return false;
        Client clientToRegister = this.clientRepository.findByUsername(username);
        if(!clientToRegister.getActivationCode().equals(activationCode)) return false;
        if(!clientToRegister.getActivationToken().equals(activationToken)) return false;

        this.userRepository.save(new User(clientToRegister.getUsername(), clientToRegister.getEmail(), clientToRegister.getPassword()));
        return true;
    }

    @Override
    public String encodeUserPassword(String password) {

        Random random = new SecureRandom();
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        StringBuilder returnValue = new StringBuilder(password.length());

        for (int i = 0; i < password.length(); i++) {
            returnValue.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return returnValue.toString();
    }

    // TO DO: see context !!!
    @Override
    public User completeUserInformation(String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender) throws InsufficientUserDataEnteredException {
        return null;
    }
}
