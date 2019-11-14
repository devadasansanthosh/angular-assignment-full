package com.stackroute.playerrecommendationservice.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stackroute.playerrecommendationservice.model.Player;

public interface PlayerRecommendationRepository extends JpaRepository<Player, String>{
	public Player findBypid(String pid);	
	public Player findByname(String name);	
	
	@Transactional
	@Modifying
	@Query("UPDATE Player p SET p.totalcount = :totalcount  WHERE p.pid = :pid")
	int updatePlayerCount(@Param("pid") String pid ,@Param("totalcount") int totalcount);

}
