package com.stackroute.userservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	private User user;
	
	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		user = new User();

		user.setUsername("Santhosh");
		user.setPassword("welcome");
		user.setEmail("santhosh@ibm.in");
	}

	
	@Test
	public void testRegisterUser() throws Exception { //
		
		when(userService.registerUser(any())).thenReturn(user);
		mockMvc.perform(
				post("/api/v1/userservice/register",user)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(print());

		verify(userService, times(1)).registerUser(any());

	}
	
	@Test
	public void testRegisterUserFailed() throws Exception  { //
		
		when(userService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
		mockMvc.perform(
				post("/api/v1/userservice/register",user)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(MockMvcResultMatchers.status().isConflict()).andDo(print());

		verify(userService, times(1)).registerUser(any());

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
