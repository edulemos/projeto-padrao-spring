package com.spring.baseproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.baseproject.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>  {
    
	User findUserByEmail(String email);

    @Query("Select u from User u where upper(u.name) like concat('%',upper(:name),'%')  or upper(u.email) like concat('%',upper(:email),'%') ")
	List<User> findUsers(@Param("name") String name, @Param("email")String email);
	
}
