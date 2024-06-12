package com.edison.app.recipesweb.configuration;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class JwtConstant {

    public static final SecretKey JWT_SECRET = Jwts.SIG.HS256.key().build();

    public static final String JWT_HEADER = "Authorization";

}
