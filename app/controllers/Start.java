package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Blog;
import models.User;


import play.Logger;
import play.mvc.Controller;

public class Start extends Controller {
	public static void index() {
		
		List<User> users = User.findAll();
				
		List<Blog> publicBlogs = new ArrayList<Blog>();
		
		for (User user: users) {
			
			List<Blog> blogs = user.blogs;			
			for (Blog blog: blogs) {
				if (blog.isPublic) {
					publicBlogs.add(blog);
				}
			}			
		}
		
		//Takes list and shuffles them
		List<Blog> showThese = new ArrayList<Blog>(publicBlogs);
		Collections.shuffle(showThese);
		
		//If the list is bigger than 10 it'll take the first 10 of the list
		if (showThese.size() > 10) {
			showThese = showThese.subList(0, 11);
		}
		
		render(showThese);
	}

	public static void login() {
		render();
	}
	
	public static void logout() {
		session.clear();
	    index();
	}

	public static void signup() {
		render();
	}

	public static User getLoggedInUser() {
		User user = null;
		if (session.contains("logged_in_userid")) {
			String userId = session.get("logged_in_userid");
			user = User.findById(Long.parseLong(userId));
		} else {
			login();
		}
		return user;
	}

	public static void register(String firstName, String lastName, int age,
			String email, String password, String password2) {
		Logger.info(firstName + " " + lastName + " " + email + " " + password);
		User user = new User(firstName, lastName, age, email, password);
		user.save();
		login();
	}

	public static void authenticate(String email, String password) {
		User user = User.findByEmail(email);
		if (user == null || !user.checkPassword(password)) {
			login();
		}
		session.put("logged_in_userid", user.id);
		Home.index();
	}
	
	public static void deleteUser(Long userid) {
		User user = User.findById(userid);
		user.delete();
		
		index();
	}

}
