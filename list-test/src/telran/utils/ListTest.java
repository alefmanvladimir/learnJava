package telran.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
List<Integer> list;
	@BeforeEach
	void setUp() throws Exception {
		boolean ad = false;
		list = new ArrayList<>(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(500, 2);
		list.add(3, 2);
		list.add(3, 2);
		list.add(3, 0);
		list.add(3, 2);
		
		ad = list.add(5, list.size());
		
		list.remove(0);
		list.remove(2);

	}

	@Test
	
	void add() {
		// testing method add
		list.add(5);
		assertEquals(5, (int)list.get(list.size()-1));
		boolean added = list.add(6, list.size());
		assertEquals(true, added);
		added = list.add(7, list.size()+1);
		assertEquals(false, added);
	}

//	
//	@Test
//	void indexOf() {
//		assertEquals(0, list.indexOf(1));
//		assertEquals(1, list.indexOf(3));
//		assertEquals(list.size()-1, list.indexOf(list.get(list.size()-1)));
//		assertEquals(-1, list.indexOf(3452323));
//		list.add(500, 2);
//		assertEquals(2, list.indexOf(500));
//		
//		int a = 500;
//		int b = 500;
//		
//		assertTrue(a==b);
//		
//		Integer _a = 500;
//		Integer _b = 500;
//		
//		assertTrue(_a!=_b);
//		assertTrue(_a.equals(_b));
//		
//		String hello = "Hello";
//		String hello1 = new String("Hello");
//		assertTrue(hello.equals(hello1));
//		
//		Person person1 = new Person(0, "Moshe");
//		Person pattern = new Person(0, "asdfasdf");
//		List<Person> persons = new ArrayList<>();
//		persons.add(person1);
//		assertEquals(0, persons.indexOf(pattern));
//	}
	
	@Test
	void lastIndexOf() {
		
//		for(int i=0; i<list.size(); i++) {
//			System.out.print(list.get(i) + ", ");
//		}
		
		assertEquals(0, list.indexOf(3));
		assertEquals(6, list.lastIndexOf(3));
		assertEquals(1, list.lastIndexOf(1));
		assertEquals(list.size()-1, list.lastIndexOf(5));
		assertEquals(5, list.lastIndexOf(500));
		
	}
	
	@Test
	void remove() {
		list.remove(500);
		list.remove(3);
		list.remove(5);
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println("size: " + list.size());
//		assertEquals(number, (int)list.get(list.size()-1));
	}
	
	
	
	
	


}
