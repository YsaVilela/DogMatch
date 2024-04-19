package br.com.dogmatch.apiprincipal.Service.validacoes.tutor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.infra.Exception.InvalidDataException;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;

@Component
public class ValidadorExisteCpf implements ValidadorTutor {

	@Autowired
	private TutorRepository tutorRepository;

	@Override
	public void validar(String cpf, String email, String dataDeNascimento, Long id) {
		Optional<Tutor> buscaPorCpf;

		if (id != null) {
			buscaPorCpf = tutorRepository.findByCpfExcludingId(cpf, id);
		} else {
			buscaPorCpf = tutorRepository.findByCpf(cpf);
		}

		if (!buscaPorCpf.isEmpty()) {
			throw new InvalidDataException("Cpf já cadastrado.");
		}

	}

}
