package telran.utils;

public class MemoryService {

	public static int getMaxAvailableMemory() {

		int res = 0;
		long right = Integer.MAX_VALUE;
		long left = 0;
		long mid = right;
		while (left <= right) {

			try {
				byte ar[] = new byte[(int)mid];
				res = (int)mid;
				left = mid + 1;
			} catch (OutOfMemoryError e) {
				right = mid-1 ;
			} finally {
				mid = (left + right) / 2;
			}
		}
		return res;
	}
}
