package com.edison.app.recipesweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edison.app.recipesweb.configuration.JwtProvider;
import com.edison.app.recipesweb.entities.User;
import com.edison.app.recipesweb.repositories.UserRepository;
import com.edison.app.recipesweb.request.LoginRequest;
import com.edison.app.recipesweb.response.AuthResponse;
import com.edison.app.recipesweb.services.CustomeUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{
        
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullname();

        User isExistEmail = userRepository.findByEmail(email);
        if(isExistEmail != null){
            throw new Exception("Email is already use with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullname(fullName);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("signup success");

        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest LoginRequest){
        String username = LoginRequest.getEmail();
        String password = LoginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("signin success");

        return res;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("User not found");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
