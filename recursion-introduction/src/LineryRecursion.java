
public class LineryRecursion {

	public static long pow(int a, int b) {
		if (b == 0) {
			return 1;
		}
		return multiple(a, (int) pow(a, b - 1));
	}

	public static long multiple(int x, int y) {
		if (y == 0) {
			return 0;
		}
		return y > 0 ? x + multiple(x, y - 1) : -x + multiple(x, y + 1);
	}

	public static int sum(int ar[]) {

		return sumR(0, ar);
	}

	private static int sumR(int index, int[] ar) {
		if (index >= ar.length) {
			return 0;
		}
		return ar[index] + sumR(index + 1, ar);
	}

	public static int square(int x) {
		// no cycles, no multiply, no addition methods, no static fields
		x = x < 0 ? -x : x;

		if (x == 0) {
			return 0;
		}

		return x + x - 1 + square(x - 1);

	}

	private static int comparingStr(String str, String substr, int count) {
		if (str.length() == 0 && count != substr.length()) {
			return 0;
		}

		if (count == substr.length()) {
			return 1;
		}
		if (str.charAt(0) == substr.charAt(count)) {

			return comparingStr(str.substring(1, str.length()), substr, count + 1);

		} else if(count==0){
			
			return comparingStr(str.substring(1, str.length()), substr, count);
		} else {
			count = 0;
			return comparingStr(str, substr, count);
		}
		
	}

	public static boolean isSubstring(String str, String substr) {
		// TODO write function
		// boolean isSubstring (String str, String substr)
		// that returns true if a given 'substr' is indeed the substring of a given
		// 'string'.
		// Challenges: 1. To apply only following methods of the class String:
		// charAt(int ind); String substring(int ind); int length(); 2. No cycles;
		return comparingStr(str, substr, 0) > 0 ? true : false;
	}

}
