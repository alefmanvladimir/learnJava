
public class LogSportLotto {

	public static long getBit(long number, int nBit) {
		long bitValue = 0L;
		bitValue = ((number >> nBit-1) & 1);
		return bitValue;
	}

	public static void main(String[] args) {
		long l = 0L;
		int i = 0;
		while (i < 7) {
			int r = (int) (1 + Math.random() * 49);
			if(getBit(l, r)==0) {
				l = (1L<<(r-1)) | l;
				System.out.println(r);
				i++;
			}
		}
	}
}
