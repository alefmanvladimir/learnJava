package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreeTest {
	SortedSet <Integer> treeInt;
	@BeforeEach
	void setUp() throws Exception {
		treeInt = new TreeSet<>();
//		treeInt.add(1);
//		treeInt.add(0);
//		treeInt.add(2);
//		treeInt.add(3);
//		treeInt.add(4);
//		treeInt.add(5);
		
		treeInt.add(20);
		treeInt.add(0);
		treeInt.add(2);
		treeInt.add(8);
		treeInt.add(4);
		treeInt.add(6);
		treeInt.add(10);
		
//		treeInt.add(7);
//		treeInt.add(6);
//		treeInt.add(9);
//		treeInt.add(8);
//		treeInt.add(12);
//		treeInt.add(10);
//		treeInt.add(13);
//		treeInt.add(11);
		
		for (Integer obj : treeInt) {
			System.out.print(obj + ", ");
		}
	}
	@Test
	void asd() {
		System.out.println(treeInt.ceiling(5));
	}
//	@Test
//	void remove() {
//		assertEquals(14, treeInt.size());
//		assertTrue(treeInt.contains(2));
//		treeInt.remove(2);
//		assertFalse(treeInt.contains(2));
//		assertEquals(13, treeInt.size());
//		
//		assertTrue(treeInt.contains(10));
//		treeInt.remove(10);
//		assertFalse(treeInt.contains(10));
//		assertEquals(12, treeInt.size());
////		
////		assertTrue(treeInt.contains(9));
////		treeInt.remove(9);
////		assertFalse(treeInt.contains(9));
////		assertEquals(12, treeInt.size());
////		System.out.println(" ");
////		for (Integer obj : treeInt) {
////			System.out.print(obj + ", ");
////		}
//	}
	
	
}
