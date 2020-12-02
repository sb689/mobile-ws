package com.example.mobile.ws.ui.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplde.mobile.ws.ui.model.response.UserRest;


@RestController
@RequestMapping("users")

public class UserController {

	@GetMapping(path ="/{userId}",
	produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	
	public UserRest getUser(@PathVariable String userId)
	{
		
		UserRest test = new UserRest();
		test.setFirstName("Ican");
		test.setLastName("Iwill");
		test.setEmail("Ican@iwill.com");
		test.setUserId("21");
		
		return test;
	}
	
	@GetMapping()
	public String getUser(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit,
			@RequestParam(value="sort", required = false, defaultValue="asc") String sort)
	{
		return "getUser was called with page = "+ page +
				", and limit = "+ limit + ", sort = " + sort;
	}
	
	@PostMapping
	public String createUser()
	{
		return "createUser was called";
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
