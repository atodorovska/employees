package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Client;
import mk.ukim.finki.employees.model.Gender;
import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.repository.jpa.ClientRepository;
import mk.ukim.finki.employees.repository.jpa.UserRepository;
import mk.ukim.finki.employees.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        UserRegistrationServiceImpl service = new UserRegistrationServiceImpl();
        service.passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(service.encodeUserPassword("pajace123"));
    }

    @Override
    public Optional<User> completeOAuthRegistration(String idOAuth) {
        if(validateOAuth(idOAuth)) return Optional.of(this.userRepository.save(new User(idOAuth)));
        else return Optional.empty();
    }

    @Override
    public Boolean validateUsername(String username) {
        if(this.userRepository.findByUsername(username) == null) return true;
        return false;
    }

    @Override
    public Boolean validateOAuth(String idOAuth) {
        if(this.userRepository.findByIdOAuth(idOAuth) == null) return true;
        return false;
    }

    @Override
    public Optional<User> completeClientRegistration(String activationToken, String activationCode ) {

        Client client = this.clientRepository.findByActivationToken(activationToken).get();

        if(validateClientActivation(client.getUsername(), activationCode))
            return Optional.of(this.userRepository.save(new User(client.getUsername(), client.getEmail(),
                    encodeUserPassword(client.getPassword()))));
        return Optional.empty();
    }

    @Override
    public Boolean validateClientActivation(String username, String activationCode) {

        if(!validateUsername(username)) return false;
        Client clientToRegister = this.clientRepository.findByUsername(username).get();

        return (!clientToRegister.getActivationCode().equals(activationCode));
    }

    @Override
    public String encodeUserPassword(String password) {

        return passwordEncoder.encode(password);
    }

    @Override
    public Client validateClientToken(String token) {
        return this.clientRepository.findByActivationToken(token).get();
    }

    @Override
    public Optional<User> detailsOAuth(String idOAuth, String username, String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender, Integer salary, Role role, Long departmentId) {

        if(!validateOAuth(idOAuth)) return Optional.empty();

        User user = this.userRepository.findByIdOAuth(idOAuth).get();
        this.userRepository.delete(user);

        user.setUsername(username);
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public Optional<User> detailsAuthentication(String username, String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender, Integer salary, Role role, Long departmentId) {

        if(!validateUsername(username)) return Optional.empty();

        User user = this.userRepository.findByUsername(username).get();
        this.userRepository.delete(user);

        return Optional.of(this.userRepository.save(user));
    }

    private User setUserInfo(String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender, Integer salary, Role role, Long departmentId, User user) {
        user.setName(name);
        user.setSurname(surname);
        user.setSsn(ssn);
        user.setDateOfBirth(dateOfBirth);
        user.setGender(gender);
        user.setSalary(salary);
        user.setRole(role);
        user.setDepartmentId(departmentId);

        return user;
    }

}
