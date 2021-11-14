package telran.concurrency;

public class MessageBox {
	private String message;
	private boolean finished;
	public synchronized void send(String message) throws InterruptedException {
		while(this.message!=null) {
			wait();
		}
		this.message = message;
		notify();
	}
	
	public synchronized String takeMessage() throws InterruptedException {
		if(finished) {
			Thread.currentThread().interrupt();
		}
		while(message==null) {
			wait();
		}
		String res = message;
		message=null;
		notifyAll();
		return res;
	}
	
	public synchronized void finish() {
		if(Thread.currentThread().getClass().toString().contains("Sender")) {
			finished = true;
		}
	}
}
