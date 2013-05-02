package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Blog extends Model {
	
	public String name;
	public String author;
	public boolean isPublic;
	
	@OneToMany (cascade = CascadeType.ALL)
	public List<Post> posts;
	
	public Blog(String name, String author, boolean isPublic) {
		this.name = name;
		this.author = author;
		this.isPublic = isPublic;
		this.posts = new ArrayList<Post>();
	}
	
	public void addPost(Post post) {
		posts.add(post);
	}
	
	public void removePost(Post post) {
		posts.remove(post);
	}
	
	public String toString() {
		return name;
	}
}
