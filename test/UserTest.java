import org.junit.Before;
import org.junit.Test;

import models.User;
import play.mvc.After;
import play.test.UnitTest;

public class UserTest extends UnitTest {
	private User u1, u2, u3;

	@Before
	public void setup() {
		u1 = new User("Mike", "Jones", 18, "mike@gmail.com", "secret");
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
		User a = User.findByName("Mike");
		assertNotNull(a);
	}
}
