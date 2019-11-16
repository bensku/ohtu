package ohtu;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;

public class Main {
	
	public static void main(String... args) throws ClientProtocolException, IOException {
		String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
		
		String bodyText = Request.Get(url).execute().returnContent().asString();
		        
		System.out.println("json-muotoinen data:");
		System.out.println( bodyText );
		
		Gson mapper = new Gson();
		Player[] players = mapper.fromJson(bodyText, Player[].class);
		
		System.out.println("Oliot:");
		for (Player player : players) {
		    System.out.println(player);
		}
	}
}