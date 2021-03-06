package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Department;
import mk.ukim.finki.employees.model.Role;
import mk.ukim.finki.employees.model.User;
import mk.ukim.finki.employees.model.exceptions.*;
import mk.ukim.finki.employees.repository.jpa.DepartmentRepository;
import mk.ukim.finki.employees.repository.jpa.UserRepository;
import mk.ukim.finki.employees.repository.mail.EmailSenderRepository;
import mk.ukim.finki.employees.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmailSenderRepository emailSenderRepository;

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.of(this.userRepository.findAll());
    }

    @Override
    public Optional<List<User>> getAllUsersOfDepartment(Long departmentId) {
        return this.userRepository.findAllByDepartmentId(departmentId);
    }

    @Override
    public Optional<User> getUserWithUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<List<User>> getUserWithSearchUsername(String search) {
        return this.userRepository.findAllByUsernameContainingIgnoreCase(search);
    }

    @Override
    public Optional<List<User>> getUserWithSearchName(String search) {
        return this.userRepository.findAllByNameContainingIgnoreCase(search);
    }

    @Override
    public Optional<List<User>> getUserWithSearchSurname(String search) {
        return this.userRepository.findAllBySurnameContainingIgnoreCase(search);
    }

    @Override
    public Optional<List<User>> getUserWithSearchSalary(Integer search) {
        return this.userRepository.findAllBySalaryLessThanEqual(search);
    }

    @Override
    public Optional<User> changePassword(String username, String oldPassword, String newPassword, String confirmNewPassword) throws UsernameNotFoundException, OldPasswordEqualsNewPasswordException, PasswordNotConfirmedException {
        if(!validateUsername(username)) throw new UsernameNotFoundException();
        if(oldPassword.equals(newPassword)) throw new OldPasswordEqualsNewPasswordException();
        if(!newPassword.equals(confirmNewPassword)) throw new PasswordNotConfirmedException();

        User user = this.userRepository.findByUsername(username).get();
        this.userRepository.delete(user);
        user.setPassword(newPassword);
        this.userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public Boolean validateUsername(String username){
        if(!this.userRepository.findByUsername(username).isPresent()) return false;
        return true;
    }

    @Override
    public void forgottenPasswordRequest(String username) throws UsernameNotFoundException{

        if(!validateUsername(username)) throw new UsernameNotFoundException();
        this.emailSenderRepository.sendPasswordRecoveryEmail(this.userRepository.findByUsername(username).get().getEmail(),generatePassword());
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

        if(!validateUsername(username)) throw new UsernameNotFoundException();

        User user = this.userRepository.findByUsername(username).get();
        this.userRepository.delete(user);
        return user;
    }

    @Override
    public User changeUserRole(String username, Role role) throws UsernameNotFoundException{

        if(!validateUsername(username)) throw new UsernameNotFoundException();

        User user = this.userRepository.findByUsername(username).get();
        this.userRepository.delete(user);
        user.setRole(role);
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User changeEmployeeDepartment(String username, String departmentName) throws UsernameNotFoundException {

        if(!validateUsername(username)) throw new UsernameNotFoundException();

        User user = this.userRepository.findByUsername(username).get();
        this.userRepository.delete(user);
        Department department = this.departmentRepository.findByName(departmentName).get();
        user.setDepartmentId(department.getId());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User changeEmployeeSalary(String username, Integer newSalary) throws UsernameNotFoundException, SalarayNotInBoundsException {

        if(!validateUsername(username)) throw new UsernameNotFoundException();

        User user = this.userRepository.findByUsername(username).get();
        this.userRepository.delete(user);


        user.setSalary(newSalary);
        this.userRepository.save(user);
        return user;

    }


    @Override
    public Boolean checkSalaryInBounds(Integer salary, Integer lowerBound, Integer upperBound){
        if(salary > lowerBound && salary < upperBound) return true;
        return false;
    }
}
