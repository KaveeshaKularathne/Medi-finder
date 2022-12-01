package com.app.Medifinder.Repository;
import com.app.Medifinder.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmailId(String emailId);

    public User findByEmailIdAndPassword(String emailId, String password);


}