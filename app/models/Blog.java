package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Blog extends Model {
	
	public String name;	
	public boolean isPublic;
	
	@ManyToOne
	public User author;
	
	@OneToMany(mappedBy="blog", cascade = CascadeType.ALL)
	public List<Post> posts;
	
	public Blog(String name, boolean isPublic) {
		this.name = name;		
		this.isPublic = isPublic;
		this.posts = new ArrayList<Post>();
	}
	
	public void addPost(Post post) {
		post.blog = this;
		post.save();
		posts.add(post);
	}
	
	public void removePost(Post post) {
		posts.remove(post);
	}
	
	public String toString() {
		return name;
	}
}
