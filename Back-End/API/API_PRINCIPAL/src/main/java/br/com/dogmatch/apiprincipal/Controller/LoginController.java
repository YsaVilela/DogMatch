package br.com.dogmatch.apiprincipal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiprincipal.DTO.Login.DadosLogin;
import br.com.dogmatch.apiprincipal.Entity.Usuario;
import br.com.dogmatch.apiprincipal.infra.Exception.ValidationException;
import br.com.dogmatch.apiprincipal.infra.security.DadosTokenJWT;
import br.com.dogmatch.apiprincipal.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
   
	@PostMapping
	public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosLogin dados) {
		try {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.status(HttpStatus.OK).body(new DadosTokenJWT(tokenJWT));
        } catch (AuthenticationException ex) {
            throw new ValidationException("Usuário e/ou senha incorretos.");
        }
	}
}
