package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Start extends Controller {
	public static void index() {		
		render();		
	}
	
	public static void login() {
		render();
	}
	
	public static void signup() {
		render();
	}

	public static User getLoggedInUser() {
		User user = null;
	    if (session.contains("logged_in_userid"))
	    {
	      String userId = session.get("logged_in_userid");
	      user = User.findById(Long.parseLong(userId));
	    }
	    else
	    {
	      login();
	    }
	    return user;
	  }
	
}
