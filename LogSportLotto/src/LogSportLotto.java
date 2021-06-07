
public class LogSportLotto {

	public static int indexOfbinary(int ar[], int num) {
		int left = 0;
		int right = ar.length-1;
		int middle = 0;
		while(left <= right-1) {
			middle = (left + right)/2;
			if(ar[middle]==num) {
				return middle;
			} else if(ar[middle]>num) {
				right = middle-1;
			} else {
				left = middle+1;
			}
			
		}
		return -1;
	}
	
	private static final int N_NUMBERS = 7;

	public static long getBit(long number, int nBit) {
		long bitValue = 0L;
		bitValue = ((number >> nBit-1) & 1);
		return bitValue;
	}
	
	public static int getLottoNumber(long _bitMax) {
		int number = (int) (1 + Math.random() * 49);
		return getBit(_bitMax, number)==0?number:getLottoNumber(_bitMax);
	}

	public static void main(String[] args) {
		long bitMax = 0;
		int number = 0;
//		int i = 0;
//		while (i < 7) {
//			int r = (int) (1 + Math.random() * 49);
//			if(getBit(bitMax, r)==0) {
//				bitMax = (1L<<(r-1)) | bitMax;
//				System.out.println(r);
//				i++;
//			}
//		}
		
		for(int i=0; i<N_NUMBERS; i++) {
			number = getLottoNumber(bitMax);
			bitMax |= (1L<<(number-1));
			System.out.println(number);
		}
	}
}
