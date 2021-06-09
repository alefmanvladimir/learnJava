package telran.utils;

import static org.junit.Assert.assertEquals;
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
		
		list.add(3, 2);
		list.add(3, 2);
		list.add(3, 0);
		list.add(3, 2);
		
		ad = list.add(5, list.size());
		
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		
		System.out.println();
		System.out.println(ad);
		
		list.remove(0);
		list.remove(2);
		boolean r = list.remove(list.size());
		
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println();
		System.out.println(r);
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
	
	void remove() {
		int number = (int)list.get(list.size()-2);
		list.remove(list.size()-1);
		assertEquals(number, (int)list.get(list.size()-1));
	}
	
	
	


}
