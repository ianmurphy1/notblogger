package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class Blog extends Model {
	public String name;
	public boolean isPublic;
	
	@OneToMany (cascade = CascadeType.ALL)
	public List<Post> posts;
	
	public Blog(String name, boolean isPublic) {
		this.name = name;
		this.isPublic = isPublic;
		this.posts = new ArrayList<Post>();
	}
	
	public void addPost(Post post) {
		posts.add(post);
	}
	
	public String toString() {
		return name;
	}
}
