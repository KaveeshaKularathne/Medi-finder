package com.app.Medifinder.Service;

import com.app.Medifinder.Entity.User;
import com.app.Medifinder.Entity.VerificationToken;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {
    private final VerificationTokenService verificationTokenService;

    public EmailService(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }
    public void sendHtmlMail(User user)throws MessagingException{
        VerificationToken verificationToken=verificationTokenService.findByUser(user);

    }
}
