package br.com.dogmatch.apiprincipal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Pet;
import jakarta.transaction.Transactional;

public interface PetRepository extends JpaRepository<Pet,Long>{
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_PET; ALTER SEQUENCE TB_PET_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();
}
