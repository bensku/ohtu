package ohtu;

public class TennisGame {
	
	private static class Player {
		
		public final String name;
		public int score;
		
		public Player(String name) {
			this.name = name;
			this.score = 0;
		}
	}
    
    private final Player[] players;

    public TennisGame(String player1Name, String player2Name) {
        this.players = new Player[] {new Player(player1Name), new Player(player2Name)};
    }

    public void wonPoint(String playerName) {
        for (Player p : players) {
        	if (p.name.equals(playerName)) {
        		p.score++;
        	}
        }
    }

    public String getScore() {
        int score1 = players[0].score;
        int score2 = players[1].score;
        
        // Both players have equal score
        if (score1 == score2) {
            return scoreToString(score1) + "-All";
        } else if (score1 >= 4 || score2 >= 4) {
            int diff = score1 - score2;
            if (diff == 1) return "Advantage player1";
            else if (diff == -1) return "Advantage player2";
            else if (diff >= 2) return "Win for player1";
            else return "Win for player2";
        } else {
        	return scoreToString(score1) + "-" + scoreToString(score2);
        }
    }
    
    private String scoreToString(int score) {
    	switch (score) {
	        case 0:
	            return "Love";
	        case 1:
	            return "Fifteen";
	        case 2:
	            return "Thirty";
	        case 3:
	            return "Forty";
	        default:
	            return "Deuce";
    	}
    }
}