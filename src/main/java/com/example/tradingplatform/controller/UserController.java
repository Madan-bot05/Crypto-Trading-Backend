package com.example.tradingplatform.controller;


import com.example.tradingplatform.domain.VerificationType;
import com.example.tradingplatform.model.User;
import com.example.tradingplatform.service.EmailService;
import com.example.tradingplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping("api/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwt(jwt);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<User> sendVerificationOtp(@RequestHeader("Authorization") String jwt, @PathVariable VerificationType verificationType) throws Exception {
        User user=userService.findUserByJwt(jwt);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @PatchMapping ("api/users/enable-two-factor/verify-otp/{otp}")
    public ResponseEntity<User> enableTwoFactorAuthentication(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwt(jwt);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


}