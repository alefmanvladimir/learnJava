import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class GroupSumAppl {

	private static final int N_THREADS = 100;
	private static final int N_GROUPS = 10000;
	private static final int N_NUMBERS_GROUP = 10000;

	public static void main(String[] args) {
		int groups[][] = getGroups();
		ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
		List<OneGroupSum> tasks = startGroups(executor, groups);
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tasks.stream().mapToLong(t->t.result).sum());
	}

	private static List<OneGroupSum> startGroups(ExecutorService executor, int[][] groups) {
		List<OneGroupSum> res = new ArrayList<>();
		for(int i=0; i< N_GROUPS; i++) {
			OneGroupSum group = new OneGroupSum(groups[i]);
			res.add(group);
			executor.execute(group);
		}
		return res;
	}

	private static int[][] getGroups() {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		int result [][] = new int [N_GROUPS][N_NUMBERS_GROUP];
		for(int i=0; i<N_GROUPS; i++) {
			for(int j=0; j<N_NUMBERS_GROUP; j++) {
				result[i][j] = tlr.nextInt();
			}
		}
		return result;
	}

}
