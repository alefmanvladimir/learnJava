
public class Operations {
/**
 * 
 * @param number - given number
 * @param nBit - given sequential number of bit from 0
 * @return value of nBit in the given number (see tests)
 */
	public static int getBit(int number, int nBit) {
	int bitValue = 0;
	//here your code goes 
	//............
	bitValue = ((number >> nBit) & 1);
	return bitValue;
}
	/**
	 * 
	 * @param number - given number
	 * @param nBit
	 * @return new number (as a particular case it may be the same number)
	 *  with value of the given bit - 1 (see tests)
	 */
	public static int setBit(int number, int nBit) {
		int numberRes = number;
		//here your code goes 
		//............
		if(getBit(numberRes, nBit)==0) {
			numberRes = numberRes | (1<<nBit);
			
			// another solutions
			// numberRes = numberRes + (1<<nBit);
		}
		
		return numberRes;
	}
	/**
	 * 
	 * @param number - given number
	 * @param nBit
	 * @return new number (as a particular case it may be the same number)
	 *  with value of the given bit - 0 (see tests)
	 */
	public static int resetBit(int number, int nBit) {
		int numberRes = number;
		//here your code goes 
		//............
		
		if(getBit(numberRes, nBit)==1) {
			numberRes = numberRes^(1<<nBit);
			// another solutions
			// numberRes = numberRes-(1<<nBit);
		}
		return numberRes;
	}
	

}
