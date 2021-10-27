package telran.hw53;

public class PrinterControllerAppl {

	private static final int N_NUMBERS = 100;
	private static final int N_PORTIONS = 10;
	private static final int N_PRINTERS = 4;

	public static void main(String[] args) {
		Printer firstPrinter = createPrinters(N_NUMBERS, N_PORTIONS, N_PRINTERS);
		startPrinters(firstPrinter, firstPrinter);
		firstPrinter.interrupt();
	}

	private static void startPrinters(Printer firstPrinter, Printer currentPrinter) {
		if (currentPrinter.nextPrinter == null) {
			currentPrinter.nextPrinter = firstPrinter;
			currentPrinter.start();
		} else {
			currentPrinter.start();
			startPrinters(firstPrinter, currentPrinter.nextPrinter);
		}
	}

	private static Printer createPrinters(int nNumbers, int nPortions, int printers) {
		Printer printer;

		if (printers <= 1) {
			printer = new Printer(printers, nNumbers, nPortions, null);
		} else {
			printer = new Printer(printers, nNumbers, nPortions, createPrinters(nNumbers, nPortions, printers - 1));
		}
		return printer;
	}

}
