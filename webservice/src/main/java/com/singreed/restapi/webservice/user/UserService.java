package com.singreed.restapi.webservice.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import java.util.function.Predicate;


@Component
public class UserService {
	
	
	static List<User> users = new ArrayList<>();
	static int idCount = 0;
	static {
		users.add(new User(++idCount, "Pavan", LocalDate.now().minusYears(20)));
		users.add(new User(++idCount, "SINGREED", LocalDate.now().minusYears(19)));
		users.add(new User(++idCount, "0rezer0", LocalDate.now().minusYears(21)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public List<User> findOne(int id){
		for(int i = 0 ; i < users.size() ; i++) {
			if(users.get(i).id == id) {
				return Arrays.asList(users.get(i));
				
			}
		}
		return Arrays.asList(new User(0, "", LocalDate.now()));
		
	}
	
	public User create(User user) {
		user.setId(++idCount);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id) {
		int index = 0;
		for(int i = 0 ; i < users.size() ; i++) {
			if(users.get(i).id == id) {
				index = i;
			}
		}
		users.remove(index);
	}

}
