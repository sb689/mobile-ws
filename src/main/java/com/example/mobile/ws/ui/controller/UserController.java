package com.example.mobile.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplde.mobile.ws.ui.model.request.UpdateUserDetailRequestModel;
import com.examplde.mobile.ws.ui.model.request.UserDetailRequestModel;
import com.examplde.mobile.ws.ui.model.response.UserRest;



@RestController
@RequestMapping("users")

public class UserController {
	
	
	Map<String,UserRest> userMap = new HashMap<>(); 

	@GetMapping(path ="/{userId}",
	produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	
	
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
	{
		if(userMap.containsKey(userId))
		{
			return new ResponseEntity<>(userMap.get(userId), HttpStatus.OK);
		}else {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping()
	public String getUser(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit,
			@RequestParam(value="sort", required = false, defaultValue="asc") String sort)
	{
		return "getUser was called with page = "+ page +
				", and limit = "+ limit + ", sort = " + sort;
	}
	
	@PostMapping( produces= {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE},
		consumes= {MediaType.APPLICATION_JSON_VALUE, 
				MediaType.APPLICATION_XML_VALUE})
	
	
	
	public ResponseEntity<UserRest> createUser(
			@Valid @RequestBody UserDetailRequestModel userDetails)
	{
		UserRest test = new UserRest();
		test.setFirstName(userDetails.getFirstName());
		test.setLastName(userDetails.getLastName());
		test.setEmail(userDetails.getEmail());
		
		String userId = UUID.randomUUID().toString();
		test.setUserId(userId);
		userMap.put(userId, test);
		
		return new ResponseEntity<UserRest>(test, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}",
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	
	public UserRest updateUser(@PathVariable String userId,
			@Valid @RequestBody UpdateUserDetailRequestModel userDetails)
	{

			UserRest user = userMap.get(userId);
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			userMap.put(userId, user);
			return user;

	}
	
	@DeleteMapping
	public String deleteUser()
	{
		return "deleteUser was called";
	}
	
	
}
