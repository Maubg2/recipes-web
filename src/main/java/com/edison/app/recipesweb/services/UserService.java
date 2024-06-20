package com.edison.app.recipesweb.services;

import com.edison.app.recipesweb.entities.User;

public interface UserService {
    
    public User findUserById(Long userId) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;
    
}
