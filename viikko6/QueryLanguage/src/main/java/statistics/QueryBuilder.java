package statistics;

import java.util.ArrayList;
import java.util.List;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {

	public static QueryBuilder begin() {
		return new QueryBuilder();
	}
	
	public static Matcher oneOf(Matcher... options) {
		return new Or(options);
	}
	
	
	private List<Matcher> matchers;

	private QueryBuilder() {
		this.matchers = new ArrayList<>();
	}
		
	public QueryBuilder playsIn(String team) {
		matchers.add(new PlaysIn(team));
		return this;
	}
	
	public QueryBuilder hasAtLeast(int value, String category) {
		matchers.add(new HasAtLeast(value, category));
		return this;
	}
	
	public QueryBuilder hasFewerThan(int value, String category) {
		matchers.add(new HasFewerThan(value, category));
		return this;
	}
	
	public Matcher build() {
		if (matchers.isEmpty()) {
			return new All();
		} else if (matchers.size() == 1) {
			return matchers.get(0);
		} else {
			return new And(matchers.toArray(new Matcher[0]));
		}
	}
}
