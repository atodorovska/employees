package mk.ukim.finki.employees.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ActiveUserController {

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
