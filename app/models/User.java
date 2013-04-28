package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	public String   firstName;
	public String   lastName;
	public int      age;
	public String   email;
	public String   password;
	public Blob     profilePicture;
	public Blob     avatar;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Blog> blogs;
	
	public User(String firstName, String lastName, int age, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;		
		this.email = email;
		this.password = password;
		this.blogs = new ArrayList<Blog>();
	}
	
	public void addBlog(Blog blog) {
		blogs.add(blog);
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public static User findByName(String name) {
		return find("firstName", name).first();
	}
	
	public String toString() {
		return firstName;
	}

}
