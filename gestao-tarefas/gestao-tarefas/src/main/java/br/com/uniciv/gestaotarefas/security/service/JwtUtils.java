package br.com.uniciv.gestaotarefas.security.service;

import br.com.uniciv.gestaotarefas.security.models.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

  @Value("${app.jwt.SecretKey}")
  private String jwtSecret;

  @Value("${app.jwt.ExpirationMs}")
  private Integer jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {

    var userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    var currentTime = new Date();
    var expirationTime = new Date(currentTime.getTime() + jwtExpirationMs);

    return Jwts.builder()
      .setSubject(userPrincipal.getUsername())
      .setIssuedAt(currentTime)
      .setExpiration(expirationTime)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

  public String getUsernameFromJwtToken(String token) {
    return Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(authToken);
      return true;
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return false;
  }
}

