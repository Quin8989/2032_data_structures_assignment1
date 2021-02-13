import static org.junit.Assert.*;

import org.junit.Test;

public class assignment1_test {

	@Test
	public void addTester1() {
		SkipList<String> list = new SkipList<>();
		assertTrue(list.size() == 0 );
		list.add("String");
		list.add("OOGA BOOGA");
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void getTester1() {
		SkipList<String> list = new SkipList<>();
		list.add("EEEEEEEEEEEE");
		list.add("A");
		list.add("Sports");
		 String y = list.get(1);
		assertTrue(y.equals("A"));
	}
	
	@Test
	public void clearTester1() {
		SkipList<String> list = new SkipList<>();
		list.add("EEEEEEEEEEEE");
		list.add("A");
		list.add("Sports");
		list.clear();
		assertTrue(list.size() == 0);
	}
	
	
	@Test
	public void removeTester1() {
		SkipList<String> list = new SkipList<>();
		list.add("EEEEEEEEEEEE");
		list.add("A");
		list.add("Sports");
		list.remove(0);
		System.out.println(list.toString());
		assertTrue(list.size() == 2);
	}

}
