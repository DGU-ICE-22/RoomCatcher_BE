package com.roomcatcher.RoomCatcher.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component // TokenProvider 클래스를 빈으로 등록
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String USER_ID = "userId";

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14; // 토큰 만료시간 설정

    @Value("${jwt.secret}") // application.yml 파일에 설정한 암호화 키를 가져옴
    private String JWT_SECRET;


    // Authentication 객체로 AccessToken 발행
    public String issueAccessToken(final Authentication authentication) { // Authentication 객체 : 인증 주체(로그인하는 사용자)를 설명하기 위한 Spring Security의 인터페이스
        return generateToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME);
    }


    /*
    사용자 정보를 포함한 JWT를 생성하여 반환
    JWT에는 발행 시간, 만료 시간, 사용자 ID 등의 정보가 포함
    반환된 JWT는 응답으로 클라이언트에게 전달되거나, 클라이언트가 인증이 필요한 요청을 할 때 사용
    */
    public String generateToken(Authentication authentication, Long tokenExpirationTime) {
        final Date now = new Date();
        final Claims claims = Jwts.claims()
                                  .setIssuedAt(now)
                                  .setExpiration(new Date(now.getTime() + tokenExpirationTime)); // 만료 시간 설정

        claims.put(USER_ID, authentication.getPrincipal());

        return Jwts.builder()
                   .setHeaderParam(Header.TYPE, Header.JWT_TYPE)  // Header 설정
                   .setClaims(claims)  // Claim 설정
                   .signWith(getSigningKey())  // 서명(Signature) 설정
                   .compact();  // 최종적으로 JWT 토큰을 생성
    }

    private SecretKey getSigningKey() {
        String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes()); //SecretKey 통해 서명 생성
        return Keys.hmacShaKeyFor(encodedKey.getBytes());   //일반적으로 HMAC (Hash-based Message Authentication Code) 알고리즘 사용 -> 암호화 키 생성
    }

    // Token에서 Claim을 추출하는 과정에서 에러가 발생하면 catch한후 validation 진행
    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getBody(token);
            return JwtValidationType.VALID_JWT;
        } catch (MalformedJwtException ex) {
            return JwtValidationType.INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException ex) {
            return JwtValidationType.EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException ex) {
            return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException ex) {
            return JwtValidationType.EMPTY_JWT;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSigningKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    // Token의 Claim에서 userId 값을 추출
    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }
}