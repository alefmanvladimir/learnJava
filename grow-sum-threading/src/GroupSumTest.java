import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GroupSumTest {

	@Test
	void test() {
		int nGroups = 100000;
		int nNumbersGroup = 10000;
		
		PerformanceTest tests [] = new PerformanceTest[] {new GroupSumPerformanceTest("#1 thread", 1, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#2 thread", 2, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#4 thread", 4, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#10 thread", 10, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#40 thread", 40, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#100 thread", 100, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#200 thread", 200, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#1000 thread", 1000, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#10000 thread", 10000, nGroups, nNumbersGroup),
			new GroupSumPerformanceTest("#50000 thread", 50000, nGroups, nNumbersGroup)};

		for(int i=0; i<tests.length; i++) {
			tests[i].run();
		}
	}

}
