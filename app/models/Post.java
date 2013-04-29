package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Post extends Model {
	public String title;
	public Date postedAt;
	
	@Lob
	public String content;
	
	@OneToMany (cascade = CascadeType.ALL)
	public List<Comment> comments;
	
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
		this.comments = new ArrayList<Comment>();
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public String toString() {
		return title;
	}
}
