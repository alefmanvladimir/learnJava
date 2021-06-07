import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class ArraysMethodsTest {
	int N_NUMBERS = 1000;

	private int[] getSortArray(int nNumbers) {
		int res[] = new int[nNumbers];
		for (int i = 0; i < nNumbers; i++) {
			res[i] = (short) (Math.random() * Short.MAX_VALUE);
		}
		Arrays.sort(res);
		return res;
	}
	
	
	@Test
	void test() {
		
		// test binaryCount
		int array1 [] = {2, 2, 2, 2, 3, 4,5,6,7,8,9};
		int array2 [] = {2, 2, 2, 2, 3, 4, 4,5,6,7,8,9};
		assertEquals(4, ArraysMethods.binaryCount(array1, 2));
		assertEquals(2, ArraysMethods.binaryCount(array2, 4));
		assertEquals(0, ArraysMethods.binaryCount(array1, 1));
		assertEquals(0, ArraysMethods.binaryCount(array1, -5));
		
		
		
		// test addNumberSorted
		
		
		int res [] = ArraysMethods.addNumberSorted(array1, 4);
		
		Assert.assertArrayEquals(res, array2);
		
		
		
		
		// test removeNumber
		int countOfIndex = ArraysMethods.binaryCount(array2, 2);
		int masRes [] = ArraysMethods.removeNumber(array2, 2);
		assertEquals(countOfIndex-1, ArraysMethods.binaryCount(masRes, 2));
//		for(int i=0; i<resArray.length; i++) {
//			System.out.print(res[i] + ", ");
//		}
//		assertEquals(4, Arrays.binarySearch(array, 2));
//		int remMas [] = ArraysMethods.removeNumber(array, 10);
//		for(int i=0; i<remMas.length; i++) {
//			System.out.print(remMas[i] + ", ");
//		}
	}

}
