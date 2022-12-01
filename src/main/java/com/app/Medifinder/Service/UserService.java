package com.app.Medifinder.Service;
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

    @Autowired
    public UserService(VerificationTokenService verificationTokenService){
        this.verificationTokenService=verificationTokenService;
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    public User saveUser(User user){
        return userRepository.save(user);}
    public User fetchUserByEmailId(String email) {
        return userRepository.findByEmailId(email);
    }
    public User fetchUserByEmailIdAndPassword(String email,String password){
        return userRepository.findByEmailIdAndPassword(email, password);
    }
    public User register(){
        User user=new User();
        user.setEnabled(false);
        //return saveUser(user);
        Optional<User>saved=Optional.of(saveUser(user));
        saved.ifPresent(user1 -> {
            try {
                String token= UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(),token);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        return saved.get();
    }
}
