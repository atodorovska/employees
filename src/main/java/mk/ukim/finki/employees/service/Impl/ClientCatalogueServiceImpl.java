package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Client;
import mk.ukim.finki.employees.model.exceptions.EmailAssociatedWithUserException;
import mk.ukim.finki.employees.model.exceptions.PasswordNotValidatedException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.employees.service.ClientCatalogueService;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public class ClientCatalogueServiceImpl implements ClientCatalogueService {

    @Override
    public Client catalogueNewClient(String username, String email, String password) throws UsernameAlreadyExistsException, EmailAssociatedWithUserException, PasswordNotValidatedException {
        return null;
    }

    @Override
    public Boolean validateClientData(String username, String email) {
        return null;
    }

    @Override
    public Boolean validatePassword(String password) {
        return null;
    }

    @Override
    public String generateActivationCode() {
        return null;
    }

    @Override
    public String generateActivationToken() {
        return null;
    }

    @Override
    public SimpleMailMessage sendEmailMessage(String email, String activationCode, String activationToken) {
        return null;
    }

    @Override
    public List<Client> removeAllPostExpiration() {
        return null;
    }
}
