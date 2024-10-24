package com.afterschool.test.Service;

import com.afterschool.test.Entity.User;
import com.afterschool.test.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final UserRepo userRepository;

    public Long login(String email, String password){
        User user = userRepository.findUserByEmail(email);
        if(user.getPassword().equals(password)){
            return user.getId();
        }
        return null;
    }
    
    public void signup(User user){
        userRepository.save(user);
    }
}
