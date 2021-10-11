package telran.currencies.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		String url = "http://data.fixer.io/api/latest?access_key=3b856b88b3f6ca2b697e8933501b4da0&format=1";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(new URI(url)).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		Map<String, Double> rates = parseToMap(response.body());
		long now = System.currentTimeMillis()/1000;
//		System.out.println(now);
//		System.out.println(rates);
	}

	private static Map<String, Double> parseToMap(String body) {
		HashMap<String, Double> res = new HashMap<String, Double>();
		Matcher m = Pattern.compile("\\S+\\d").matcher(body);
		while (m.find()) {
			String[] rate = m.group().split(":");
			try {
				String key = rate[0].substring(1, rate[0].length()-1);
				res.put(key, Double.parseDouble(rate[1]));
			} catch(Exception e) {}
			
		}
		System.out.println(res.get("timestamp"));
		long t = res.remove("timestamp").longValue();
		System.out.println(t);
		return res;
	}
}
