package com.edison.app.recipesweb.configuration;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.edison.app.recipesweb.configuration.JwtConstant.*;

public class JwtTokenValidator extends OncePerRequestFilter{

    //private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        String jwt = request.getHeader(JWT_HEADER);
        if(jwt != null){
            jwt = jwt.substring(7);
            try{
                Claims claims = Jwts.parser().verifyWith(JWT_SECRET).build().parseSignedClaims(jwt).getPayload();
        
                String email = String.valueOf(claims.get("email"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(Exception e){
                throw new BadCredentialsException("Invalid Token...");
            }
        }
        filterChain.doFilter(request, response);
    }

}
