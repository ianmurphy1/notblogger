package controllers;

import java.util.List;

import models.User;
import play.mvc.Controller;

public class Members extends Controller {
	
	public static void index() {
		User user = Start.getLoggedInUser();
		
		List<User> users = User.findAll();
		users.remove(user);
		
		render(users);
	}
	
	public static void subscribe(Long id) {
		User user = Start.getLoggedInUser();
		User userToFollow = User.findById(id);
		
		user.subscribing.add(userToFollow);
		user.save();
		
		index();
	}
}
