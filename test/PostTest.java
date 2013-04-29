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
		bob.save();
		blogA = new Blog("Blog1", true);
		bob.addBlog(blogA);
		
		bob.save();		
	}
	
	@After
	public void teardown() {
		blogA.posts.clear();		
		bob.blogs.clear();
		bob.delete();		
	}
	
	@Test
	public void createPost() {
		Post aPost = new Post("Post1", "This is content.");
		blogA.addPost(aPost);
		blogA.save();
		bob.save();
		
		User user = User.findByEmail("bob@gmail.com");
		List<Blog> blogs = user.blogs;
		Blog aBlog = blogs.get(0);
		List<Post> posts = aBlog.posts;
		assertEquals(1, posts.size());		
	}
}
