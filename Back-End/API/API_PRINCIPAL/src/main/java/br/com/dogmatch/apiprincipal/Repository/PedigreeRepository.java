package br.com.dogmatch.apiprincipal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Pedigree;
import jakarta.transaction.Transactional;

public interface PedigreeRepository extends JpaRepository<Pedigree, Long> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_PEDIGREE; ALTER SEQUENCE TB_PEDIGREE_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	Pedigree findByPetId(Long idPet);
}