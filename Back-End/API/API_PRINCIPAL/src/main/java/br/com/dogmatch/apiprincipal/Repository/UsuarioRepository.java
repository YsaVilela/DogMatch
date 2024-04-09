package br.com.dogmatch.apiprincipal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.dogmatch.apiprincipal.Entity.Usuario;
import jakarta.transaction.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_USUARIO; ALTER SEQUENCE TB_USUARIO_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	UserDetails findByLogin(String login);
	
}
