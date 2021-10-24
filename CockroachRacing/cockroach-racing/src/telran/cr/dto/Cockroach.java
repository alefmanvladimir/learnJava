package telran.cr.dto;

public class Cockroach extends Thread{
	public int id;
	int dest;
	public static Cockroach winner;
	public Cockroach(int id, int dest) {
		super();
		this.id = id;
		this.dest = dest;
	}
	
	@Override
	public void run() {
		for(int i=0; i<dest; i++) {
			int max = 5;
			int min = 2;
			int rand = (int)(Math.random() * (max-min)) + min;
			try {
				sleep(rand);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
			}
		}
		System.out.printf("CockroachID - %d, dest - %d \n", id, dest);
		
		if(winner==null) {
			winner = this;
		}
	}
	
	public Cockroach getWinner() {
		return winner;
	}
	
}
