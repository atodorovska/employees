package mk.ukim.finki.employees.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.util.Objects;

//@Entity
public class User {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TO DO: not null for username, name, surname, ss, dateOfBirth, gender, salary, role, departmentName
    private String username;
    private String email;
    private String password;
    private String idOAuth;
    private String name;
    private String surname;
    private String ssn;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Integer salary;
    private Role role;
    private String departmentName;

    public User(String username, String idOAuth, String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender, Integer salary, Role role, String departmentName) {
        this.username = username;
        this.idOAuth = idOAuth;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.salary = salary;
        this.role = role;
        this.departmentName = departmentName;
    }

    public User(String username, String email, String password, String name, String surname, String ssn, LocalDate dateOfBirth, Gender gender, Integer salary, Role role, String departmentName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.salary = salary;
        this.role = role;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdOAuth() {
        return idOAuth;
    }

    public void setIdOAuth(String idOAuth) {
        this.idOAuth = idOAuth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
