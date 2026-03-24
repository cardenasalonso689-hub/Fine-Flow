package pe.edu.fineflow.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.*;

@Component
public class JwtProvider {

    @Value("${fineflow.jwt.secret}")
    private String secret;

    @Value("${fineflow.jwt.access-token-ms:900000}")    // 15 min default
    private long accessTokenMs;

    @Value("${fineflow.jwt.refresh-token-ms:604800000}") // 7 days default
    private long refreshTokenMs;

    private Key signingKey;

    @PostConstruct
    public void init() {
        this.signingKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public String generateAccessToken(UserPrincipal principal) {
        return Jwts.builder()
                .subject(principal.userId())
                .claim("schoolId",  principal.schoolId())
                .claim("email",     principal.email())
                .claim("role",      principal.role())
                .claim("type",      "ACCESS")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenMs))
                .id(UUID.randomUUID().toString())
                .signWith(signingKey)
                .compact();
    }

    public String generateRefreshToken(String userId, String jti) {
        return Jwts.builder()
                .subject(userId)
                .claim("jti_ref", jti)
                .claim("type",    "REFRESH")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenMs))
                .id(UUID.randomUUID().toString())
                .signWith(signingKey)
                .compact();
    }

    public Claims parseAndValidate(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValid(String token) {
        try { parseAndValidate(token); return true; }
        catch (JwtException | IllegalArgumentException e) { return false; }
    }

    public UserPrincipal extractPrincipal(String token) {
        Claims claims = parseAndValidate(token);
        return new UserPrincipal(
                claims.getSubject(),
                claims.get("schoolId", String.class),
                claims.get("email",    String.class),
                claims.get("role",     String.class),
                Set.of("ROLE_" + claims.get("role", String.class))
        );
    }

    public long getRefreshTokenMs() { return refreshTokenMs; }
}
