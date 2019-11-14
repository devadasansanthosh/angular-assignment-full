package com.stackroute.favouriteservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.model.Player;
import com.stackroute.favouriteservice.model.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.service.FavouriteService;

@WebMvcTest(FavoriteController.class)
@RunWith(SpringRunner.class)
public class FavoriteConrollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FavouriteService favService;

	@InjectMocks
	private FavoriteController favoriteController;

	private User user;

	private Player cPlayer;

	private List list;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(favoriteController).build();
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
	public void testAddPlayerToFavoriteList() throws Exception {
		when(favService.savePlayerToFavorite(any(), eq(user.getUsername()))).thenReturn(user);
		mockMvc.perform(post("/api/v1/favoriteservice/user/{username}/player", user.getUsername())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(cPlayer)))
				.andExpect(status().isCreated()).andDo(print());

		verify(favService, times(1)).savePlayerToFavorite(any(), eq(user.getUsername()));
	}

	@Test
	public void testAddPlayerToFavoriteListFailure() throws Exception {
		when(favService.savePlayerToFavorite(any(), eq(user.getUsername())))
				.thenThrow(PlayerAlreadyExistsException.class);
		mockMvc.perform(post("/api/v1/favoriteservice/user/{username}/player", user.getUsername())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(cPlayer)))
				.andExpect(status().isConflict()).andDo(print());

		verify(favService, times(1)).savePlayerToFavorite(any(), eq(user.getUsername()));

	}

	@Test
	public void testGetAllTrackFromWishList() throws Exception {
		when(favService.getPlayerList((user.getUsername()))).thenReturn(list);
		mockMvc.perform(get("/api/v1/favoriteservice/user/{username}/player", user.getUsername())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(cPlayer))).andExpect(status().isOk())
				.andDo(print());

		verify(favService, times(1)).getPlayerList(user.getUsername());

	}

	private static String jsonToString(final Object obj) throws Exception {
		String result;

		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (Exception e) {
			result = "error in Json processing";
		}
		return result;
	}

}
