package br.com.dogmatch.apiprincipal.infra.Exception.tratamento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.dogmatch.apiprincipal.infra.Exception.CustomDataIntegrityException;
import br.com.dogmatch.apiprincipal.infra.Exception.InvalidDataException;
import br.com.dogmatch.apiprincipal.infra.Exception.ValidationException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(CustomDataIntegrityException.class)
	public ResponseEntity<MensagemErro> tratarBuscar(CustomDataIntegrityException ex) {
		MensagemErro mensagemErro = new MensagemErro(ex.getMessage());
		return ResponseEntity.badRequest().body(mensagemErro);
	}

    @ExceptionHandler(InvalidDataException.class) 
    public ResponseEntity<MensagemErro> tratarDadosInvalidos(InvalidDataException ex) {
		MensagemErro mensagemErro = new MensagemErro(ex.getMessage());
        return ResponseEntity.badRequest().body(mensagemErro);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DadosErroValidacao> tratarErro400(MethodArgumentNotValidException ex) {
        var primeiroErro = ex.getFieldErrors().stream()
                .findFirst() 
                .map(DadosErroValidacao::new) 
                .orElse(new DadosErroValidacao("Erro de validação não especificado", null)); 
        return ResponseEntity.badRequest().body(primeiroErro);
    }
    
    public record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        } 
    }
        
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<MensagemErro> tratarValidacao(ValidationException ex) {
		MensagemErro mensagemErro = new MensagemErro(ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mensagemErro);
	}

	public record MensagemErro(String mensagem) {
		public MensagemErro(FieldError erro) {
			this(erro.getDefaultMessage());
		}
	}
    
    

}
