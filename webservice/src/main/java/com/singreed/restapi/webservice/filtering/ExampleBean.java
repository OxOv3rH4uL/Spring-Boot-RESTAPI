package com.singreed.restapi.webservice.filtering;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("value1")
public class ExampleBean {
	String value1;
	String value2;
	
	
	public ExampleBean(String value1, String value2) {
		super();
		this.value1 = value1;
		this.value2 = value2;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	@Override
	public String toString() {
		return "ExampleBean [value1=" + value1 + ", value2=" + value2 + "]";
	}
	
}
