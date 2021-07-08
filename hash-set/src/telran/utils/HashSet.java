package telran.utils;

import java.util.Iterator;

public class HashSet<T> implements Set<T> {

	private int size;
	private LinkedList<T>[] hashTable;
	private double factor = 0.75;
	private static final int DEFAULT_TABLE_LENGTH = 16;

	@SuppressWarnings("unchecked")
	public HashSet(int initialTableLength) {
		hashTable = new LinkedList[initialTableLength];
	}

	public HashSet() {
		this(DEFAULT_TABLE_LENGTH);
	}

	private class SetIterator implements Iterator<T> {
		int index = 0;
		int linkedIndex = 0;
		Iterator<T> it;
		int count = 0;
		@Override
		public boolean hasNext() {
			return count < size;
		}

		@Override
		public T next() {
			while (hasNext() && (hashTable[index] == null || hashTable[index].size()==0)) {
				index++;
				
			}
			
			if (hasNext()) {
				if(it==null) {
					it = hashTable[index].iterator();
				}
				
				if(it.hasNext()) {
					T obj = it.next();
					count++;
					if(!it.hasNext()) {
						index++;
						it = null;
					}
					return obj;
					
				}

			}
			return null;
		}
		
		public void remove() {
			if(linkedIndex>0) {
				hashTable[index].remove(--linkedIndex);
			}  else {
				hashTable[--index].remove(linkedIndex);
			}
			
			size--;
			count--;
						
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new SetIterator();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (size >= hashTable.length * factor) {
			recreateTable();
		}

		int index = getIndex(obj);

		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}

		if (!hashTable[index].contains(obj)) {
			hashTable[index].add(obj);
			res = true;
			size++;
		}

		return res;

	}

	private void recreateTable() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for (LinkedList<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
	}

	private int getIndex(T obj) {
		int hashCode = obj.hashCode();
		int res = Math.abs(hashCode) % hashTable.length;
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		if (contains(pattern)) {
			int index = getIndex(pattern);
			hashTable[index].remove(pattern);
			size--;
			res = true;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getIndex(pattern);
		return hashTable[index] != null && hashTable[index].contains(pattern);
	}

	@Override
	public void clean() {
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = null;
		}
		size = 0;
	}

}
