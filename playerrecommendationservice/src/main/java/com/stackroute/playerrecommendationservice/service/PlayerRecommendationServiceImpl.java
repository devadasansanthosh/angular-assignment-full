package com.stackroute.playerrecommendationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.playerrecommendationservice.model.Player;
import com.stackroute.playerrecommendationservice.repository.PlayerRecommendationRepository;

@Service
public class PlayerRecommendationServiceImpl implements PlayerRecommendationService {
	
private PlayerRecommendationRepository playerRecommendationRepository;
	
	@Autowired
	public PlayerRecommendationServiceImpl(PlayerRecommendationRepository playerRecommendationRepository ) {
		super();
		this.playerRecommendationRepository = playerRecommendationRepository;
	
	}
	
	@Override
	public List<Player> getAllUserPlayerList() throws Exception {
		List<Player> playerList = playerRecommendationRepository.findAll();
		return playerList;
	}
	
	@Override
	public Player savePlayer(Player player)  throws Exception {
		int totalRecommendation= 0;
		Player p = playerRecommendationRepository.findBypid(player.getPid());
		
		if(p!=null) {
			totalRecommendation = p.getTotalcount() + 1 ; 
			playerRecommendationRepository.updatePlayerCount(p.getPid() , totalRecommendation);
			return p;
		}
		
		else {
			totalRecommendation = 1;
			player.setTotalcount(totalRecommendation);
			return playerRecommendationRepository.save(player);
			
		}
		
	}
	
	@Override
	public Player deletePlayer(Player player) throws Exception {
		// TODO Auto-generated method stub
		int totalRecommendation= 0;
		Player p = playerRecommendationRepository.findBypid(player.getPid());
		
		if(p!=null) {
			totalRecommendation = p.getTotalcount() - 1 ; 
			playerRecommendationRepository.updatePlayerCount(p.getPid() , totalRecommendation);
		}
		
		return p;
		
	}
	

}
