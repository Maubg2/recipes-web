package com.edison.app.recipesweb.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class AppConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        return null;
    }

}
