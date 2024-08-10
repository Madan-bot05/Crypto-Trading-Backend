package com.example.tradingplatform.controller;


import com.example.tradingplatform.config.JwtProvider;
import com.example.tradingplatform.model.User;
import com.example.tradingplatform.repository.UserRepository;
import com.example.tradingplatform.response.AuthResponse;
import com.example.tradingplatform.service.CustomUserDetailsService;
import com.example.tradingplatform.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register (@RequestBody User user) throws Exception {

        User isEmailExist=userRepository.findByEmail(user.getEmail());
        if(isEmailExist!=null){
            throw new Exception("Email already exists and used with another account");
        }
        User newUser=new User();
        newUser.setFullname(user.getFullname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setMobile(user.getMobile());
        User savedUser=userRepository.save(newUser);

        Authentication auth=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);
        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Regsiter Success");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody User user) throws Exception {
        String username=user.getEmail();
        String password=user.getPassword();
        Authentication auth=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt= JwtProvider.generateToken(auth);

        if(user.getTwoFactAuth().isEnabled()){
            AuthResponse res=new AuthResponse();
            res.setMessage("Two factor Auth Enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp= OtpUtils.generateOtp();

        }
        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login Success");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) throws Exception {
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
        if(userDetails==null){
            throw new Exception("Invalid Username");
        }
        if(!password.equals(userDetails.getPassword())){
            throw new Exception("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
