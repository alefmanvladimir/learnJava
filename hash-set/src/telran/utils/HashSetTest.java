package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashSetTest {
	private static final int N_PERSONS = 100;
	Set<Integer> setInt;
	@BeforeEach
	void setUp() throws Exception {
		setInt = new HashSet<>();
		setInt.add(1);
		setInt.add(2);
		setInt.add(3);
		setInt.add(4);
		setInt.add(5);
	}

	@Test
	void addTest() {
		setInt.add(6);
		assertTrue(setInt.contains(6));
	}
	
	@Test 
	void removeTest(){
		assertTrue(setInt.contains(5));
		setInt.remove(5);
		assertFalse(setInt.contains(5));
	}
	
	@Test
	void addAllTest() {
		Set<Integer> setInt2 = new HashSet<>();
		setInt2.add(6);
		setInt2.add(7);
		setInt2.add(8);
		setInt2.add(9);
		setInt2.add(10);
		setInt.addAll(setInt2.iterator());
		assertEquals(10, setInt.size());
		assertTrue(setInt.contains(10));
		assertTrue(setInt.contains(8));
//		for (int i = 0; i < sizeOld; i++) {
//			assertNotEquals(setInt.indexOf(setInt.get(i)), setInt.lastIndexOf(setInt.get(i)));
//		}
		int count = 0;
		for (Integer num : setInt) {
			count++;
		}
		
		assertEquals(setInt.size(), count);
	}

//	@Test
//	void addIndexTest() {
//		assertTrue(setInt.add(100, 0));
//		assertEquals(100, setInt.get(0));
//		assertEquals(1, setInt.get(1));
//		assertTrue(setInt.add(200, setInt.size()));
//		assertEquals(200, setInt.get(setInt.size() - 1));
//		assertTrue(setInt.add(300, 1));
//		assertEquals(300, setInt.get(1));
//		assertEquals(1, setInt.get(2));
//		assertFalse(setInt.add(400, -1));
//		assertFalse(setInt.add(400, 100));
//		assertEquals(8, setInt.size());
//	}

//	@Test
//	void removeTest() {
//		assertTrue(setInt.remove(0));
//		assertEquals(2, setInt.get(0));
//		assertTrue(setInt.remove(1));
//		assertEquals(4, setInt.get(1));
//		assertFalse(setInt.remove(-1));
//		assertFalse(setInt.remove(setInt.size()));
//		assertEquals(3, setInt.size());
//		assertTrue(setInt.remove(setInt.size() - 1));
//	}
//
//	@Test
//	void getTest() {
//		assertEquals(1, setInt.get(0));
//		assertNull(setInt.get(-1));
//		assertNull(setInt.get(100));
//	}
//
//	@Test
//	void sizeTest() {
//		assertEquals(5, setInt.size());
//	}
//	
//	@Test
//	void removePatternTest() {
//		assertTrue(setInt.remove((Integer) 2));
//		assertEquals(-1, setInt.indexOf(2));
//		assertFalse(setInt.remove((Integer) 2));
//	}
//	
//	@Test
//	void addAllTest() {
//		int sizeOld = setInt.size();
//		setInt.addAll(setInt);
//		assertEquals(sizeOld * 2, setInt.size());
//		for (int i = 0; i < sizeOld; i++) {
//			assertNotEquals(setInt.indexOf(setInt.get(i)), setInt.lastIndexOf(setInt.get(i)));
//		}
//		int count = 0;
//		for (Integer num : setInt) {
//			count++;
//		}
//		
//		assertEquals(setInt.size(), count);
//	}
//	
	@Test
	void removeAll() throws Exception {
		setInt.removeAll(setInt);
		assertEquals(0, setInt.size());
	}

	@Test
	void retainAll() {
		setInt.retainAll(setInt);
		assertEquals(5, setInt.size());
		Set<Integer> patterns = new HashSet<>();
		patterns.add(2);
		setInt.add(1);
		setInt.add(2);
		setInt.add(3);
		setInt.add(4);
		setInt.add(5);
		setInt.add(6);
		setInt.add(7);
		setInt.retainAll(patterns);
		int count = 0;
		for (Integer num : setInt) {
			count++;
		}
		assertEquals(1, count);
		assertTrue(setInt.contains(2));

	}
//	
//	@Test
//	void cleanTest() {
//		setInt.clean();
//		assertEquals(0, setInt.size());
//	}
//	


}
