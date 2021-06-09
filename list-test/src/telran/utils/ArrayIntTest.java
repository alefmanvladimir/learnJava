import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ArrayIntTest {

	private static final int N_NUMBERS = 1_000_000;

	@Test
	void sortTest() {
		short ar[] = getShortArray(N_NUMBERS);
		Arrays.sort(ar);
		int lim = N_NUMBERS - 1;
		for (int i = 0; i < lim; i++) {
			assertTrue(ar[i] <= ar[i + 1]);
		}
	}

	private short[] getShortArray(int nNumbers) {
		short[] res = new short[nNumbers];
		for(int i = 0; i < nNumbers; i++) {
			res[i] = (short) (Math.random() * Short.MAX_VALUE);
		}
		return res;
	}
	@Test
	void binaryCountTest() {
		int ar[] = {1, 3, 3, 4, 4, 4, 4, 4};
		assertEquals(0, ArrayInt.binaryCount(ar, 100));//no number
		assertEquals(1, ArrayInt.binaryCount(ar, 1)); //beginning
		assertEquals(2, ArrayInt.binaryCount(ar, 3)); //middle
		assertEquals(5, ArrayInt.binaryCount(ar, 4)); //tailing 
	}
	@Test 
	void addNumberSortedTest() {
		int ar[] = {1, 3, 3, 4, 4, 4, 4, 4};
		int arBeginning[] = {-10, 1, 3, 3, 4, 4, 4, 4, 4}; //add at beginning
		int arMiddleNew[] = {1, 2, 3, 3, 4, 4, 4, 4, 4};//add at middle new
		int arMiddleExisted[] = {1, 3, 3,3, 4, 4, 4, 4, 4};//add at middle existed
		int arEnd[] = {1, 3, 3, 4, 4, 4, 4, 4, 100};//add at end new
		assertArrayEquals(arBeginning, ArrayInt.addNumberSorted(ar, -10));
		assertArrayEquals(arMiddleNew, ArrayInt.addNumberSorted(ar, 2));
		assertArrayEquals(arMiddleExisted, ArrayInt.addNumberSorted(ar, 3));
		assertArrayEquals(arEnd, ArrayInt.addNumberSorted(ar, 100));
		
	}
	@Test
	void removeNumberTest() {
		int ar[] = {1, 3, 3, 4, 4, 4, 4, 4};
		int arBeginning[] = {3, 3, 4, 4, 4, 4, 4};//remove from beginning
		int arMiddle[] = {1, 3,  4, 4, 4, 4, 4};//remove from middle
		int arEnd[] = {1, 3, 3, 4, 4, 4, 4};//remove from end
		assertArrayEquals(ar, ArrayInt.removeNumber(ar, -1));//index < 0
		assertArrayEquals(ar, ArrayInt.removeNumber(ar, ar.length));//index == length
		assertArrayEquals(arBeginning, ArrayInt.removeNumber(ar, 0));//index == 0
		assertArrayEquals(arMiddle, ArrayInt.removeNumber(ar, 2));//index at middle
		assertArrayEquals(arEnd, ArrayInt.removeNumber(ar, ar.length - 1));//index at end
	}
	@Test
	void standardBinarySearchTest() {
		int ar[] = {1, 3,  4};
		assertEquals(-1, Arrays.binarySearch(ar, 0)); //no number at beginning
		assertEquals(-4, Arrays.binarySearch(ar, 100)); //no number at end
		assertEquals(-2, Arrays.binarySearch(ar, 2)); //no number at middle
		assertEquals(1, Arrays.binarySearch(ar, 3)); //number exists
		
	}

}
