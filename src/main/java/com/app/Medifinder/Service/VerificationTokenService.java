package com.app.Medifinder.Service;

import com.app.Medifinder.Entity.User;
import com.app.Medifinder.Entity.VerificationToken;
import com.app.Medifinder.Repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }
    @Transactional
    public VerificationToken findByToken(String token){
        return verificationTokenRepository.findByToken(token);
    }
    @Transactional
    public VerificationToken findByUser(User user){
     return verificationTokenRepository.findByUser(user);
    }
    @Transactional
    public void save(User user,String token){
      VerificationToken verificationToken=new VerificationToken(token,user);
      verificationToken.setExpiryDate(calculateExpiryDate(24*60));
      verificationTokenRepository.save(verificationToken);
    }
    private Timestamp calculateExpiryDate(int expiryTimeMinutes){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MINUTE,expiryTimeMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
