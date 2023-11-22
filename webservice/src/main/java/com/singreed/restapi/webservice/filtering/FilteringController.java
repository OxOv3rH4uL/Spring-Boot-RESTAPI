package com.singreed.restapi.webservice.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping(path="/fields")
	public ExampleBean fields() {
		return new ExampleBean("kakakakakakakka","Value 1 is ignored bruhh");
	}
}
