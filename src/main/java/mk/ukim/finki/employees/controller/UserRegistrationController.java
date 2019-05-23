package mk.ukim.finki.employees.controller;

import mk.ukim.finki.employees.model.Gender;
import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/api/users/registration")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/token")
    public void getClientToken(HttpServletResponse response, @RequestParam String token) throws IOException {
        if (this.userRegistrationService.validateClientToken(token)!= null) {
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);

            // todo: change this -- goes to client page for activation !!!!!!!
            response.sendRedirect("http://localhost:8080/api/users/registration/activation");
        }
    }

    // todo: change this method, see if needed
    @PostMapping("/activation")
    public void completeClientRegistration(HttpServletResponse response, HttpServletRequest request, @RequestBody String code) throws IOException {

        String token = Arrays.stream(request.getCookies()).findAny().get().getValue();
        this.userRegistrationService.completeClientRegistration(token, code);

        // todo: change this -- goes to client page for my profile !!!!!!!
        response.sendRedirect("http://localhost:4200/authentication-details");
    }

    @PostMapping("/authentication-details")
    public ResponseEntity<User> completeDetailsAuthentication(HttpServletRequest request, @RequestBody String name,
                                                     @RequestBody String surname, @RequestBody String ssn, @RequestBody LocalDate dateOfBirth,
                                                     @RequestBody String gender, @RequestBody Integer salary,
                                                     @RequestBody String role, @RequestBody Long departmentId){
        String username = Arrays.stream(request.getCookies()).findFirst().get().getValue();
        Gender g = getGender(gender);
        Role r = getRole(role);

        return this.userRegistrationService.detailsAuthentication(username, name, surname, ssn, dateOfBirth, g, salary, r, departmentId)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping("/oauth-details")
    public ResponseEntity<User> completeDetailsOAuth(HttpServletRequest request, @RequestBody String username, @RequestBody String name,
                                                     @RequestBody String surname, @RequestBody String ssn, @RequestBody LocalDate dateOfBirth,
                                                     @RequestBody String gender, @RequestBody Integer salary,
                                                     @RequestBody String role, @RequestBody Long departmentId){
        String idOAuth = Arrays.stream(request.getCookies()).findFirst().get().getValue();
        Gender g = getGender(gender);
        Role r = getRole(role);

        return this.userRegistrationService.detailsOAuth(idOAuth, username, name, surname, ssn, dateOfBirth, g, salary, r, departmentId)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    private Gender getGender(String gender){
        Gender g = Gender.FEMALE;
        if(gender.equals("M")) g = Gender.MALE;
        if(gender.equals("U")) g = Gender.UNSPECIFIED;
        return g;
    }

    private Role getRole(String role){
        Role r = Role.EMPLOYEE;
        if(role.equals("A")) r = Role.ADMIN;
        if(role.equals("M")) r = Role.MANAGER;
        return r;
    }


}
