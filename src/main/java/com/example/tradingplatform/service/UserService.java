package com.example.tradingplatform.service;


import com.example.tradingplatform.domain.VerificationType;
import com.example.tradingplatform.model.User;

public interface UserService {
    public User findUserByJwt(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User findUserByID(long id) throws Exception;
    public User enableTwoFactorAuthentication(VerificationType verificationType,String sendTo, User user);
    public User updatePassword(User user,String newPassword);

}
