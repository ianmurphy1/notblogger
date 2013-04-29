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

public class UserTest extends UnitTest {
	private User u1, u2, u3;

	@Before
	public void setup() {
		u1 = new User("Mick", "Jones", 18, "mike@gmail.com", "secret");
		u2 = new User("Jim", "Jacob", 25, "jim@gmail.com", "secret");
		u3 = new User("Frank", "Stapleton", 34, "frank@gmail.com", "secret");
		u1.save();
		u2.save();
		u3.save();
	}
	
	@After
	public void teardown() {
		u1.delete();
		u2.delete();
		u3.delete();
	}
	
	@Test
	public void testCreate() {
		User a = User.findByName("Mick");
		assertNotNull(a);
		assertEquals("Mick", a.firstName);
		User b = User.findByName("Jim");
		assertNotNull(b);
		assertEquals("Jim", b.firstName);
		User c = User.findByName("Frank");
		assertNotNull(c);
		assertEquals("Frank", c.firstName);
	}
	
	@Test
	public void notCreated() {
		User d = User.findByName("Dave");
		assertNull(d);
	}
}
