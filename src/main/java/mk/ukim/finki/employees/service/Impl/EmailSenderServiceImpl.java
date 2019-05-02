package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.repository.mail.EmailSenderRepository;
import mk.ukim.finki.employees.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

// TO DO: If we have to send emails through controller

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private EmailSenderRepository emailSenderRepository;

    public EmailSenderServiceImpl(EmailSenderRepository emailSenderRepository) {
        this.emailSenderRepository = emailSenderRepository;
    }

   /* @Override
    public void sendEmail(SimpleMailMessage email) {
       // this.emailSenderRepository.sendEmail(email);
    }*/
}
