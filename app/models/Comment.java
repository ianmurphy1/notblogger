package models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model
{
  public String content;
 
  public Comment(String content)
  {
    this.content = content;
  }
}