package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.model.Player;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistsException;
import com.stackroute.favouriteservice.service.FavouriteService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/favoriteservice")
public class FavoriteController {

	private ResponseEntity responseEntity;

	private FavouriteService favService;

	public FavoriteController() {
		super();
	}

	@Autowired
	public FavoriteController(FavouriteService favService) {
		super();
		this.favService = favService;
	}

	@PostMapping("/user/{username}/player")
	public ResponseEntity<?> addPlayerToFavoriteList(@RequestBody Player cPlayer,
			@PathVariable String username) throws PlayerAlreadyExistsException {

		try {
			User user1 = favService.savePlayerToFavorite(cPlayer, username);
			responseEntity = new ResponseEntity<User>(user1, HttpStatus.CREATED);
		} catch (PlayerAlreadyExistsException e) {
			throw new PlayerAlreadyExistsException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}

	@DeleteMapping("/user/{username}/player")
	public ResponseEntity<?> deleteFromList(@RequestBody Player cPlayer, @PathVariable String username)
			throws PlayerNotFoundException {

		try {
			User user1 = favService.deletePlayerFromFavorite(cPlayer.getPid(), username);
			responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
		} catch (PlayerNotFoundException e) {
			throw new PlayerNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}
	
	@GetMapping("/user/{username}/player")
	public ResponseEntity<?> getPlayerList(@PathVariable String username) throws PlayerNotFoundException {

		try {
			List<Player> playerList = favService.getPlayerList(username);
			responseEntity = new ResponseEntity(playerList, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}


}
