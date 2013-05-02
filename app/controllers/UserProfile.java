package controllers;

import models.User;
import play.Logger;
import play.mvc.Controller;

public class UserProfile extends Controller {
	
	public static void visit(Long id) {
		
		User currentUser = Start.getLoggedInUser();
		
		User user = User.findById(id);
		Logger.info("Visiting " + user.firstName + " " + user.lastName + "'s Profile Page.");
		
		render(currentUser, user);
	}
	
	public static void follow(Long id) {
		User currentUser = Start.getLoggedInUser();
		User userToFollow = User.findById(id);
		
		currentUser.following.add(userToFollow);
		currentUser.save();
		
		visit(id);
	}
}
