package com.stackroute.userservice.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.model.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void setup() {
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
	public void registerUser() {
		userRepository.deletebyname(user.getUsername());
		
		userRepository.save(user);
		User userObj = userRepository.findByUsername(user.getUsername());
		Assert.assertEquals(userObj.getUsername(), user.getUsername());
		userRepository.deletebyname(user.getUsername());
		
	}
	
	@Test
	public void validateUser() {
		userRepository.deletebyname(user.getUsername());
		
		userRepository.save(user);
		User userObj = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		Assert.assertEquals(userObj.getUsername(), user.getUsername());
		userRepository.deletebyname(user.getUsername());
		
	}
}
