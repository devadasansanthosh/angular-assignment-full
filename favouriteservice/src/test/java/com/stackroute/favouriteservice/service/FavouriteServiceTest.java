package com.stackroute.favouriteservice.service;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.model.Player;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.FavouriteRepository;

import junit.framework.Assert;

public class FavouriteServiceTest {

	@Mock
	private FavouriteRepository favRepository;

	@InjectMocks
	public FavouriteServiceImpl favouriteServiceImpl;

	private User user;

	private Player cPlayer;

	private List list;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		cPlayer = new Player();
		cPlayer.setFullName("sachin tendulkar");
		cPlayer.setName("sachin");
		cPlayer.setPid("99");
		list = new ArrayList();
		list.add(cPlayer);

		user = new User();
		user.setUsername("santhosh");
		user.setPlayerList(list);
	}

	@After
	public void delete() {
		user = null;
	}

	@Test
	public void savePlayerToFavoriteListTest() throws PlayerAlreadyExistsException {

		user = new User();
		user.setUsername("santhosh");
		
		Mockito.when(favRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = favouriteServiceImpl.savePlayerToFavorite(cPlayer, user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(favRepository, timeout(1)).findByUsername(user.getUsername());
		verify(favRepository, times(1)).save(user);
	}

	@Test
	public void deletePlayerFromFavoriteListTest() throws  PlayerNotFoundException {
		Mockito.when(favRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = favouriteServiceImpl.deletePlayerFromFavorite(cPlayer.getPid(),user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(favRepository, timeout(1)).findByUsername(user.getUsername());
		verify(favRepository, times(1)).save(user);

	}
	
	@Test
	public void testgetAllFavoriteList() throws Exception {
		Mockito.when(favRepository.findByUsername(user.getUsername())).thenReturn(user);
		List fetchedList = favouriteServiceImpl.getPlayerList(user.getUsername());
		Assert.assertEquals(fetchedList, list);
		verify(favRepository, times(1)).findByUsername(user.getUsername());

	}

}
