package by.kes.altReality.controller.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import by.kes.altReality.data.domain.RealityCharacteristics;

@Component
public class JwtTokenUtil {

  @Value("${jwt.issuer}")
  private String issuer;

  @Value("${jwt.signing-key}")
  private String signingKey;

  @Value("${jwt.valid-for}")
  private Long validFor;

  public String generateToken(final RealityCharacteristics realityCharacteristics) {
    final Claims claims = Jwts.claims();
    claims.put("realityId", realityCharacteristics.getId());
    claims.put("name", realityCharacteristics.getRealityName());

    return Jwts.builder()
        .setClaims(claims)
        .setIssuer(issuer)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + validFor))
        .signWith(SignatureAlgorithm.HS256, signingKey)
        .compact();
  }
}
