package telran.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
	List<Integer> listInt;
	List<String> listString;
	List<Person> listPerson;
	Person p1 = new Person(1, "Moshe");
	Person p2 = new Person(2, "Alex");

	@BeforeEach
	void setUp() throws Exception {
		listInt = new ArrayList<>(1);
		listString = new ArrayList<>();
		listPerson = new ArrayList<>();
		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		listInt.add(4);
		listInt.add(5);
		listString.add("b");
		listString.add("z");
		listString.add("a");
		listString.add("c");
		listString.add("r");
		listString.add("q");
		listPerson.add(p1);
		listPerson.add(p2);
	}

	@Test
	void sizeTest() {
		assertEquals(5, listInt.size());
		listInt.add(6);
		assertEquals(6, listInt.size());
		listInt.add(7);
		assertEquals(7, listInt.size());
		listInt.remove(6);
		assertEquals(6, listInt.size());
	}

	@Test
	void addTest() {
		listInt.add(9);
		assertEquals(9, listInt.get(5));
		listInt.add(7);
		assertEquals(7, listInt.get(6));
		listInt.add(3);
		assertEquals(3, listInt.get(7));
	}

	@Test
	void addByIndexTest() {
		assertFalse(listInt.add(5, -1));
		assertTrue(listInt.add(5, 0));
		assertTrue(listInt.add(5, 3));
		assertTrue(listInt.add(5, listInt.size()));
		assertFalse(listInt.add(5, listInt.size() + 1));
	}

	@Test
	void getTest() {
		assertEquals(null, listInt.get(-1));
		assertEquals(1, listInt.get(0));
		assertEquals(3, listInt.get(2));
		assertEquals(5, listInt.get(4));
		assertEquals(null, listInt.get(5));
	}

	@Test
	void removeTest() {
		assertFalse(listInt.remove(-1));
		assertTrue(listInt.remove(0));
		assertTrue(listInt.remove(2));
		assertTrue(listInt.remove(listInt.size() - 1));
		assertFalse(listInt.remove(listInt.size()));
	}

	@Test
	void indexOfTest() {
		assertEquals(0, listInt.indexOf(1));
		assertEquals(2, listInt.indexOf(3));
		assertEquals(4, listInt.indexOf(5));
		assertEquals(-1, listInt.indexOf(100));
	}

	@Test
	void lastIndexOfTest() {
		assertEquals(0, listInt.lastIndexOf(1));
		assertEquals(2, listInt.lastIndexOf(3));
		assertEquals(4, listInt.lastIndexOf(5));
		assertEquals(-1, listInt.lastIndexOf(100));
	}

	@Test
	void removeByPatternTest() {
		assertFalse(listInt.remove((Integer) 0));
		assertTrue(listInt.remove((Integer) 1));
		assertTrue(listInt.remove((Integer) 5));
		assertTrue(listInt.remove((Integer) 3));
		assertFalse(listInt.remove((Integer) 6));

	}

	@Test
	void addAllTest() {
		listInt.addAll(listInt);
		assertEquals(9, listInt.lastIndexOf(5));
		assertEquals(7, listInt.lastIndexOf(3));
		assertEquals(5, listInt.lastIndexOf(1));
	}

	@Test
	void setTest() {
		assertEquals(null, listInt.set(9, -1));
		assertEquals(1, listInt.set(9, 0));
		assertEquals(3, listInt.set(9, 2));
		assertEquals(5, listInt.set(9, 4));
		assertEquals(null, listInt.set(9, 5));
	}

	@Test
	void swapTest() {
		assertFalse(listInt.swap(-1, 0));
		assertTrue(listInt.swap(0, 4));
		assertEquals(5, listInt.get(0));
		assertEquals(1, listInt.get(4));
		assertTrue(listInt.swap(0, 2));
		assertEquals(5, listInt.get(2));
		assertEquals(3, listInt.get(0));
	}

	@Test
	void removeAllTest() {
		List<Integer> listTemp = new ArrayList<>(4);
		listTemp.add(1);
		listTemp.add(2);
		listTemp.add(4);
		assertTrue(listInt.removeAll(listTemp));
		assertEquals(3, listInt.get(0));
		assertEquals(5, listInt.get(1));
	}

	@Test
	void retainAllTest() {
		List<Integer> listTemp = new ArrayList<>(4);
		listTemp.add(1);
		listTemp.add(2);
		listTemp.add(4);
		assertTrue(listInt.retainAll(listTemp));
		assertEquals(1, listInt.get(0));
		assertEquals(2, listInt.get(1));
		assertEquals(4, listInt.get(2));
	}

	@Test
	void maxTest() {
		assertEquals(p2, List.max(listPerson));
		assertEquals("z", List.max(listString));
		assertEquals(5, List.max(listInt));
	}

	@Test
	void minTest() {
		assertEquals(p1, List.min(listPerson));
		assertEquals("a", List.min(listString));
		assertEquals(1, List.min(listInt));
	}

	@Test
	void sortTest() {
		listPerson.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
		assertEquals("b", listString.get(0));
		assertEquals("c", listString.get(3));
		assertEquals("q", listString.get(5));
		listString.sort(Comparator.naturalOrder());
		assertEquals("a", listString.get(0));
		assertEquals("q", listString.get(3));
		assertEquals("z", listString.get(5));
	}

	@Test
	void indexOfPredicateTest() {
		Predicate<Integer> pred = n -> n % 2 == 0;
		assertEquals(1, listInt.indexOf(pred));
		assertEquals(-1, listInt.indexOf(n -> n % 10 == 0));
	}

	@Test
	void lastIndexOfPredicateTest() {
		Predicate<Integer> pred = n -> n % 2 == 0;
		assertEquals(3, listInt.lastIndexOf(pred));
		assertEquals(-1, listInt.lastIndexOf(n -> n % 10 == 0));
	}
	
	@Test
	void removeIfTest() {
		assertEquals(2, listInt.get(1));
		assertEquals(4, listInt.get(3));
		listInt.removeIf(n -> n % 2 == 0);
		assertEquals(3, listInt.get(1));
		assertEquals(5, listInt.get(2));
	}
	
	@Test
	void removeRepeatedTest() {
		listInt.add(4);
		listInt.add(6);
		listInt.add(3);
		listInt.add(1);
		listInt.add(2);
		listInt.removeRepeated();
		for(int i=0; i<listInt.size(); i++) {
			System.out.print(listInt.get(i) + ",");
		}
//		System.out.println(" ");
//		System.out.print(listInt.indexOf(2));
//		System.out.print(listInt.lastIndexOf(2));
		
	}
	
	@Test
	void cleanTest() {
		assertEquals(5, listInt.size());
		listInt.clean();
		assertEquals(0, listInt.size());
	}
}
