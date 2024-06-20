package com.edison.app.recipesweb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edison.app.recipesweb.configuration.JwtProvider;
import com.edison.app.recipesweb.entities.User;
import com.edison.app.recipesweb.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new Exception("Usuario no encontrado con id "+userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if(email == null){
            throw new Exception("Provide valid jwt token....");
        }
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User not found with email "+email);
        }

        return user;
    }

    

}
