package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.repository.mail.EmailSenderRepository;
import mk.ukim.finki.employees.service.EmailSenderService;
import org.springframework.stereotype.Service;

// todo: see if ever used, if not delete the entire service

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
