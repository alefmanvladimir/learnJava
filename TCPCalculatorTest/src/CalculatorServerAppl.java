
import telran.net.*;
public class CalculatorServerAppl {
	public static void main(String[] args) {
		TcpJavaServer server = new TcpJavaServer(5000, new Protocol());
		server.run();
	}


}
