package co.kr.capstonemju.JobBrief.domain.auth.jwt;


import co.kr.capstonemju.JobBrief.domain.auth.model.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final String secret;
    private final int expirySeconds;

    public JwtProvider(
            @Value("${jwt.token.secret}") String secret,
            @Value("${jwt.token.access-token-validity-in-seconds}") int expirySeconds) {

        this.secret = secret;
        this.expirySeconds = expirySeconds;
    }

    public String createToken(PrincipalDetails principalDetails) {
        Date now = new Date();
        return JWT.create()
                .withSubject(principalDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + expirySeconds * 1000L))
                .withClaim("id", principalDetails.getMember().getId())
                .withClaim("userId", principalDetails.getUsername())
                .sign(Algorithm.HMAC512(secret));
    }

    public DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
    }
}