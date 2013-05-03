package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User extends Model {
	public String   firstName;
	public String   lastName;	
	public String   email;
	public String   password;
	public String   visitorMessage;
	public int      age;
	public Blob     profilePicture;
	public Blob     avatar;
	
	@ManyToMany
	@JoinTable(name = "Subscribing")
	public Set<User> subscribing;	
	
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
		this.blogs.add(blog);
	}
	
	public void removeBlog(Blog blog) {
		this.blogs.remove(blog);
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public static User findByEmail(String email) {
		return find("email", email).first();
	}
	
	public static User findByName(String name) {
		return find("firstName", name).first();
	}
	
	public String toString() {
		return firstName;
	}

}
