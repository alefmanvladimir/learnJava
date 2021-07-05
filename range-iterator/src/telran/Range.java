package telran;

import java.util.Iterator;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {

	int min;
	int max;

	Predicate<Integer> predicate;

	public Range(int _min, int _max) {
		min = _min;
		max = _max;
		setPredicate(n->true);
	}

	private class RangeIterator implements Iterator<Integer> {
		int current = min;

		@Override
		public boolean hasNext() {
			return current < max;
		}

		@Override
		public Integer next() {

			while (!predicate.test(current) && hasNext()) {
				current++;
			}
			
			if(current==Integer.MAX_VALUE) {
				return predicate.test(current) ? current : -1;
			}
			
			return predicate.test(current) ? current++ : -1;
		}

	}

	public void setPredicate(Predicate<Integer> _predicate) {
		predicate = _predicate;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

}
