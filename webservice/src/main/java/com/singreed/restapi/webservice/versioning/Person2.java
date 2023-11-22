package com.singreed.restapi.webservice.versioning;


public class Person2 {
	
	Name name;

	@Override
	public String toString() {
		return "Person2 [name=" + name + "]";
	}
	
	

	public Name getName() {
		return name;
	}



	public void setName(Name name) {
		this.name = name;
	}



	public Person2(Name name) {
		super();
		this.name = name;
	}
	
	

}
