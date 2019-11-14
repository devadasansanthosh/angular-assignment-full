package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.model.Player;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {

	private FavouriteRepository favRepository;
	
	@Autowired
	public FavouriteServiceImpl(FavouriteRepository favRepository) {
		super();
		this.favRepository = favRepository;
	}

	@Override
	public User savePlayerToFavorite(Player player, String username) throws PlayerAlreadyExistsException {
		// TODO Auto-generated method stub
		User user1 = favRepository.findByUsername(username);

		if (user1 == null) {
			user1 = new User(username, new ArrayList<Player>());
		}
		
		List<Player> playerList = user1.getPlayerList();

		if (playerList != null) {
			for (Player p : playerList) {

				if (p.getPid().equals(player.getPid())) {
					throw new PlayerAlreadyExistsException();
				}
			}

			playerList.add(player);
			
			System.out.println("Saving Data if block");
			user1.setPlayerList(playerList);
			favRepository.save(user1);
		}

		else {
			playerList = new ArrayList();
			playerList.add(player);

			user1.setPlayerList(playerList);
			favRepository.save(user1);
		}
		return user1;

	}

	@Override
	public User deletePlayerFromFavorite(String pId, String username) throws PlayerNotFoundException {
		User user1 = favRepository.findByUsername(username);
		boolean trackFound = false;
		int indexnum = 0;
		List<Player> playerList = user1.getPlayerList();

		if (playerList != null && playerList.size() > 0) {
			for (Player t : playerList) {
				indexnum++;
				if (t.getPid().equals(pId)) {
					playerList.remove(indexnum - 1);
					user1.setPlayerList(playerList);
					favRepository.save(user1);
					break;
				}
			}

		}

		else {
			throw new PlayerNotFoundException();
		}
		return user1;
	}

	@Override
	public List<Player> getPlayerList(String username) throws Exception {
		User user1 = favRepository.findByUsername(username);
		return user1.getPlayerList();
	}


}
