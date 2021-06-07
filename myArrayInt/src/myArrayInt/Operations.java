package myArrayInt;

import java.util.Arrays;

public class Operations {
	public static boolean isSum(short array[], short sum) {
		boolean even = sum % 2 > 0 ? true : false;
		for (int i = 0; i < array.length - 1; i++) {
			if (even) {
				if (array[i] < sum && array[i] % 2 > 0) {

				}
			}

		}

		return true;
	}

	public static short[] sort(short array[]) {
		short counters[] = new short[((1 << 15) - 1)];
		short sortArray[] = new short[array.length];
		for (int i = 0; i < array.length; i++) {
			counters[array[i]]++;
		}

		int indexSortMas = 0;

		for (short i = 0; i < counters.length && indexSortMas < array.length; i++) {
			for (int j = 0; j < counters[i]; j++) {
				sortArray[indexSortMas++] = i;
			}
		}

		return sortArray;
	}

	public static int[] push(int[] array, int elem) {
		int[] newAr = Arrays.copyOf(array, array.length + 1);
		newAr[array.length] = elem;
		return newAr;
	}

	public static int[] insert(int[] array, int index, int num) {
		if (index <= array.length) {
			int[] res = Arrays.copyOf(array, array.length + 1);
			res[index] = num;
//			Arrays.copyOfRange(array, 0, index);
			System.arraycopy(array, 0, res, 0, index);
			System.arraycopy(array, index, res, index + 1, array.length - index);
//			for(int i=index; i<res.length; i++) {
//				res[i+1] = res[i];
//			}
//			res[index] = num;
			return res;
		}

		return array;
	}

	// dz

}
