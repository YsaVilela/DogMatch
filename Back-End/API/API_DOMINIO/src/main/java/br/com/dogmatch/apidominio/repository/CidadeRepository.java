package br.com.dogmatch.apidominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.dogmatch.apidominio.DTO.DadosCidade;
import br.com.dogmatch.apidominio.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_CIDADE; ALTER SEQUENCE TB_CIDADE_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();
	
	@Query("""
			select c from Cidade c
			join c.estado e
    	    where lower(e.uf) = lower(:siglaEstado)
			""")
	List<DadosCidade> findByNomeEstado(String siglaEstado);
}
