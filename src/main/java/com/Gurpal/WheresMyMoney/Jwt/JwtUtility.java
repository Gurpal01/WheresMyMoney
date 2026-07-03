package com.Gurpal.WheresMyMoney.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtility {

    private final long EXPIRATION_TIME = 1000*60*60;
    private final String SECRET = "my-super-secret-key-that-is-long-enough-1234567890!@#";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String createToken(String userName)
    {
        String token = Jwts.builder() //we can see all these details that wee are setting in jwt.io ie. it is part of token
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public Claims getClaims(String token)
    {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims;
    }

    public String getUsernameFromToken(String token)
    {
        Claims claims = getClaims(token);

        return claims.getSubject();
    }

    public boolean isTokenExpired(String token)
    {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public boolean validateToken(UserDetails userDetails,String token,String userName)
    {
        return (userDetails.getUsername().equals(userName) && !isTokenExpired(token));
    }


}
