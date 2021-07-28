package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryServiceTest {
	byte ar[];
	@Test
//	void test() {
//		boolean flException = false;
//		int maxMemory = MemoryService.getMaxAvailableMemory();
//		ar = new byte[maxMemory]; // no exception
//		ar = null;
//		try {
//			ar = new byte[maxMemory+1];
//			fail("There should be exceprion");
//		} catch(OutOfMemoryError e){
//			System.out.println("Besedr");
//		}
//	}
	
	void myTest() {
		boolean flException = false;
		int maxMemory = MemoryService.getMaxAvailableMemory();
		ar = new byte[maxMemory]; // no exception
		ar = null;
		try {
			ar = new byte[maxMemory+1];
			fail("There should be exception! Max Memory - " + maxMemory);
		} catch(OutOfMemoryError e){
			System.out.println("Besedr");
			System.out.println("Max memory is - " + maxMemory);
		}
	}

}
