package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.model.exceptions.*;
import mk.ukim.finki.employees.repository.jpa.UserRepository;
import mk.ukim.finki.employees.repository.mail.EmailSenderRepository;
import mk.ukim.finki.employees.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSenderRepository emailSenderRepository;

    @Override
    public User changePassword(String username, String oldPassword, String newPassword, String confirmNewPassword) throws UsernameNotFoundException, OldPasswordEqualsNewPasswordException, PasswordNotConfirmedException {
        if(!validateUsername(username)) throw new UsernameNotFoundException();
        if(oldPassword.equals(newPassword)) throw new OldPasswordEqualsNewPasswordException();
        if(!newPassword.equals(confirmNewPassword)) throw new PasswordNotConfirmedException();

        User user = this.userRepository.findByUsername(username);
        user.setPassword(newPassword);
        return user;
    }

    @Override
    public Boolean validateUsername(String username){
        if(this.userRepository.findByUsername(username) == null) return false;
        return true;
    }

    @Override
    public void forgottenPasswordRequest(String username) {
        if(validateUsername(username))
        this.emailSenderRepository.sendPasswordRecoveryEmail(this.userRepository.findByUsername(username).getEmail(),generatePassword());
    }

    @Override
    public String generatePassword() {

        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();

        for(int i=0; i<8; i++){
            stringBuilder.append((char) random.nextInt(127));
        }

        return stringBuilder.toString();
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
