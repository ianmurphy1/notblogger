package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {	
	
	public Date postedAt;
	
	@Lob
	public String content;	
	
	@ManyToOne
	public Post post;
	
	@ManyToOne
	public User author;

	public Comment(String content) {
		this.content = content;		
		this.postedAt = new Date();
	}
	
	public String toString() {
		return author + " " + content;
	}
}