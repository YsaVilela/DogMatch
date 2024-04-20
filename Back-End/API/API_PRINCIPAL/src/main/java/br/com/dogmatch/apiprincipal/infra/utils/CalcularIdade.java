package br.com.dogmatch.apiprincipal.infra.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class CalcularIdade {

	public int calcularIdade(String dataDeNascimento) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dataNascimento = LocalDate.parse(dataDeNascimento, formatter);

		LocalDate dataAtual = LocalDate.now();

		Period periodo = Period.between(dataNascimento, dataAtual);

		return periodo.getYears();
	}
}
