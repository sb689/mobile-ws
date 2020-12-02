package com.example.mobile.ws.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplde.mobile.ws.ui.model.request.UserDetailRequestModel;
import com.examplde.mobile.ws.ui.model.response.UserRest;



@RestController
@RequestMapping("users")

public class UserController {

	@GetMapping(path ="/{userId}",
	produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
	{
		
		UserRest test = new UserRest();
		test.setFirstName("Ican");
		test.setLastName("Iwill");
		test.setEmail("Ican@iwill.com");
		
		return new ResponseEntity<UserRest>(test, HttpStatus.OK);
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
		
		return new ResponseEntity<UserRest>(test, HttpStatus.OK);
	}
	
	@PutMapping
	public String updateUser()
	{
		return "updateUser was called";
	}
	
	@DeleteMapping
	public String deleteUser()
	{
		return "deleteUser was called";
	}
	
	
}
