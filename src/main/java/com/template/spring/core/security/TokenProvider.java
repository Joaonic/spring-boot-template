package com.template.spring.core.security;

import com.template.spring.auth.model.User;
import com.template.spring.core.config.Config;
import com.template.spring.core.exceptions.custom.UnauthorizedException;
import com.template.spring.core.security.entities.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@NoArgsConstructor
public class TokenProvider {

    public String createToken(Authentication authentication) {
        UserPrincipal user;
        if (authentication.getPrincipal() instanceof User) {
            user = new UserPrincipal((User) authentication.getPrincipal());
        } else {
            user = (UserPrincipal) authentication.getPrincipal();
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000);

        //ADD PUBLIC USER INFOS TO JWT TOKEN
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject("1")
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .claim("role", user.getLevel());

        return jwtBuilder
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, Config.secret)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Config.secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.get("id").toString());
    }

    public void validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(Config.secret).parseClaimsJws(authToken);
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new UnauthorizedException();
        }
    }
}
