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
		if(size>=array.length) {
			allocate();
		} 
		array[size++] = obj;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean add(T obj, int index) {
		if(index>size || index<0) {
			return false;
		}
		
		if(size>=array.length) {
			allocate();
		} 
		
		System.arraycopy(array, index, array, index+1, size-index);
		array[index] = obj;
		size++;
		return true;
	}

	@Override
	public T get(int index) {
		if(index>=size || index<0) {
			return null;
		}
		return array[index];
	}

	@Override
	public boolean remove(int index) {
		if(index>=size || index<0) {
			return false;
		} 
		
		System.arraycopy(array, index+1, array, index, size-index);
		size--;
		return true;
		
	}

	@Override
	public int size() {
		return size;
	}

}
