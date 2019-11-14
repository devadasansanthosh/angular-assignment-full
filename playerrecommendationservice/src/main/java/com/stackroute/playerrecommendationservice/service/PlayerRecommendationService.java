package com.stackroute.playerrecommendationservice.service;

import java.util.List;

import com.stackroute.playerrecommendationservice.model.Player;

public interface PlayerRecommendationService {
	
public List<Player> getAllUserPlayerList() throws Exception;
	
	public Player savePlayer(Player player) throws Exception;
	
	public Player deletePlayer(Player player) throws Exception;

}
