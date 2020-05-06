package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Logic {
    private int roundsWaiting;
    private static IntegerProperty currentPlayer = new SimpleIntegerProperty(0);

    public ArrayList<Player> getPlayers() {
        return players;
    }

    GameBoard gameBoard = new GameBoard();

    private ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayersTurn() {
        return players.get(currentPlayer.getValue());
    }

    public Player nextPlayer(ArrayList<Player> player) {
        currentPlayer.setValue((1 + currentPlayer.get()) % players.size());
        if (players.get(currentPlayer.get()).getIsWaiting()) {
            currentPlayer.setValue((1 + currentPlayer.get()) % players.size());
        }
        return player.get(currentPlayer.getValue());
    }

    public void setRoundsWaiting(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Makes no sense to wait negative amount dude");
        }
        roundsWaiting = amount;
    }

    public void movePlayer(int rolledNumber){
       players.get(currentPlayer.getValue()).setPosition((players.get(currentPlayer.getValue()).getPosition() + rolledNumber)%gameBoard.getBoardSize());
       Config.FieldType fieldType = gameBoard.getFieldType(players.get(currentPlayer.getValue()).getPosition());
       switch(fieldType){
           case MODULE: processModule();
               break;
           case STARTUP: startup();
               break;
           case JOB:
               break;
           case CHANCE:
               break;
           case START:
               break;
           case FEE_TYPE_ONE:
               break;
           case FEE_TYPE_TWO:
               break;
           case FEE_TYPE_THREE:
               break;
           case REPETITION:
               break;
           case EXAM:
               break;
       }
    }

    private void processModule(){

    }

    private void startup(){

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
        return currentPlayer;
    }
}
