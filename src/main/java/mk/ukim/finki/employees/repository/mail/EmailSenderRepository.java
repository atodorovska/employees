package mk.ukim.finki.employees.repository.mail;

public interface EmailSenderRepository {

     void sendRegistrationEmail(String to, String activationCode, String activationToken);

     void sendPasswordRecoveryEmail(String to, String newPassword);
}
