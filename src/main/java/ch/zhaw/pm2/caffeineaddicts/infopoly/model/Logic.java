package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Logic {
    private int roundsWaiting;
    private static IntegerProperty playerTurn = new SimpleIntegerProperty(0);

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayersTurn() {
        return players.get(playerTurn.getValue());
    }

    public Player nextPlayer(ArrayList<Player> player) {
        playerTurn.setValue((1 + playerTurn.get()) % players.size());
        if (players.get(playerTurn.get()).getIsWaiting()) {
            playerTurn.setValue((1 + playerTurn.get()) % players.size());
        }
        return player.get(playerTurn.getValue());
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

    /**
     * Property getter, used by UI to update if a player turn has been updated.
     *
     * @return playerTurn IntegerProperty
     */
    public IntegerProperty getPlayerTurnProperty() {
        return playerTurn;
    }
}
