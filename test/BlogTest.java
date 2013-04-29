import java.util.List;

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


public class BlogTest extends UnitTest {
	
	private User bob;
	
	@Before
	public void setup() {
		bob = new User("Bob", "Jones", 18, "bob@gmail.com", "secret");
		bob.save();
	}
	
	@After
	public void teardown() {
		bob.blogs.clear();
		bob.delete();
	}
	
	@Test
	public void testCreateBlog() {
		Blog aBlog = new Blog("Blog1", false);
		bob.addBlog(aBlog);
		bob.save();
		
		User user = User.findByEmail("bob@gmail.com");
		List<Blog> blogs = user.blogs;
		assertEquals(1, blogs.size());
		Blog blog = blogs.get(0);
		assertEquals("Blog1", blog.name);		
	}
	
	@Test
	public void testCreateMultipleBlogs() {
		Blog blogA = new Blog("Blog1", true);
		Blog blogB = new Blog("Blog2", false);
		
		bob.addBlog(blogA);
		bob.addBlog(blogB);
		bob.save();
		
		User user = User.findByEmail("bob@gmail.com");
		List<Blog> blogs = user.blogs;
		assertEquals(2, blogs.size());
		
		Blog bloga = blogs.get(0);
		assertEquals("Blog1", bloga.name);
		assertTrue(bloga.isPublic);
		
		Blog blogb = blogs.get(1);
		assertEquals("Blog2", blogb.name);
		assertFalse(blogb.isPublic);
		
	}
}
