package controllers;

import java.util.List;
import java.util.Set;
import play.db.jpa.Model;

import play.*;

import models.*;
import play.Logger;
import play.mvc.Controller;

public class Subscriptions extends Controller {
	
	public static void index() {
		User user = Start.getLoggedInUser();	
			
		render(user);
	}
	
	public static void unsubscribe(Long id) {
		User user = Start.getLoggedInUser();
		
		User userToDrop = User.findById(id);
	    user.subscribing.remove(userToDrop);
	    user.save();
	    
	    Logger.info("Dropping " + userToDrop.email);
	    index();
	}
}
