package mk.ukim.finki.employees.controller;

import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/management")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return this.userManagementService.getAllUsers().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all/department")
    public ResponseEntity<List<User>> getAllUsersOfDepartment(@RequestParam Long departmentId){
        return this.userManagementService.getAllUsersOfDepartment(departmentId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/username")
    public ResponseEntity<User> getUserWithUsername(@RequestParam String username){
        return this.userManagementService.getUserWithUsername(username).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/id")
    public ResponseEntity<User> getUserWithUsername(@RequestParam Long id){
        return this.userManagementService.getUserById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all/search/username")
    public ResponseEntity<List<User>> getAllUsersWithSearchUsername(@RequestParam String search){
        return this.userManagementService.getUserWithSearchUsername(search).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all/search/name")
    public ResponseEntity<List<User>> getAllUsersWithSearchName(@RequestParam String search){
        return this.userManagementService.getUserWithSearchName(search).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all/search/surname")
    public ResponseEntity<List<User>> getAllUsersWithSearchSurname(@RequestParam String search){
        return this.userManagementService.getUserWithSearchSurname(search).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all/search/salary")
    public ResponseEntity<List<User>> getAllUsersWithSearchSalary(@RequestParam Integer search){
        return this.userManagementService.getUserWithSearchSalary(search).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



}
