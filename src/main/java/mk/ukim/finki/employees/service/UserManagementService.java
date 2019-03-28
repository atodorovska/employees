package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.User;
import org.springframework.mail.SimpleMailMessage;

public interface UserManagementService {

    // must log out afterwards
    User changePassword(String oldPassword, String newPassword, String confirmNewPassword);

    void forgottenPasswordRequest(String username);

    String generatePassword();

    SimpleMailMessage sendEmailForForgottenPassword(String username);

    User removeEmployee(String username);  // admin

    User changeUserRole();  // admin

    User changeEmployeeDepartment(String departmentName);  // admin

    User changeEmployeeSalary(Integer newSalary);  // manager
}
