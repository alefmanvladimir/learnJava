package telran.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import telran.concurrent.MyBlockingListQueue;

public class MessageBox {
	private static final int DEFAULT_LIMIT = 5;
	private MyBlockingListQueue<String> messages = new MyBlockingListQueue<>(DEFAULT_LIMIT);

	public MessageBox(int limit) {
		messages = new MyBlockingListQueue<>(limit);
	}
	
	public MessageBox() {
		this(DEFAULT_LIMIT);
	}

	public void send(String message) throws InterruptedException {
		messages.put(message);
	}
	
	public String takeMessage() throws InterruptedException {
		return messages.take();
	}
	
	public String getMessage() {
		return messages.poll();
	}
	
}
