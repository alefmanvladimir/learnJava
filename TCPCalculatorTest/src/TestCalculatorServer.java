import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import telran.net.TcpJavaClient;

class TestCalculatorServer extends TcpJavaClient {

	protected TestCalculatorServer() throws Exception {
		super("localhost", 5000);
	}

	@Test
	void calculateTest() throws Exception {
		assertEquals(-19, (int)send("", "   3 +4/7 -20"));
		assertEquals(20, (int)send("", "   3 +4/7 *20"));
//		assertEquals(Integer.MAX_VALUE, (int)send("", "3 +4/0 *20"));
		try {
			int res = (int)send("", " * 25");
			fail("Illegal argument exception is required");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	@AfterEach
	void disconnect() throws IOException {
		close();
	}

}
