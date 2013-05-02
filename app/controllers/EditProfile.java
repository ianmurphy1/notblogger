package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class EditProfile extends Controller {

	public static void index() {
		
		User user = Start.getLoggedInUser();
		render(user);
	}

	public static void changeEmail(Long id, String email, String password) {
		
		User user = Start.getLoggedInUser();

		if (user == null || !user.checkPassword(password)) {
			Logger.info("not matched");
			index();
		}

		if (email.isEmpty() != true) {
			Logger.info("matched changing email");
			user.email = email;
			user.save();
			HomeProfile.index();
		}
	}

	public static void changeName(Long id, String firstName, 
			                      String lastName, String password) {
		
		User user = Start.getLoggedInUser();

		if (user == null || !user.checkPassword(password)) {
			Logger.info("not matched");
			index();
		}

		if ((user.firstName.isEmpty() != true) && (user.lastName.isEmpty() != true)) {
			
			Logger.info("matched: changing name");
			user.firstName = firstName;
			user.lastName = lastName;
			user.save();
			HomeProfile.index();
		}
	}

	public static void changeAge(Long id, String age, String password) {
		
		User user = Start.getLoggedInUser();

		if (user == null || !user.checkPassword(password)) {
			Logger.info("not matched");
			index();
		}

		if (age.isEmpty() != true) {
			Logger.info("matched: changing age");
			user.age = Integer.parseInt(age);
			// user.age = age;
			user.save();
			HomeProfile.index();
		}
	}

	public static void changePassword(Long id, String oldPassword,
			                                   String password) {
		
		User user = Start.getLoggedInUser();

		if (user == null || !user.checkPassword(oldPassword)) {
			Logger.info("not matched");
			index();
		}

		if (password.isEmpty() != true) {
			Logger.info("matched: changing password");
			user.password = password;
			user.save();
			HomeProfile.index();
		}
	}

}