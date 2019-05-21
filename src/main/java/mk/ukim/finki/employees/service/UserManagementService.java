package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.exceptions.*;
import mk.ukim.finki.employees.model.User;

import java.util.List;
import java.util.Optional;

public interface UserManagementService {

    // crud methods

    Optional<List<User>> getAllUsers();

    Optional<List<User>> getAllUsersOfDepartment(Long departmentId);

    Optional<User> getUserWithUsername(String username);

    Optional<User> getUserById(Long id);

    Optional<List<User>> getUserWithSearchUsername(String search);

    Optional<List<User>> getUserWithSearchName(String search);

    Optional<List<User>> getUserWithSearchSurname(String search);

    Optional<List<User>> getUserWithSearchSalary(Integer search);

    // must log out afterwards
    Optional<User> changePassword(String username, String oldPassword, String newPassword, String confirmNewPassword)
            throws UsernameNotFoundException, OldPasswordNotMatchedException, OldPasswordEqualsNewPasswordException,
            PasswordNotConfirmedException;

    Boolean validateUsername(String username);

    // checks if user exists in db
    void forgottenPasswordRequest(String username) throws UsernameNotFoundException;

    // also puts generated password in db
    String generatePassword();

    // SimpleMailMessage sendEmailForForgottenPassword(String email, String generatedPassword);

    User removeEmployee(String username) throws UsernameNotFoundException;  // admin

    User changeUserRole(String username, Role role) throws UsernameNotFoundException;  // admin

    User changeEmployeeDepartment(String username, String departmentName)
            throws UsernameNotFoundException;  // admin

    User changeEmployeeSalary(String username, Integer newSalary)
            throws UsernameNotFoundException, SalarayNotInBoundsException;  // manager

    Boolean checkSalaryInBounds(Integer salary, Integer lowerBound, Integer upperBound);
}
