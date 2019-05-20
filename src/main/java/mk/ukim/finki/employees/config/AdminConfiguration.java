package mk.ukim.finki.employees.config;

import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AdminConfiguration {

    @Bean
    User createAdmin(){

       User admin =  new User("admin", "admin@gmail.com", "admin");
       admin.setRole(Role.ADMIN);
       return admin;
    }
}
