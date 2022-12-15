package com.app.Medifinder.Service;
import com.app.Medifinder.site.Utility;
import net.bytebuddy.utility.RandomString;
import com.app.Medifinder.Entity.User;
import com.app.Medifinder.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final VerificationTokenService verificationTokenService;
    private final UserRepository userRepository;

    private EmailService emailService;
    @Autowired
    public UserService(VerificationTokenService verificationTokenService,
                       EmailService emailService,UserRepository userRepository){
        this.verificationTokenService=verificationTokenService;
        this.emailService=emailService;
        this.userRepository=userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);}
    public User fetchUserByEmailId(String email) {
        return userRepository.findByEmailId(email);
    }
    public User fetchUserByEmailIdAndPassword(String email,String password){
        return userRepository.findByEmailIdAndPassword(email, password);
    }
    public User register(String siteURL){
        User user=new User();
        user.setEnabled(false);

        //return saveUser(user);
        Optional<User>saved=Optional.of(saveUser(user));
        saved.ifPresent(user1 -> {
            try {
                String token= UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(),token);

                emailService.sendMail(user,siteURL);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        return saved.get();
    }
}
