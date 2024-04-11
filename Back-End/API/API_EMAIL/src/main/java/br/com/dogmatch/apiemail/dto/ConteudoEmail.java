package br.com.dogmatch.apiemail.dto;

public record ConteudoEmail(
		String to,
		String subject,
		String body) {
}
