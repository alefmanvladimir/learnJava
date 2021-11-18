package telran.concurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingListQueue<E> implements BlockingQueue<E> {
	private int count;
	
	class Node<E>{
		E item;
		Node<E> next;
		Node(E x) { item = x; }
	}
	
	private final int limit;
	
	Node<E> head;
	Node<E> tail;
	
    private final ReentrantLock monitor = new ReentrantLock();

    private final Condition producerWaiting = monitor.newCondition();

    private final Condition consumerWaiting = monitor.newCondition();
	
	
	@Override
	public int remainingCapacity() {
		return limit - count;
	}

	
	public MyBlockingListQueue() {
		this(Integer.MAX_VALUE);
	}
	
	public MyBlockingListQueue(int limit) {
		this.limit = limit;
		count = 0;
	}

	@Override
	public E remove() {
		monitor.lock();
		try {
			if (head != null) {
				E res = head.item;
				head = head.next;
				count--;
				return res;
			} else {
				throw new NullPointerException();
			} 
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E poll() {
		monitor.lock();
		try {
			if (head != null) {
				E res = head.item;
				head = head.next;
				count--;
				return res;
			} else {
				return null;
			} 
		} finally {
			monitor.unlock();
		}
		
	}
	
	@Override
	public E take() throws InterruptedException {
		monitor.lock();
		try {
			while (isEmpty()) {
				consumerWaiting.await();
			}
			E res = head.item;
			head = head.next;
			count--;
			producerWaiting.signal();
			return res;
		} finally {
			monitor.unlock();
		}
	}
	@Override
	public void put(E e) throws InterruptedException {
		monitor.lock();
		try {
			while (remainingCapacity() == 0) {				
				producerWaiting.await();
				System.out.println("signalPut!");
			}
			Node<E> node = new Node<>(e);
			if (tail != null) {
				tail.next = node;
				tail = node;
			} else {
				head = node;
				tail = node;
			}
			count++;
			consumerWaiting.signal();
//			System.out.println("send");
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E element() {
		
		monitor.lock();
		try {
			if (head.item == null) {
				throw new NoSuchElementException();
			} else {
				return head.item;
			} 
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E peek() {
		monitor.lock();
		try {
			return head.item;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return head==null;
	}
	
	@Override
	public boolean add(E e) {
		monitor.lock();
		try {
			if (remainingCapacity() > 0) {
				Node<E> node = new Node<>(e);
				if (tail != null) {
					tail.next = node;
					tail = node;
				} else {
					head = node;
					tail = node;
				}
				count++;
				return true;
			} else {
				throw new IllegalStateException();
			} 
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public boolean offer(E e) {
		monitor.lock();
		try {
			if (remainingCapacity() > 0) {
				Node<E> node = new Node<>(e);
				if (tail != null) {
					tail.next = node;
					tail = node;
				} else {
					head = node;
					tail = node;
				}
				count++;
				return true;
			} else {
				return false;
			} 
		} finally {
			monitor.unlock();
		}
		
	}

	@Override
	public Iterator<E> iterator() {
		// no implement
		return null;
	}

	@Override
	public Object[] toArray() {
		// no implement
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// no imlement
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// no implement
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// no implement
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// no implement
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// no implement
		return false;
	}

	@Override
	public void clear() {
		// no implement
		
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		// no implement
		return false;
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		// no implement
		return null;
	}

	

	@Override
	public boolean remove(Object o) {
		// no implement
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// no implement
		return false;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		// no implement
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		// no implement
		return 0;
	}

}
