package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;



public interface UserService {

	public User registerUser(User user) throws UserAlreadyExistsException;
	public User validateUser(String userName, String password) throws UserNotFoundException;
}
