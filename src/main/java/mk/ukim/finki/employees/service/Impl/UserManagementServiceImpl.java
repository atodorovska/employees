package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.model.exceptions.*;
import mk.ukim.finki.employees.service.UserManagementService;
import org.springframework.mail.SimpleMailMessage;

public class UserManagementServiceImpl implements UserManagementService {

    @Override
    public User changePassword(String username, String oldPassword, String newPassword, String confirmNewPassword) throws UsernameNotFoundException, OldPasswordNotMatchedException, OldPasswordEqualsNewPasswordException, PasswordNotConfirmedException {
        return null;
    }

    @Override
    public Boolean forgottenPasswordRequest(String username) {
        return null;
    }

    @Override
    public String generatePassword() {
        return null;
    }

    @Override
    public SimpleMailMessage sendEmailForForgottenPassword(String email, String generatedPassword) {
        return null;
    }

    @Override
    public User removeEmployee(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public User changeUserRole(String username, Role role) throws UsernameNotFoundException, RoleNotExistsException {
        return null;
    }

    @Override
    public User changeEmployeeDepartment(String username, String departmentName) throws UsernameNotFoundException, DepartmentNotExistsException {
        return null;
    }

    @Override
    public User changeEmployeeSalary(String username, Integer newSalary) throws UsernameNotFoundException, SalarayNotInBoundsException {
        return null;
    }
}
