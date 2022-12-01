package com.app.Medifinder.Service;

import com.app.Medifinder.Entity.User;
import com.app.Medifinder.Entity.VerificationToken;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;

@Service
public class EmailService {
    private final VerificationTokenService verificationTokenService;

    public EmailService(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }
    public void sendHtmlMail(User user)throws MessagingException{
        VerificationToken verificationToken=verificationTokenService.findByUser(user);
        if(verificationToken!=null){
            String token=verificationToken.getToken();
            Context context=new Context();
            context.setVariable("title","Verify your email address");
            context.setVariable("link","http://localhos:8090/activation?token="+token);



        }

    }
}
