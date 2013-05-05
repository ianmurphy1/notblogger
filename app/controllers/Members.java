package controllers;

import play.*;
import java.util.List;
import java.util.Set;
import play.db.jpa.Model;
import models.*;
import play.mvc.Controller;

public class Members extends Controller {
	
	public static void index() {
		User user = Start.getLoggedInUser();
		
		List<User> users = User.findAll();
		users.remove(user);			
		
		Set<User> subscribingTo = user.subscribing;
		
		for (User checkUser: subscribingTo) {
			users.remove(checkUser);
		} 
		
		render(user, users);
	}
	
	public static void subscribe(Long id) {
		User user = Start.getLoggedInUser();
		User userToFollow = User.findById(id);
		
		user.subscribing.add(userToFollow);
		user.save();
		
		index();
	}
}
