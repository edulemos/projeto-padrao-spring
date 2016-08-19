package com.spring.baseproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.baseproject.entity.Usuario;

public interface UserRepository  extends JpaRepository<Usuario, Long>  {
    
	Usuario findUsuarioByEmail(String email);

    @Query("Select u from Usuario u where upper(u.name) like concat('%',upper(:name),'%')  or upper(u.email) like concat('%',upper(:email),'%') ")
	List<Usuario> findUsers(@Param("name") String name, @Param("email")String email);
	
}
