package com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.provider;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Role;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.common.TokenExpiration;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.common.TokenType;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.dto.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private static final String CATEGORY_KEY = "category";

    public JwtProvider(@Value("${spring.jwt.secretKey}") String key) {
        this.secretKey = new SecretKeySpec(
                key.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256
                        .key()
                        .build()
                        .getAlgorithm()
        );
    }

    public TokenResponse createTokens(Long memberId, Role role) {
        String accessToken = createAccessToken(memberId, role);
        String refreshToken = createRefreshToken();

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String token) {
        String memberId = getClaimsFromToken(token).getSubject();
        // 유저 권한은 하나밖에 없기에 singletonList 로 진행함. 단 하나의 권한만 가질때 사용.
        GrantedAuthority authority = new SimpleGrantedAuthority(getAuthority(token).getValue());
        return new UsernamePasswordAuthenticationToken(
                memberId,
                null,
                Collections.singletonList(authority)
        );
    }

    public boolean isNotValidateToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            validateExpiredToken(claims);
            return false;
        } catch (SecurityException | MalformedJwtException e) {
            log.warn(ErrorMessage.INVALID_TOKEN.getMessage() + token);
        } catch (ExpiredJwtException e) {
            log.warn(ErrorMessage.TOKEN_EXPIRED.getMessage() + token);
        } catch (UnsupportedJwtException e) {
            log.warn(ErrorMessage.TOKEN_HASH_NOT_SUPPORTED.getMessage() + token);
        } catch (IllegalArgumentException e) {
            log.warn(ErrorMessage.BAD_REQUEST_TOKEN.getMessage() + token);
        }
        return true;
    }

    private String createAccessToken(Long memberId, Role role) {
        Date date = new Date();
        Date validity = new Date(date.getTime() + TokenExpiration.ACCESS_TOKEN.getExpirationTime());

        return Jwts.builder()
                .subject(String.valueOf(memberId))
                .claim(CATEGORY_KEY, TokenType.ACCESS.getValue())
                .claim(TokenType.AUTHORIZATION_HEADER.getValue(), role)
                .expiration(validity)
                .signWith(this.secretKey)
                .compact();
    }

    private String createRefreshToken() {
        Date date = new Date();
        Date validity = new Date(date.getTime() + TokenExpiration.REFRESH_TOKEN.getExpirationTime());

        return Jwts.builder()
                .claim(CATEGORY_KEY, TokenType.REFRESH.getValue())
                .expiration(validity)
                .signWith(this.secretKey)
                .compact();
    }

    private Role getAuthority(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return Role.valueOf(claimsFromToken.get(TokenType.AUTHORIZATION_HEADER.getValue(), String.class));
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private void validateExpiredToken(Claims claims) {
        Date expiration = claims.getExpiration();
        if (expiration.before(new Date())) {
            throw new ExpiredJwtException(null, claims, ErrorMessage.TOKEN_EXPIRED.getMessage());
        }
    }

}