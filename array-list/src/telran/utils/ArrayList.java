package telran.utils;

import java.util.Arrays;

@SuppressWarnings("unchecked")

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T array[];
	int size = 0;

	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void add(T obj) {
		if (size >= array.length) {
			allocate();
		}
		array[size++] = obj;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean add(T obj, int index) {
		if (index > size || index < 0) {
			return false;
		}

		if (size >= array.length) {
			allocate();
		}

		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
		return true;
	}

	@Override
	public T get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return array[index];
	}

	private boolean remove(int index) {
		if (index >= size || index < 0) {
			return false;
		}

		System.arraycopy(array, index + 1, array, index, size - index);
		size--;
		return true;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !array[index].equals(pattern)) {
			index++;
		}
		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0 && !array[index].equals(pattern)) {
			index--;
		}
		return index >= 0 ? index : -1;
	}

	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		return remove(index);
	}

	@Override
	public void addAll(List<T> objects) {
		for (int i = 0; i < objects.size(); i++) {
			add(objects.get(i));
		}

	}

	@Override
	public boolean removeAll(List<T> patterns) {
		boolean res = false;
		for (int i = 0; i < patterns.size(); i++) {
			if (remove(patterns.get(i))) {
				res = true;
			}
		}
		return res;
	}

	@Override
	public boolean retainAll(List<T> patterns) {
		boolean res = false;
		for (int i = 0; i < size(); i++) {
			if (patterns.indexOf(get(i)) == -1) {
				remove(get(i));
				res = true;
			}
		}
		return res;
	}

	@Override
	public T set(T object, int index) {
		if (index > 0 && index < size()) {
			array[index] = object;
			return array[index];
		}
		return null;
	}

	@Override
	public boolean swap(int index1, int index2) {
		if ((index1 > 0 && index1 < size()) && (index2 > 0 && index2 < size())) {
			T obj = get(index1);
			set(get(index2), index1);
			set(obj, index2);
			return true;
		}
		return false;
	}

}
