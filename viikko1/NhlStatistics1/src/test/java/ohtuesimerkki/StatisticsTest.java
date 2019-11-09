package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

	private Statistics stats;
	
	@Before
	public void initStats() {
		Reader reader = () -> {
			List<Player> players = new ArrayList<>();
			players.add(new Player("David Pastrnak", "BOS", 13, 14));
			players.add(new Player("Leon Draisaitl", "EDM", 13, 13));
			players.add(new Player("Brad Marchand", "BOS", 8, 15));
			players.add(new Player("Scott Laughton", "PHI", 0, 3));
			players.add(new Player("Travis Konecny", "PHI", 6, 8));
			
			return players;
		};
		stats = new Statistics(reader);
	}
	
	@Test
	public void simpleSearch() {
		assertEquals("Leon Draisaitl", stats.search("Drais").getName());
		assertNull(stats.search("foo"));
	}
	
	@Test
	public void simpleTeam() {
		List<Player> ps = stats.team("PHI");
		ps.sort(Comparator.comparing(Player::getName));
		assertEquals("Scott Laughton", ps.get(0).getName());
		assertEquals("Travis Konecny", ps.get(1).getName());
	}
	
	@Test
	public void topScorers() {
		List<Player> ps = stats.topScorers(4);
		assertEquals("David Pastrnak", ps.get(0).getName());
		assertEquals("Leon Draisaitl", ps.get(1).getName());
		assertEquals("Brad Marchand", ps.get(2).getName());
		assertEquals("Travis Konecny", ps.get(3).getName());
		assertEquals("Scott Laughton", ps.get(4).getName());
	}
}
