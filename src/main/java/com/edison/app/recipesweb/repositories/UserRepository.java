package com.edison.app.recipesweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edison.app.recipesweb.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public User findByEmail(String email);

}
