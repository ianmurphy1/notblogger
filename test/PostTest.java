import java.util.ArrayList;
import java.util.List;

import models.Blog;
import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class PostTest extends UnitTest {
	
	private User bob;
	private Blog blogA;
	
	@Before
	public void setup() {
		bob = new User("Bob", "Jones", 18, "bob@gmail.com", "secret");	
		String author = bob.firstName;
		blogA = new Blog("Blog1", author, true);
		bob.addBlog(blogA);		
		bob.save();		
	}
	
	@After
	public void teardown() {
		blogA.posts.clear();		
		bob.blogs.clear();
		bob.delete();
		blogA.delete();
	}
	
	@Test
	public void createPost() {
		String author = bob.firstName;
		Post aPost = new Post("Post1", "This is content.", author);
		blogA.addPost(aPost);
		blogA.save();
		bob.save();
		
		User user = User.findByEmail("bob@gmail.com");
		List<Blog> blogs = user.blogs;
		Blog aBlog = blogs.get(0);
		List<Post> posts = aBlog.posts;
		assertEquals(1, posts.size());
		
		Post thePost = posts.get(0);
		assertEquals("Post1", thePost.title);
		assertEquals("This is content.", thePost.content);
	}
	
	@Test
	public void createMultiplePosts() {
		String author = bob.firstName;
		Post postA = new Post("Post1", "This is post 1.", author);
		blogA.addPost(postA);
		blogA.save();
		bob.save();
		
		Post postB = new Post("Post2", "This is post 2.", author);
		blogA.addPost(postB);
		blogA.save();
		bob.save();
		
		User user = User.findByEmail("bob@gmail.com");
		List<Blog> blogs = user.blogs;
		Blog aBlog = blogs.get(0);
		List<Post> posts = aBlog.posts;
		assertEquals(2, posts.size());
		
		Post posta = posts.get(0);
		assertEquals("Post1", posta.title);
		assertEquals("This is post 1.", posta.content);
		
		Post postb = posts.get(1);
		assertEquals("Post2", postb.title);
		assertEquals("This is post 2.", postb.content);
	}
	
	@Test
	public void testRemovePost() {
		String author = bob.firstName;
		Post postA = new Post("Post1", "This is post 1.", author);
		blogA.addPost(postA);
		Post postB = new Post("Post2", "This is post 2.", author);
		blogA.addPost(postB);
		blogA.save();
		bob.save();
		
		User user = User.findByEmail("bob@gmail.com");
		Blog aBlog = user.blogs.get(0);
		assertEquals(2, aBlog.posts.size());	
		
		aBlog.removePost(postA);			
		aBlog.save();
		postA.delete();
		
		User secondUser = User.findByEmail("bob@gmail.com");
		Blog secondBlog = secondUser.blogs.get(0);
		List<Post> posts = secondBlog.posts;
		Post aPost = posts.get(0);
		assertEquals("Post2", aPost.title);		
	}
}
