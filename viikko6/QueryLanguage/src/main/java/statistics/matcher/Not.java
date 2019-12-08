package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {

	private final Matcher negated;
	
	public Not(Matcher negated) {
		this.negated = negated;
	}
	
	@Override
	public boolean matches(Player p) {
		return !negated.matches(p);
	}

}
