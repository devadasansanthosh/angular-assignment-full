package com.stackroute.playerrecommendationservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.playerrecommendationservice.model.Player;
import com.stackroute.playerrecommendationservice.service.PlayerRecommendationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/playerrecommendationservice")
public class PlayerRecommendationController {
	
	private ResponseEntity responseEntity;
	
	private PlayerRecommendationService playerRecommendationService;

	public PlayerRecommendationController() {
		super();
	}

	@Autowired
	public PlayerRecommendationController(PlayerRecommendationService playerRecommendationService) {
		super();
		this.playerRecommendationService = playerRecommendationService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllRecommPlayerList() {

		try {
			List<Player> playerList = playerRecommendationService.getAllUserPlayerList();
			responseEntity = new ResponseEntity(playerList, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}


}
