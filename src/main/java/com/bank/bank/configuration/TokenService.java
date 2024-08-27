package com.bank.bank.configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.bank.bank.models.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;


@Service
public class TokenService {

    public String gerarToken(User user) {
        //token é
        return JWT.create()
                .withIssuer("Posts")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        //tempo de requisição
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secreta"));
    }

    public String getSubject(String token) {
        try {

            // Verifica se o token não é nulo ou vazio
            if (token == null || token.isEmpty()) {
                return null;
            }

            // Verifica se o token não está expirado
            if (isTokenExpired(token)) {
                return null;
            }
            return JWT.require(Algorithm.HMAC256("secreta"))
                    .withIssuer("Posts")
                    .build().verify(token).getSubject();

        } catch (JWTDecodeException e) {
            return null;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            // Obtém a data de expiração do token
            Date expiration = JWT.decode(token).getExpiresAt();

            // Verifica se a data de expiração é anterior à data atual
            return expiration != null && expiration.before(Date.from(Instant.now()));
        } catch (JWTDecodeException e) {
            return true; // Se ocorrer um erro na decodificação do token, consideramos que está expirado
        }
    }
}