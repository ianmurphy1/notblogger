import java.util.ArrayList;
import java.util.List;

import models.Blog;
import models.Comment;
import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class CommentTest extends UnitTest {
	private User bob;
	private Blog blogA;
	private Post postA;
	
	@Before
	public void setup() {
		bob = new User("Bob", "Jones", 18, "bob@gmail.com", "secret");		
		
			
		blogA = new Blog("Blog1", true);		
		bob.addBlog(blogA);			
		bob.save();
		postA = new Post("Title1", "Post 1 content", bob);
		postA.save();
		blogA.addPost(postA);		
		blogA.save();
	}
	
	@After
	public void teardown() {
		bob.delete();
	}
	
	@Test
	public void testCreateComment() {
		User user = User.findByEmail("bob@gmail.com");		
		
		Comment commentA = new Comment("This is a comment", bob);		
		Post post = postA;		
		post.addComment(commentA);
		postA.save();
		blogA.save();
		bob.save();
		
		List<Comment> comments = post.comments;
		assertEquals(1, comments.size());
		
		Comment thisComment = comments.get(0);		
		assertEquals("This is a comment", thisComment.content);
		assertEquals("This is a comment", bob.blogs.get(0).posts.get(0).comments.get(0).content);
	}
	
	@Test
	public void testMultipleCreateComments() {
		User user = User.findByEmail("bob@gmail.com");
		
		Post post = postA;	
		
		Comment commentA = new Comment("First", user);				
		post.addComment(commentA);
		
		Comment commentB = new Comment("Second", user);
		post.addComment(commentB);
		postA.save();
		blogA.save();
		bob.save();
		
		List<Comment> comments = postA.comments;
		assertEquals(comments.size(), 2);

		Comment comment1 = postA.comments.get(0);
		assertEquals(comment1.content, "First");
		Comment comment2 = postA.comments.get(1);
		assertEquals(comment2.content, "Second");
	}
	
	@Test
	public void testRemoveComment() {
		User user = User.findByEmail("bob@gmail.com");
		
		Post post = postA;	
		
		Comment commentA = new Comment("First", user);				
		post.addComment(commentA);
		
		Comment commentB = new Comment("Second", user);
		post.addComment(commentB);
		
		
		List<Comment> comments = postA.comments;
		assertEquals(2, comments.size());

		post.removeComment(commentA);
		
		commentA.delete();
		assertEquals(1, comments.size());
		
		Comment testThis = comments.get(0);
		assertEquals("Second", testThis.content);
	}
}
