public abstract class PerformanceTest {
	private String testName;
	
public PerformanceTest(String testName) {
		this.testName = testName;
	}
public void run() {
	long start = System.currentTimeMillis();
	runTest();
	printResult(System.currentTimeMillis() - start);
}
private void printResult(long diffTime) {
	System.out.printf("Test: %s; Running Time: %d\n",
			testName, diffTime);
	
}
protected abstract void runTest();
	

}
