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

@Service
public class UserService {
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
}
