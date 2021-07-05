package telran;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {
	int q = 1;//Integer.MAX_VALUE-10;
	int q1 = 37;//Integer.MAX_VALUE;

	Range range = new Range(q, q1);
	Range range2 = new Range(q, q1);
	Range range3 = new Range(q, q1);
	Range range4 = new Range(-5, 3);
	@BeforeEach
	void setUp() throws Exception {
		range.setPredicate(n -> n % 2 == 0);
		range2.setPredicate(n -> n % 2 != 0);
		range4.setPredicate(n -> n > 0);
	}

	@Test
	void test() {

		for (int num : range) {
			System.out.print(num + " ");
		}

		System.out.println(" - even numbers");

		for (int num : range2) {
			System.out.print(num + " ");
		}
		
		System.out.println(" - odd numbers ");

		for (int num : range3) {
			System.out.print(num + " ");
		}
		
		System.out.println(" - all");

		for (int num : range4) {
			System.out.print(num + " ");
		}
		
		System.out.println(" - integers");
	}

}
