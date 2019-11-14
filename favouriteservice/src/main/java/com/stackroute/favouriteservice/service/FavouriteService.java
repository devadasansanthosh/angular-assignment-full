package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.model.Player;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;



public interface FavouriteService {

	public User savePlayerToFavorite(Player player, String username) throws PlayerAlreadyExistsException;
	public User deletePlayerFromFavorite(String trackId , String username ) throws PlayerNotFoundException;

	public List<Player> getPlayerList(String username ) throws Exception;
	

}
