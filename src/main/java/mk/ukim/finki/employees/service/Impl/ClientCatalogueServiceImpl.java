package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Client;
import mk.ukim.finki.employees.model.exceptions.EmailAssociatedWithUserException;
import mk.ukim.finki.employees.model.exceptions.PasswordNotValidatedException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.employees.repository.jpa.ClientRepository;
import mk.ukim.finki.employees.repository.mail.EmailSenderRepository;
import mk.ukim.finki.employees.service.ClientCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ClientCatalogueServiceImpl implements ClientCatalogueService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmailSenderRepository emailSenderRepository;

    @Override
    public Optional<Client> catalogueNewClient(String username, String email, String password) throws UsernameAlreadyExistsException, EmailAssociatedWithUserException, PasswordNotValidatedException {
        if(!validateUsername(username)) throw new UsernameAlreadyExistsException();
        if(!validateEmail(email)) throw new EmailAssociatedWithUserException();
        if(!validatePassword(password)) throw new PasswordNotValidatedException();

        String activationCode = generateActivationCode();
        String activationToken = generateActivationToken();

        this.emailSenderRepository.sendRegistrationEmail(email, activationCode, activationToken);

        return Optional.of(this.clientRepository.save(new Client(username, email, password, LocalDateTime.now(), activationCode, activationToken)));
    }

    @Override
    public Boolean validateUsername(String username) {
        if(this.clientRepository.findByUsername(username).isPresent()) return false;
        return true;
    }

    @Override
    public Boolean validateEmail(String email) {
        if(this.clientRepository.findByEmail(email).isPresent()) return false;
        return true;
    }


    @Override
    public Boolean validatePassword(String password) {

        if(password.length() < 8) return false;

        char[] passSequence = password.toCharArray();
        boolean oneLetter = false;
        boolean oneDigit = false;

        for(char c : passSequence){
            if(Character.isAlphabetic(c)) oneLetter = true;
            if(Character.isDigit(c)) oneDigit = true;
        }

        if(oneDigit && oneLetter) return true;
        return false;
    }

    @Override
    public String generateActivationCode() {

        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();

        for(int i=0; i<7; i++){
            stringBuilder.append((char) random.nextInt(127));
        }

        return stringBuilder.toString();
    }

    @Override
    public String generateActivationToken(){

        return UUID.randomUUID().toString();
    }


    @Override
    @Scheduled(cron = "0 0 2 * * *")
    public void removeAllPostExpiration() {

        this.clientRepository.findAll().stream()
                .filter(i -> i.getTimestamp().toLocalTime().isBefore(LocalTime.now().minusHours(24)))
                .forEach(j -> this.clientRepository.delete(j));
    }


    @Override
    public Optional<List<Client>> getAll() {
        return Optional.of(this.clientRepository.findAll());
    }


}
