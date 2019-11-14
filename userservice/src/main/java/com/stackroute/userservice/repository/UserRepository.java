package com.stackroute.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stackroute.userservice.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("delete from User u where u.username = :name ")
	int deletebyname(@Param("name") String name );

}

