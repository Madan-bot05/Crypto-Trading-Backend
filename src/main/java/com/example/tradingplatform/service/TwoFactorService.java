package com.example.tradingplatform.service;


import com.example.tradingplatform.model.TwoFactorOtp;
import com.example.tradingplatform.model.User;


public interface TwoFactorService {
    TwoFactorOtp createTwoFactorOtp(User user,String otp,String jwt) ;
    TwoFactorOtp findByUser(Long userId);
    TwoFactorOtp findById(String id);
    boolean verifyTwoFactorOtp(TwoFactorOtp twoFactorOtp,String otp);
    void deleteTwoFactorOtp(TwoFactorOtp twoFactorOtp);
}
