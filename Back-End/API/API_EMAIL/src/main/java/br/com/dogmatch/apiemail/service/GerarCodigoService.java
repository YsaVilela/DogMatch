package br.com.dogmatch.apiemail.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GerarCodigoService {

	int GerarCodigo() {
		Random random = new Random();
		int codigo = random.nextInt(9000) + 1000;
		return codigo;
	}

}
