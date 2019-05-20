package mk.ukim.finki.employees.controller;

import mk.ukim.finki.employees.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/management")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;


     //TO BE IMPLEMENTED

}
