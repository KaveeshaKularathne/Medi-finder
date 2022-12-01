package com.app.Medifinder.Service;

import com.app.Medifinder.Entity.User;
import com.app.Medifinder.Entity.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final VerificationTokenService verificationTokenService;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailService(VerificationTokenService verificationTokenService,TemplateEngine templateEngine,
                        JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.templateEngine=templateEngine;
        this.javaMailSender=javaMailSender;
    }
    public void sendHtmlMail(User user)throws MessagingException{
        VerificationToken verificationToken=verificationTokenService.findByUser(user);
        if(verificationToken!=null){
            String token=verificationToken.getToken();
            Context context=new Context();
            context.setVariable("title","Verify your email address");
            context.setVariable("link","http://localhos:8090/activation?token="+token);

            String body=templateEngine.process("register-success",context);

            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setTo(user.getEmailId());
            helper.setSubject("Email address verification");
            helper.setText(body,true);
            javaMailSender.send(message);

        }

    }
}
