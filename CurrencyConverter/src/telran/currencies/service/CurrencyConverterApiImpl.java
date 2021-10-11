package telran.currencies.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyConverterApiImpl extends AbstractCurrencyConverter {

	static String url = "http://data.fixer.io/api/latest?access_key=3b856b88b3f6ca2b697e8933501b4da0&format=1";
	static HttpClient client = HttpClient.newHttpClient();
	
	static long lastRefresh;
	static int refreshTime = 60;
	static long timestamp;
	
	public static CurrencyConverter getCurrencyConverter() {
		return new CurrencyConverterApiImpl(getRates());
	}
	
	private static Map<String, Double> getRates() {
		Map<String, Double> data = null;
		try {
			HttpRequest request = HttpRequest.newBuilder(new URI(url)).build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			data = parseRates(response.body());
			lastRefresh = System.currentTimeMillis()/1000;
			
		} catch (URISyntaxException e) {
			System.out.println("request error");
			System.out.println(e.getMessage());
		} catch (IOException | InterruptedException e) {
			System.out.println("responce error");
			System.out.println(e.getMessage());
		}
		return data;
	}

	private static Map<String, Double> parseRates(String body) {
		HashMap<String, Double> res = new HashMap<String, Double>();
		Matcher m = Pattern.compile("\\S+\\d").matcher(body);
		while (m.find()) {
			String[] rate = m.group().split(":");
			try {
				String key = rate[0].substring(1, rate[0].length()-1);
				res.put(key, Double.parseDouble(rate[1]));
			} catch(Exception e) {}
		}
		timestamp = res.remove("timestamp").longValue();
		return res;
	}
	
	protected CurrencyConverterApiImpl(Map<String, Double> rates) {
		super(rates);
	}
	
	@Override
	protected void refresh() {
		long now = System.currentTimeMillis()/1000;
		if(now>=lastRefresh+refreshTime) {
			rates = getRates();
		}
	}

}
