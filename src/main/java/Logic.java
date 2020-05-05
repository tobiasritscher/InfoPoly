import java.util.ArrayList;

public class Logic {
    private int roundsWaiting;
    private static int playerTurn = 0;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayersTurn() {
        return players.get(playerTurn);
    }

    public Player nextPlayer(ArrayList<Player> player) {
        playerTurn = (++playerTurn) % players.size();
        if (players.get(playerTurn).getIsWaiting()) {
            playerTurn = (++playerTurn) % players.size();
        }
        return player.get(playerTurn);
    }

    public void setRoundsWaiting(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Makes no sense to wait negative amount dude");
        }
        roundsWaiting = amount;
    }

    public int getRoundsWaiting() {
        return roundsWaiting;
    }
}
