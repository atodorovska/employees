package mk.ukim.finki.employees.controller;

import mk.ukim.finki.employees.model.Client;
import mk.ukim.finki.employees.model.exceptions.EmailAssociatedWithUserException;
import mk.ukim.finki.employees.model.exceptions.PasswordNotValidatedException;
import mk.ukim.finki.employees.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.employees.service.ClientCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/clients/catalogue")
public class ClientCatalogueController {

    @Autowired
    private ClientCatalogueService clientCatalogueService;

    // todo: change this method, if not needed delete it
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAll(){
        return this.clientCatalogueService.getAll().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Client> catalogueNewClient(HttpServletResponse response, @RequestParam String username, @RequestParam String email, @RequestParam String password) throws PasswordNotValidatedException, EmailAssociatedWithUserException, UsernameAlreadyExistsException {
        response.addCookie(new Cookie("username", username));
        return clientCatalogueService.catalogueNewClient(username, email, password)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
