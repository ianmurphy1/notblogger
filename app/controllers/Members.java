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
	
	public static void follow(Long id) {
		User user = Start.getLoggedInUser();
		User userToFollow = User.findById(id);
		
		user.following.add(userToFollow);
		user.save();
		
		Home.index();
	}
}
