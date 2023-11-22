package com.singreed.restapi.webservice.user;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.singreed.restapi.webservice.exception.UserNotFoundException;

import jakarta.validation.Valid;

//@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	
	@GetMapping(path="/users")
	public List<User> getAllusers(){
		return service.findAll();
		
	}
	
	@GetMapping(path = "/secretkey")
	public String secret() {
		return "Secret Key for the day is:- DUMBASS";
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id){
		User foundUser = service.findOne(id).get(0);
		if(foundUser.getId() == 0) {
			throw new UserNotFoundException("ID:- " + id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(foundUser);
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).getAllusers());
		entityModel.add(link1.withRel("all-users")); 
		
		return entityModel;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<User> create(@Valid @RequestBody User user){
		User createdUser = service.create(user);
		
		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		//Athavathu ServletRequestComponentBuilder takes current request la vara ah , createdUser odaya id ah attach pani oru URI ah maathi antha location ku redirect pana porom
		
		return ResponseEntity.created(locationUri).build();
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
		
	}
}
