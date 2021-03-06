import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LogTest {

	@Test
	void log2test() {
		assertEquals(1, Operations.log2(3));
		assertEquals(7, Operations.log2(128));
		assertEquals(8, Operations.log2(256));
		assertEquals(8, Operations.log2(400));
		assertEquals(10, Operations.log2(1024));
		assertEquals(10, Operations.log2(-1024));
		assertEquals(39, Operations.log2(549755813888L));
		assertEquals(62, Operations.log2(4611686018427388000L));
	}

}
