package com.edison.app.recipesweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edison.app.recipesweb.entities.User;
import com.edison.app.recipesweb.repositories.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception{
        User userExist = repository.findByEmail(user.getEmail());
        if(userExist != null){
            throw new Exception("El usuario ya existe con el correo: "+user.getEmail());
        }
        User savedUser = repository.save(user);
        return savedUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception{
        repository.deleteById(userId);
        return "Usuario eliminado con exito";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception{
        List<User> users = repository.findAll();
        return users;
    }

    

}
