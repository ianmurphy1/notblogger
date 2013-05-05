package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Post extends Model {
	public String title;
	
	public Date postedAt;
	
	@Lob
	public String content;
	
	@ManyToOne
	public Blog blog;
	
	@ManyToOne
	public User author;
	
	
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL)
	public List<Comment> comments;
	
	public Post(String title, String content, User author) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.postedAt = new Date();
		this.comments = new ArrayList<Comment>();
	}
	
	public void addComment(Comment comment) {
		comment.post = this;		
		comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		comments.remove(comment);
	}
	
	public String toString() {
		return title;
	}
}
