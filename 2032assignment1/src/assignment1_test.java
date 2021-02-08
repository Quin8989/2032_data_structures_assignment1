import static org.junit.Assert.*;

import org.junit.Test;

public class assignment1_test {

	@Test
	public void addTester1() {
		SkipList<String> list = new SkipList<>();
		assertTrue(list.size() == 0 && list.height == 0);
		list.add("1");
		assertTrue(list.size() == 1 && list.height != 0);
	}

}
