package mk.ukim.finki.employees.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.Objects;

//@Entity
public class Client {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TO DO: not null for all
    private String username;
    private String email;
    private String password;
    private LocalDateTime timestamp;
    private String activationCode;
    private String activationToken;

    public Client(String username, String email, String password, LocalDateTime timestamp, String activationCode, String activationToken) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.timestamp = timestamp;
        this.activationCode = activationCode;
        this.activationToken = activationToken;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return username.equals(client.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                '}';
    }
}
