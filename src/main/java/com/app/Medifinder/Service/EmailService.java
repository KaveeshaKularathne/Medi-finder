package com.app.Medifinder.Service;

import com.app.Medifinder.Entity.User;
import com.app.Medifinder.Entity.VerificationToken;
import com.app.Medifinder.site.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    private final VerificationTokenService verificationTokenService;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(VerificationTokenService verificationTokenService,TemplateEngine templateEngine,
                        JavaMailSender javaMailSender,JavaMailSender mailSender) {
        this.verificationTokenService = verificationTokenService;
        this.templateEngine=templateEngine;
        this.javaMailSender=javaMailSender;
        this.mailSender=mailSender;
    }
    public void sendMail(User user,String siteURL)throws MessagingException,UnsupportedEncodingException{
        VerificationToken verificationToken=verificationTokenService.findByUser(user);
        if(verificationToken!=null){

            String token=verificationToken.getToken();
//            Context context=new Context();
//            context.setVariable("title","Verify your mail address");
//            context.setVariable("link","http://localhost:8090/activation?token="+token);
//
//            String body=templateEngine.process("verification",context);
//            MimeMessage message=javaMailSender.createMimeMessage();
//            MimeMessageHelper helper=new MimeMessageHelper(message,true);
//            helper.setTo(user.getEmailId());
//            helper.setSubject("Email Address Verification");
//            helper.setText(body,true);
//            javaMailSender.send(message);

            String subject="Please verify your registration";
            String senderName="Medifinder Team";
            String mailContent= "<p>Dear "+user.getUsername() + ",</p>";
            mailContent+= "<p> <u> Please click the link below to verify to your registration</u></p>";
            String verifyURL=siteURL +"/verify?code"+verificationToken.getToken();
            mailContent+="<h3><a href=\""+verifyURL+"\">CLICK HERE TO VERIFY</a></h3>";
            mailContent+="<p>  Thank you <br>___The Medifinder Team___</p>";
            MimeMessage message= mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);

            helper.setFrom("kaveeshadomain@gmail.com",senderName);
            user.setEmailId(null);
            helper.setTo(user.getEmailId());
            helper.setSubject(subject);
            helper.setText(mailContent,true);

            this.mailSender.send(message);
            System.out.println("Email has been sent");
        }
    }
}
