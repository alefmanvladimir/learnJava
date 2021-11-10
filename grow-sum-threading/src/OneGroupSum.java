import java.util.Arrays;

public class OneGroupSum implements Runnable {
	int group[];
	long result;
	
	@Override
	public void run() {
		result = Arrays.stream(group).asLongStream().sum();
	}
	
	public OneGroupSum(int[] group) {
		super();
		this.group = group;
	}

}
