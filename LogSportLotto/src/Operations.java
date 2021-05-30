
public class Operations {
	/**
	 * 
	 * @param n
	 * @return logN - power of 2 to get n (see tests)
	 */

	public static int log2(long n) {
		int count = 0;
		if (n > 0) {
			while (n > 1) {
				n >>= 1;
				count++;
			}
		} else {
			while (n < -1) {
				n >>= 1;
				count++;
			}
		}
		return count;
	}
}
