package br.com.dogmatch.apiprincipal.infra.Exception.tratamento;

import java.util.List;

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
	public ResponseEntity<List<MensagemErro>> tratarBuscar(CustomDataIntegrityException ex) {
		MensagemErro mensagemErro = new MensagemErro(ex.getMessage());
		return ResponseEntity.badRequest().body(List.of(mensagemErro));
	}

    @ExceptionHandler(InvalidDataException.class) 
    public ResponseEntity<List<MensagemErro>> tratarDadosInvalidos(InvalidDataException ex) {
		MensagemErro mensagemErro = new MensagemErro(ex.getMessage());
        return ResponseEntity.badRequest().body(List.of(mensagemErro));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }
    
    public record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        } 
    }
        
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<List<MensagemErro>> tratarValidacao(ValidationException ex) {
		MensagemErro mensagemErro = new MensagemErro(ex.getMessage());
		return ResponseEntity.badRequest().body(List.of(mensagemErro));
	}

	public record MensagemErro(String mensagem) {
		public MensagemErro(FieldError erro) {
			this(erro.getDefaultMessage());
		}
	}
    
    

}
