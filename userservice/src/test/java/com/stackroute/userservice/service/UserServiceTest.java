package com.stackroute.userservice.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

import com.stackroute.userservice.repository.UserRepository;

import junit.framework.Assert;

public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	public UserServiceImpl userServiceImpl;

	private com.stackroute.userservice.model.User user;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUsername("Santhosh");
		user.setPassword("welcome");
		user.setEmail("santhosh@ibm.in");
	}

	@After
	public void delete() {
		user = null;
	}
	
	@Test
	public void registerUser() throws UserAlreadyExistsException {
		
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User fetchedUser = userServiceImpl.registerUser(user);
		Assert.assertEquals(fetchedUser, user);
		verify(userRepository, times(1)).save(user);
	}
	
	@Test
	public void validateUser() throws UserNotFoundException {
		Mockito.when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		User fetchedUser = userServiceImpl.validateUser(user.getUsername(), user.getPassword());
		Assert.assertEquals(fetchedUser, user);
		verify(userRepository, times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
