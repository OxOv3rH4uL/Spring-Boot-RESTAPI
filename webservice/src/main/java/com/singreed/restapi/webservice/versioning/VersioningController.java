package com.singreed.restapi.webservice.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	@GetMapping(path="/v1/person")
	public Person1 person1() {
		return new Person1("Pavan Kalyan");
	}
	
	@GetMapping(path="/v2/person")
	public Person2 person2() {
		return new Person2(new Name("Pavan", "Kalyan"));
	}
}
