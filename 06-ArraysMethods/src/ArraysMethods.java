
public class ArraysMethods {

	public static int binaryCount(int[] ar, int num) {
		int count = 0;
		int left = 0;
		int right = ar.length - 1;
		int numRight = 1;
		int numLeft = 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (num > ar[mid]) {
				left = mid + 1;
			} else if (num < ar[mid]) {
				right = mid - 1;
			} else {
				count++;
				if (mid + numRight < ar.length && mid - numLeft >= 0) {
					if (num == ar[mid + numRight]) {
						numRight++;
					} else if (num == ar[mid - numLeft]) {
						numLeft++;
					} else {
						break;
					}
				} else {
					break;
				}
			} 
		}
		return count;
	}

	public static int[] addNumberSorted(int[] ar, int num) {
		int left = 0;
		int right = ar.length - 1;
		int index = 0;
		int [] res = new int[ar.length+1];
		int [] _num = {num};
		while (left <=right) {
			int mid = (left + right) / 2;
			if (num > ar[mid+1]) {
				left = mid + 1;
			} else if (num < ar[mid-1]) {
				right = mid - 1;
			} else {
				index = mid;
				break;
			}
		}
		
		System.arraycopy(ar, 0, res, 0, index);
		System.arraycopy(_num, 0, res, index, 1);
		System.arraycopy(ar, index, res, index+1, ar.length-index);
		
		return res;
	}
	
	public static int[] removeNumber(int[] ar, int num) {
		int [] res = new int[ar.length-1];
		System.arraycopy(ar, 0, res, 0, num);
		System.arraycopy(ar, num+1, res, num, res.length-num);
		return res;
	}
}
