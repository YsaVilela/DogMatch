package br.com.dogmatch.apiprincipal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.dogmatch.apiprincipal.Entity.Tutor;
import jakarta.transaction.Transactional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_TUTOR; ALTER SEQUENCE TB_TUTOR_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	Optional<Tutor> findByCpf(String cpf);

	@Query("SELECT t FROM Tutor t WHERE t.cpf = :cpf AND t.id <> :excludedId")
	Optional<Tutor> findByCpfExcludingId(@Param("cpf") String cpf, @Param("excludedId") Long excludedId);

	Optional<Tutor> findByEmail(String email);

	@Query("SELECT t FROM Tutor t WHERE t.email = :email AND t.id <> :excludedId")
	Optional<Tutor> findByEmailExcludingId(@Param("email") String email, @Param("excludedId") Long id);
}
