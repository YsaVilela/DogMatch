package br.com.dogmatch.apiprincipal.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.dogmatch.apiprincipal.Entity.Usuario;
import br.com.dogmatch.apiprincipal.infra.Exception.ValidationException;

@Service
public class TokenService {
	 @Value("${api.security.token.secret}")
	    private String secret;

	    public String gerarToken(Usuario usuario) {
	        try {
	            var algoritmo = Algorithm.HMAC256(secret);
	            return JWT.create()
	                    .withIssuer("DogMatch")
	                    .withSubject(usuario.getLogin())
	                    .withExpiresAt(dataExpiracao())
	                    .withClaim("idUsuario", usuario.getTutor().getId())
	                    .sign(algoritmo);
	        } catch (JWTCreationException exception){
	            throw new RuntimeException("Erro ao gerar token jwt", exception);
	        }
	    }

	    private Instant dataExpiracao() {
	        return LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.of("-03:00"));
	    }
	    
	    public String getSubject(String tokenJWT) {
	        try {
	            var algoritmo = Algorithm.HMAC256(secret);
	            return JWT.require(algoritmo)
	                    .withIssuer("DogMatch")	                    
	                    .build()
	                    .verify(tokenJWT)
	                    .getSubject();
	        } catch (JWTVerificationException exception) {
	            throw new ValidationException("Token JWT inválido ou expirado!");
	        }
	    }
	    
	    public Long getTutorId(String tokenJWT) {
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            DecodedJWT decodedJWT = JWT.require(algorithm)
	                    .withIssuer("DogMatch")
	                    .build()
	                    .verify(tokenJWT);
	            return decodedJWT.getClaim("idUsuario").asLong();
	        } catch (JWTVerificationException exception) {
	            throw new ValidationException("Token JWT inválido ou expirado!");
	        }
	    }

}
