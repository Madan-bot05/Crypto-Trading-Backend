package com.example.tradingplatform.service;

import com.example.tradingplatform.config.JwtProvider;
import com.example.tradingplatform.domain.VerificationType;
import com.example.tradingplatform.model.TwoFactAuth;
import com.example.tradingplatform.model.User;
import com.example.tradingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserserviceImpl implements UserService{
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User Not Found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User Not Found");
        }
        return user;
    }

    @Override
    public User findUserByID(long id) throws Exception {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("User not found");
        }
        return user.get();
    }

    @Override
    public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
        TwoFactAuth twoFactAuth=new TwoFactAuth();
        twoFactAuth.setEnabled(true);
        twoFactAuth.setSendTo(verificationType);
        user.setTwoFactAuth(twoFactAuth);

        return userRepository.save(user);
    }


    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}
