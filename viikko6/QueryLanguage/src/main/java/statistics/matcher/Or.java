package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {

	private final Matcher[] options;
	
	public Or(Matcher... options) {
		this.options = options;
	}
	
	@Override
	public boolean matches(Player p) {
		for (Matcher opt : options) {
			if (opt.matches(p)) {
				return true;
			}
		}
		return false;
	}

}
