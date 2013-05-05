package models;

import javax.persistence.Entity;

import play.data.validation.Email;
import play.data.validation.Required;
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
import javax.persistence.Table;

@Entity
public class User extends Model {
	
	@Required
	public String   firstName;
	
	@Required
	public String   lastName;	
	
	@Required
	@Email
	public String   email;
	
	@Required
	public String   password;
	
	@Required
	public int      age;
	
	
	public String   visitorMessage;
	public Blob     profilePicture;
	public Blob     avatar;
	
	@ManyToMany
	@JoinTable(name = "Subscribing")
	public Set<User> subscribing;	
	
	@OneToMany(mappedBy="author", cascade = CascadeType.ALL)
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
		blog.author = this;
		blogs.add(blog);		
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
