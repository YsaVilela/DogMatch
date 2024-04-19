package br.com.dogmatch.apiprincipal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Foto;
import jakarta.transaction.Transactional;

public interface FotoRepository extends JpaRepository<Foto, Long> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_FOTO; ALTER SEQUENCE TB_FOTO_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	List<Foto> findByPetId(Long idPet);
}
