package br.com.dogmatch.apiemail.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiemail.dto.DadosArmazenar;
import br.com.dogmatch.apiemail.dto.DadosValidacao;
import br.com.dogmatch.apiemail.dto.MensagemDeSucesso;
import br.com.dogmatch.apiemail.entity.Verificacao;
import br.com.dogmatch.apiemail.infra.exception.InvalidDataException;
import br.com.dogmatch.apiemail.repository.VerificacaoRepository;

@Service
public class GerenciarCodigoService {

	@Autowired
	private VerificacaoRepository verificacaoRepository;

	public void armazenarCodigo(DadosArmazenar dados) {
		Optional<Verificacao> verificacaoInvalida = verificacaoRepository.findByEmail(dados.email());
		if (verificacaoInvalida.isPresent()) {
			verificacaoRepository.deleteById(verificacaoInvalida.get().getId());
		}
		
		Verificacao verificacao = new Verificacao();
		verificacao.setEmail(dados.email());
		verificacao.setCodigo(dados.codigo());

		LocalDateTime agora = LocalDateTime.now();
		LocalDateTime dataDeExpiracao = agora.plusMinutes(15);

		verificacao.setdataExpiracao(dataDeExpiracao);
		verificacaoRepository.save(verificacao);
	}

	public MensagemDeSucesso verificarCodigo(DadosValidacao dados) {
		Optional<Verificacao> verificacao = verificacaoRepository.findByEmailAndCodigo(dados.email(), dados.codigo());
		if (verificacao.isEmpty()) {
			throw new InvalidDataException("Código Inválido");
		}
		boolean codigoAtivo = verificacao.get().getDataExpiracao().isAfter(dados.dataDeEnvio());
		
		if (!codigoAtivo) {
			verificacaoRepository.deleteById(verificacao.get().getId());
			throw new InvalidDataException("Código expirado.");
		}
		return new MensagemDeSucesso("Email válidado com sucesso",dados.email());
	}
}
