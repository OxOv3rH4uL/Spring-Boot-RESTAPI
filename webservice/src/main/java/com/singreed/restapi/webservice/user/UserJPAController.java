package com.singreed.restapi.webservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.singreed.restapi.webservice.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserJPAController {
	
	UserJPARepository repository;
	PostJPARepository post;

	public UserJPAController(UserJPARepository repository, PostJPARepository post) {
		super();
		this.repository = repository;
		this.post = post;
	}
	
	@GetMapping(path="/users")
	public List<User> getAllusers(){
		return repository.findAll();
		
	}
	
	@GetMapping(path = "/secretkey")
	public String secret() {
		return "Secret Key for the day is:- DUMBASS";
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<Optional<User>> getUser(@PathVariable int id){
		Optional<User> foundUser = repository.findById(id);
		
		
		EntityModel<Optional<User>> entityModel = EntityModel.of(foundUser);
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).getAllusers());
		entityModel.add(link1.withRel("all-users")); 
		
		return entityModel;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<User> create(@Valid @RequestBody User user){
		User createdUser = repository.save(user);
		
		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		//Athavathu ServletRequestComponentBuilder takes current request la vara ah , createdUser odaya id ah attach pani oru URI ah maathi antha location ku redirect pana porom
		
		return ResponseEntity.created(locationUri).build();
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
		
	}
	
	//POST CONTROLLER
	
	@GetMapping(path = "/users/{id}/posts")
	public List<Post> retrievePostUser(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("ID: " + id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id , @Valid @RequestBody Post posts){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("ID:- " + id);
		}
		posts.setUser(user.get());
		Post createPost = post.save(posts);
		
		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createPost.getId()).toUri();
		//Athavathu ServletRequestComponentBuilder takes current request la vara ah , createdUser odaya id ah attach pani oru URI ah maathi antha location ku redirect pana porom
		
		return ResponseEntity.created(locationUri).build();


	}
	
	@GetMapping(path = "/users/{id1}/posts/{id2}")
	public Post getPostDetails(@PathVariable int id1 , @PathVariable int id2) {
		Optional<User> user = repository.findById(id1);
		if(user.isEmpty()) {
			throw new UserNotFoundException("ID:- " + id1);
		}
		Optional<Post> postToGet = post.findById(id2);
		return postToGet.get();
	}
	
	@DeleteMapping(path="/users/{id1}/posts/{id2}")
	public void deleteUser(@PathVariable int id1 , @PathVariable int id2) {
		Optional<User> user = repository.findById(id1);
		if(user.isEmpty()) {
			throw new UserNotFoundException("ID:- " + id1);
		}
		Optional<Post> postToGet = post.findById(id2);
		if(postToGet.isEmpty()) {
			throw new UserNotFoundException("POST NOT FOUND OF ID:- " + id2);
		}
		post.deleteById(id2);
	
	}
	
	@GetMapping(path="users/posts")
	public List<Post> getEveryPosts(){
		return post.findAll();
	}
		
	
	

	
}
