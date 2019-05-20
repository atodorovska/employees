package mk.ukim.finki.employees.repository.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSenderRepositoryImpl implements EmailSenderRepository{

    private JavaMailSender javaMailSender;

    public EmailSenderRepositoryImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendRegistrationEmail(String to, String activationCode, String activationToken) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Complete Registration!");
        simpleMailMessage.setText("To confirm your account, please click here:   " +
                "http://localhost:8080/api/users/registration?token="
                +activationToken + "   and the code you need to enter is: " + activationCode);

        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void sendPasswordRecoveryEmail(String to, String newPassword) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Password Recovery");
        simpleMailMessage.setText("Your new password is     : " + newPassword);

        javaMailSender.send(simpleMailMessage);
    }
}
