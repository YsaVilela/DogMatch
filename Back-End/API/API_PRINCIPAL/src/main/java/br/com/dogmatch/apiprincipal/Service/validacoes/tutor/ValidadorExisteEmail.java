package br.com.dogmatch.apiprincipal.Service.validacoes.tutor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosTutor;
import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.infra.Exception.InvalidDataException;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;

@Component
public class ValidadorExisteEmail implements ValidadorTutor{

	@Autowired
	private TutorRepository tutorRepository;
	
	@Override
	public void validar(DadosTutor tutor) {
		Optional<Tutor> buscaPorEmail = tutorRepository.findByEmail(tutor.email());
		if(!buscaPorEmail.isEmpty()) {
			throw new InvalidDataException ("Email já cadastrado.");
		}
	}

}
