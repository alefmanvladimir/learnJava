package myArrayInt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ArrayTest {

	private static final int N_NUMBERS = 1000000000;

	@Test
	void test() {
		
//		short [] array = getShortArray(N_NUMBERS);
//		array = Operations.sort(array);
//		for(int i=0; i<array.length-1; i++) {
//			assertTrue(array[i]<=array[i+1]);
//		}
//		
//		Operations.insert(null, N_NUMBERS, N_NUMBERS)

	}

	private short[] getShortArray(int nNumbers) {
		short res[] = new short[nNumbers];
		for (int i = 0; i < nNumbers; i++) {
			res[i] = (short) (Math.random() * Short.MAX_VALUE);
		}
		return res;
	}

}
