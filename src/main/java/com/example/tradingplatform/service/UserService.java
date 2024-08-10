package com.example.tradingplatform.service;


import com.example.tradingplatform.model.User;

public interface UserService {
    public User findUserByJwt(String jwt);
    public User findUserByEmail(String email);
    public User findUserByID(long id);
    public User enableTwoFactorAuthentication(User user);
    public User updatePassword(User user,String newPassword);
}
