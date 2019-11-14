package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/userservice")
public class UserController {

	private ResponseEntity<?> responseEntity;
	Map<String,String> map = new HashMap<>();


    private UserService userService;

	public UserController() {
		super();
	}

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
		
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {

		try {
			User user1 = userService.registerUser(user);
			responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			throw new UserAlreadyExistsException();
		}
		return responseEntity;

	}

	@RequestMapping(method=RequestMethod.POST, path="/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
		
		Map<String, String> 	map =new HashMap<String,String>();
		String jwtToken = "";
		try {
			User user1 = userService.validateUser(user.getUsername(), user.getPassword());
			System.out.println("Printing -------------------------------->"+user1.getUsername());
			if(user1.getUsername().equals(user.getUsername())) {
				jwtToken = 	getToken(user.getUsername(),user.getPassword());
				//map.clear();
				map.put("message", "User Successfully Logged In");
				map.put("token", jwtToken);

			}
			responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new UserNotFoundException();
		} catch (Exception e) {
			e.printStackTrace();
			//map.clear();
			//map.put("message", e.getMessage());
			//map.put("token", null);
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		}
		
		return responseEntity;
	}

	public String getToken(String username, String password) throws Exception {
		if(username == null || password == null) {
			throw new ServletException("Please provide proper username and password");
		}
		
				
	   String jwtToken = 	Jwts.builder()
		    .setSubject(username)
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis() + 30000 ))
		    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		
		return jwtToken;

        
        
}



}
