package telran.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedList<T> extends AbstractList<T> {
	static private class Node<T> {
		public T obj;
		public Node<T> next;
		public Node<T> prev;

		public Node(T obj) {
			this.obj = obj;
		}

	}

	private Node<T> head;
	private Node<T> tail;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			T res = current.obj;
			current = current.next;
			return res;
		}

		@Override
		public void remove() {
			removeNode(current == null ? tail : current.prev);
		}

	}

	@Override
	public void add(T obj) {
		Node<T> node = new Node<>(obj);
		if (tail != null) {
			tail.next = node;
			node.prev = tail;
			tail = node;
		} else {
			head = tail = node;
		}
		size++;
	}

	@Override
	public boolean add(T obj, int index) {
		if (index < 0 || index > size) {
			return false;
		} else {

			if (index == 0) {
				addHead(obj);
			} else if (index == size) {
				add(obj);
			} else {
				Node<T> currentNode = getNodeIndex(index);
				Node<T> newNode = new Node<>(obj);

				newNode.next = currentNode;
				newNode.prev = currentNode.prev;
				currentNode.prev.next = newNode;
				currentNode.prev = newNode;
				size++;
			}

			return true;
		}
	}

	private void addHead(T obj) {
		Node<T> newHead = new Node<>(obj);
		head.prev = newHead;
		newHead.next = head;
		head = newHead;
		size++;
	}

	@Override
	public T get(int index) {
		return isValidIndex(index) ? getNodeIndex(index).obj : null;
	}

	@Override
	public boolean remove(int index) {
		if (!isValidIndex(index)) {
			return false;
		}

		return removeNode(getNodeIndex(index));
	}

	private void removeHead() {
		head.next.prev = null;
		head = head.next;
	}

	private void removeTail() {
		tail.prev.next = null;
		tail = tail.prev;
	}

	private boolean removeNode(Node<T> node) {
		if (head == tail) {
			head = tail = null;
			size = 0;
			return true;
		}

		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}

		size--;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> node = getNode(pattern);
		return node != null ? removeNode(node) : false;
	}

	private Node<T> getNode(T pattern) {
		Node<T> currentNode = head;
		while (currentNode.next != null && !currentNode.obj.equals(pattern)) {
			currentNode = currentNode.next;
		}
		return currentNode.obj.equals(pattern) ? currentNode : null;
	}

	@Override
	public void addAll(List<T> objects) {
		if (objects instanceof LinkedList && this != objects) {
			LinkedList<T> linkedList = (LinkedList<T>) objects;
			addAllFromLinkedList(linkedList);
		} else {
			super.addAll(objects);
		}
	}

	private void addAllFromLinkedList(LinkedList<T> objects) {
		tail.next = objects.head;
		objects.head.prev = tail;
		tail = objects.tail;
		size += objects.size();
	}

	@Override
	public T set(T object, int index) {
		T res = null;

		if (isValidIndex(index)) {
			Node<T> node = getNodeIndex(index);
			res = node.obj;
			node.obj = object;
		}
		return res;
	}

	@Override
	public boolean swap(int index1, int index2) {
		if (isValidIndex(index1) && isValidIndex(index2) && index1 != index2) {
			Node<T> currentNode = head;
			int maxIndex = index1 - index2 > 0 ? index1 : index2;
			int minIndex = index1 - index2 > 0 ? index2 : index1;
			Node<T> firstNode = new Node<>(null);
			Node<T> secondNode;

			for (int i = 0; i < maxIndex; i++) {
				if (i == minIndex) {
					firstNode = currentNode;
				}
				currentNode = currentNode.next;
			}
			secondNode = currentNode;

			T obj = secondNode.obj;
			secondNode.obj = firstNode.obj;
			firstNode.obj = obj;
			return true;
		}
		return false;

	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		Node<T> currentNode = head;
		int index = 0;
		while (currentNode.next != null && !predicate.test(currentNode.obj)) {
			currentNode = currentNode.next;
			index++;
		}
		return predicate.test(currentNode.obj) ? index : -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		Node<T> currentNode = tail;
		int index = size - 1;
		while (currentNode.prev != null && !predicate.test(currentNode.obj)) {
			currentNode = currentNode.prev;
			index--;
		}
		return predicate.test(currentNode.obj) ? index : -1;
	}

	private boolean conditionRemoving(T current, Predicate<T> predicate) {
		return predicate.test(current);
	}

//	@Override
//	public boolean removeIf(Predicate<T> predicate) {
//		Node<T> currentNode = head;
//		boolean res = false;
//		while (currentNode != null) {
//			if (conditionRemoving(currentNode.obj, predicate)) {
//				removeNode(currentNode);
//				res = true;
//			}
//			currentNode = currentNode.next;
//		}
//
//		return res;
//	}

	@Override
	public void clean() {
		head = tail = null;
		size = 0;
	}

	private Node<T> getNodeRtL(int index) {
		Node<T> currentNode = tail;
		for (int i = size - 1; i > index; i--) {
			currentNode = currentNode.prev;
		}
		return currentNode;
	}

	private Node<T> getNodeLtR(int index) {
		Node<T> currentNode = head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	private Node<T> getNodeIndex(int index) {
		return index > size / 2 ? getNodeRtL(index) : getNodeLtR(index);
	}

	@Override
	public void sort(Comparator<T> comp) {
		T arr[] = toArray();
		Arrays.sort(arr, comp);
		fillFromArray(arr);
	}

	private void fillFromArray(T[] arr) {
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			current.obj = arr[i];
			current = current.next;
		}
	}

	@SuppressWarnings("unchecked")
	private T[] toArray() {
		T[] res = (T[]) new Object[size];
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			res[i] = current.obj;
			current = current.next;
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

}
