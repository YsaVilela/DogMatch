package br.com.dogmatch.apiprincipal.Service.validacoes.tutor;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosTutor;
import br.com.dogmatch.apiprincipal.infra.Exception.InvalidDataException;

@Component
public class ValidadorIdade implements ValidadorTutor{

	@Override
	public void validar(DadosTutor tutor) {
		LocalDate dataAtual = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimento = LocalDate.parse(tutor.dataDeNascimento(), formatter);

		Period periodo = Period.between(dataNascimento, dataAtual);
		int diferencaEmAnos = periodo.getYears();

		if (diferencaEmAnos < 18) {
			throw new InvalidDataException ("Deve ter mais de 18 anos");
		}
	}

}
