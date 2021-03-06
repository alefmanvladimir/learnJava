
public class Operations {
	/**
	 * 
	 * @param n
	 * @return logN - power of 2 to get n (see tests)
	 */

	public static int log2Rtl(long n) {
		int res = 62;
		while (((1L << res) & n) == 0) {
			res--;
		}
		return res;
	}

	public static int log2Ltr(long n) {
		int count = 0;

		if (n < 0) {
			n = -n;
		}

		while (n > 1) {
			n >>= 1;
			count++;
		}

		return count;
	}

	public static int log2(long n) {
		return n > (-1 >>> 1) ? log2Rtl(n) : log2Ltr(n);
	}
}