package com.spring.baseproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.baseproject.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	@Query("Select p from Perfil p where upper(p.nome) like concat('%',upper(:nome),'%')")
	List<Perfil> pesquisar(@Param("nome") String nome);

	Perfil findPerfilByNome(String nome);

	@Query(value = "select count(*) from tb_usuario_perfil where perfil_id =  ?1", nativeQuery = true)
	Integer buscarAssociacoesPerfil(Long id);

}
