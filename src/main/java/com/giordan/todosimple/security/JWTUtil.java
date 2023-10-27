package com.giordan.todosimple.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.print.attribute.standard.JobKOctets;

import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Objects;

public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username){
        SecretKey key = getKeyBySecret();
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
                .signWith(key)
                .compact();
    }

    public boolean isValid(String token){
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if(Objects.nonNull(username) && Objects.nonNull(expirationDate) && now.before(expirationDate)) return true;
        }
        return false;
    }

    private Claims getClaims(String token){
        SecretKey key = getKeyBySecret();
        try{
            return Jwts.parserBuilder().setSigningKey().build().parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }

    }

    private SecretKey getKeyBySecret() {
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
        return key;
    }

}
