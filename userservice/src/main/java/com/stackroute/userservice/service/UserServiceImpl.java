package com.stackroute.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User registerUser(User user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub

		User userexisting = userRepository.findByUsername(user.getUsername());

		if (userexisting != null) {
			throw new UserAlreadyExistsException();
		}

		return userRepository.save(user);
	}

	@Override
	public User validateUser(String userName, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsernameAndPassword(userName, password);

		if (user == null) {
			throw new UserNotFoundException();
		}

		return user;
	}

}
