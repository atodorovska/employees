package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.service.UserManagementService;
import org.springframework.mail.SimpleMailMessage;

public class UserManagementServiceImpl implements UserManagementService {
    @Override
    public User changePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        return null;
    }

    @Override
    public void forgottenPasswordRequest(String username) {

    }

    @Override
    public String generatePassword() {
        return null;
    }

    @Override
    public SimpleMailMessage sendEmailForForgottenPassword(String username) {
        return null;
    }

    @Override
    public User removeEmployee(String username) {
        return null;
    }

    @Override
    public User changeUserRole() {
        return null;
    }

    @Override
    public User changeEmployeeDepartment(String departmentName) {
        return null;
    }

    @Override
    public User changeEmployeeSalary(Integer newSalary) {
        return null;
    }
}
