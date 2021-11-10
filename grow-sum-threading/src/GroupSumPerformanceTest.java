import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GroupSumPerformanceTest extends PerformanceTest{
	
	int nThreads;
	int nGroups;
	int nNumbersGroup;
	
	public GroupSumPerformanceTest(String testName, int nThreads, int nGroups, int nNumbersGroup) {
		super(testName);
		this.nThreads = nThreads;
		this.nGroups = nGroups;
		this.nNumbersGroup = nNumbersGroup;
	}


	@Override
	protected void runTest() {
		int groups[][] = getGroups();
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		List<OneGroupSum> tasks = startGroups(executor, groups);
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(tasks.stream().mapToLong(t->t.result).sum());
	}
	
	private List<OneGroupSum> startGroups(ExecutorService executor, int[][] groups) {
		List<OneGroupSum> res = new ArrayList<>();
		for(int i=0; i< nNumbersGroup; i++) {
			OneGroupSum group = new OneGroupSum(groups[i]);
			res.add(group);
			executor.execute(group);
		}
		return res;
	}

	private int[][] getGroups() {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		int result [][] = new int [nGroups][nNumbersGroup];
		for(int i=0; i<nGroups; i++) {
			for(int j=0; j<nNumbersGroup; j++) {
				result[i][j] = tlr.nextInt();
			}
		}
		return result;
	}

}
