
import telran.net.ApplProtocolJava;
import telran.net.dto.RequestJava;
import telran.net.dto.ResponseCode;
import telran.net.dto.ResponseJava;

public class Protocol implements ApplProtocolJava {

	@Override
	public ResponseJava getResponse(RequestJava request) {
		try {
			
			String expression = (String)request.data;
			if(expression.matches(arithmeticExpression())) {
				return new ResponseJava(ResponseCode.OK, calculate(expression));
			} else {
				throw new IllegalArgumentException("Inccorect expression");
			}
		} catch(Exception e) {
			return new ResponseJava(ResponseCode.WRONG_REQUEST_DATA, e);
		}
	}
	
	private static String arithmeticExpression() {
		String delimetr = "\\s*";
		String number = "\\d+";
		String operator = "[+*/-]";
		String res = String.format("%1$s%2$s(%1$s%3$s%1$s%2$s)*%1$s", delimetr, number, operator);
		String operations = "[+*/-]";
		String intNumber = "\\s*\\d+\\s*";
		return res;
	}
	
	private static int calculate(String expression) {
		
		String [] numbers = expression.split("\\D+");
		String [] operators = expression.split("[^\\+\\-\\*\\/]+");
		int res = Integer.parseInt(numbers[1]);
		
		for(int i=0; i<operators.length; i++) {
			switch (operators[i]) {
			case "+": {
				res += Integer.parseInt(numbers[i+1]);
				break;
			}
			case "-": {
				res -= Integer.parseInt(numbers[i+1]);
				break;
			}
			case "*":{
				res *= Integer.parseInt(numbers[i+1]);
				break;
			}
			case "/":{
				res /= Integer.parseInt(numbers[i+1]);
				break;
			}
			}
		}
		
		return res;
	}

}
