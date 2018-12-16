package com.InventoryControl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Transactional(readOnly=true)
	Usuario findByEmail(String email);
	
	@Transactional(readOnly=true)
	Usuario findByMatricula(String matricula);
	
	@Query("SELECT DISTINCT obj FROM Usuario obj INNER JOIN obj.site st WHERE st.codigo = :siteCod")
	public List<Usuario> findUsuariosSites(@Param("siteCod") Integer siteCod);
}
