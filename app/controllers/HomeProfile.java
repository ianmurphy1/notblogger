package controllers;

import play.*;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import play.mvc.*;
import java.util.*;
import models.*;

public class HomeProfile extends Controller {
	public static void index() {
		User user = Start.getLoggedInUser();
		render(user);
	}

	public static void changeMessage(String message) {
		User user = Start.getLoggedInUser();
		user.visitorMessage = message;
		user.save();
		Logger.info("Visitor " + message);
		index();
	}

	public static void getPicture(Long id) {
		User user = User.findById(id);
		Blob picture = user.profilePicture;
		if (picture.exists()) {
			response.setContentTypeIfNotSet(picture.type());
			renderBinary(picture.get());
		}
	}

	public static void uploadPicture(Long id, Blob picture) {
		User user = User.findById(id);
		user.profilePicture = picture;
		user.save();
		index();
	}

	public static void getAvatar(Long id) {
		User user = User.findById(id);
		Blob picture = user.avatar;
		if (picture.exists()) {
			response.setContentTypeIfNotSet(picture.type());
			renderBinary(picture.get());
		}
	}

	public static void uploadAvatar(Long id, Blob picture) {
		User user = User.findById(id);
		user.avatar = picture;
		user.save();
		index();
	}

}