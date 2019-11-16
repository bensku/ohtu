package ohtu;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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
		
		Arrays.stream(players).filter(p -> p.getNationality().equals("FIN"))
				.sorted(Comparator.comparing(Player::getPoints).reversed())
				.forEach(p -> System.out.println(String.join(" ", p.getName(), p.getTeam(),
						"" + p.getGoals(), "+", "" + p.getAssists(), "=", "" + p.getPoints())));
	}
}