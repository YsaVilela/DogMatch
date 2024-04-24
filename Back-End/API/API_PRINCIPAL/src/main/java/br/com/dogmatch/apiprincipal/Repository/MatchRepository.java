package br.com.dogmatch.apiprincipal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Match;
import jakarta.transaction.Transactional;

public interface MatchRepository extends JpaRepository<Match, Long> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_MATCH; ALTER SEQUENCE TB_MATCH_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	Match findByPet1IdAndPet2Id(Long idPet, Long id);

	@Query("SELECT pr FROM Match pr WHERE (pr.pet1.id = :idPet AND pr.pet2.id = :id) OR (pr.pet2.id = :idPet AND pr.pet1.id = :id)")
	Match findRelatedPets(Long idPet, Long id);
}
