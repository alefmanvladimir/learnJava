package telran.hw53;

public class Printer extends Thread {
	public int number;
	public int N_NUMBERS;
	public int N_PORTIONS;
	public Printer nextPrinter;

	public Printer(int number, int n_NUMBERS, int n_PORTIONS, Printer printer) {
		super();
		this.number = number;
		N_NUMBERS = n_NUMBERS;
		N_PORTIONS = n_PORTIONS;
		nextPrinter = printer;
	}

	@Override
	public void run() {
		while (N_NUMBERS > 0) {
			try {
				sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				for (int i = N_NUMBERS > N_PORTIONS ? N_PORTIONS : N_NUMBERS; i > 0; i--) {
					System.out.print(number);
					N_NUMBERS--;
				}
				System.out.println("");
				nextPrinter.interrupt();
			}
		}
	}
}
