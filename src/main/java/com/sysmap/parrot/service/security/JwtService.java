package com.sysmap.parrot.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService implements IJwtService {

    private final long EXPIRATION_TIME = 14400000;//4h
    private final String KEY = "472D4B6150645367566B59703373367638792F423F4528482B4D625165546857";

    public String generateToken(UUID userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token, String userId) {
        String sub = getClaim(token, Claims::getSubject);
        Date expiration = getClaim(token, Claims::getExpiration);

        if (!sub.equals(userId)) {
            return false;
        }

        if (expiration.before(new Date())) {
            return false;
        }

        return true;
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(genSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }

    private Key genSignInKey() {
        byte[] decodedKey = Base64.getDecoder().decode(KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}

