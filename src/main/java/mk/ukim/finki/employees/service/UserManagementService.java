package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.exceptions.*;
import mk.ukim.finki.employees.model.User;

public interface UserManagementService {

    // must log out afterwards
    User changePassword(String username, String oldPassword, String newPassword, String confirmNewPassword)
            throws UsernameNotFoundException, OldPasswordNotMatchedException, OldPasswordEqualsNewPasswordException,
            PasswordNotConfirmedException;

    Boolean validateUsername(String username);

    // checks if user exists in db
    void forgottenPasswordRequest(String username);

    // also puts generated password in db
    String generatePassword();

    // SimpleMailMessage sendEmailForForgottenPassword(String email, String generatedPassword);

    User removeEmployee(String username) throws UsernameNotFoundException;  // admin

    User changeUserRole(String username, Role role) throws UsernameNotFoundException, RoleNotExistsException;  // admin

    User changeEmployeeDepartment(String username, String departmentName)
            throws UsernameNotFoundException, DepartmentNotExistsException;  // admin

    User changeEmployeeSalary(String username, Integer newSalary)
            throws UsernameNotFoundException, SalarayNotInBoundsException;  // manager
}
