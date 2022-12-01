package com.app.Medifinder.Controller;
import com.app.Medifinder.Entity.User;
import com.app.Medifinder.Service.UserService;
import com.app.Medifinder.Service.VerificationTokenService;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    @Autowired
    public UserController(UserService userService,VerificationTokenService verificationTokenService){
        this.userService=userService;
        this.verificationTokenService=verificationTokenService;
    }
    @PostMapping("/registration")
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Accept"})
    public User registerUser(@RequestBody User user,String siteURL, HttpServletRequest request)
            throws UnsupportedOperationException, MessagingException, Exception {

        String tempEmailId = user.getEmailId();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            User userObj = userService.fetchUserByEmailId(tempEmailId);
            if (userObj != null) {
                throw new Exception("user with " + tempEmailId + " is already exists...");
            }
        }
        User userObj = null;
        userObj=userService.saveUser(user);
        logger.info("User registered successfully");
        userService.register();
        return userObj;
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Accept"})
     public User loginUser(@RequestBody User user) throws Exception {
        System.out.println("request accepted");
        String tempEmailId = user.getEmailId();
        String tempPass = user.getPassword();
        User userObj = null;
        if (tempEmailId != null && tempPass != null) {
            userObj = userService.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
        }
        if (userObj == null) {
            throw new Exception("invalid user credentials..");
        }
        return userObj;
    }
    @GetMapping("/registeration-verify")
    public String activation(@RequestParam("token")String token,Model model){

        return "";
    }


}







