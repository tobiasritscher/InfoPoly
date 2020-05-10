package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.GameField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Logic {
    private static IntegerProperty currentPlayer = new SimpleIntegerProperty(0);
    private final GameBoard gameBoard;
    private ArrayList<Player> players = new ArrayList<>();

    public Logic() {
        gameBoard = new GameBoard();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayersTurn() {
        return players.get(currentPlayer.getValue());
    }

    public void nextPlayer(ArrayList<Player> player) {
        currentPlayer.setValue((1 + currentPlayer.get()) % player.size());
        if (player.get(currentPlayer.get()).getIsWaiting()) {
            new InformationalWindow(player.get(currentPlayer.getValue()).getName() + " has to sit this round out...You know why!");
            player.get(currentPlayer.getValue()).setRoundsWaiting(player.get(currentPlayer.getValue()).getRoundsWaiting() - 1);
            currentPlayer.setValue((1 + currentPlayer.get()) % player.size());

        }
    }

    private Player getCurrentPlayer() {
        if (players == null || players.isEmpty()) {
            throw new RuntimeException("invalid operation: no players!");
        }
        return players.get(currentPlayer.getValue());
    }

    private int calculateNextFieldId(int currentFieldId, int numberFieldToMove) {
        return (currentFieldId + numberFieldToMove) % Config.BOARD_SIZE;
    }

    /**
     * @param rolledNumber the number between 0 and {@link Config inclusive
     */
    public void movePlayer(int rolledNumber) {
        int fieldId = calculateNextFieldId(getCurrentPlayer().getPosition(), rolledNumber);
        getPlayersTurn().move(fieldId);
        makeAction(fieldId);

        verifyCurrentPlayerHasMoney();
        verifyCurrentPlayerIsWinner();

        nextPlayer(players);
    }

    /**
     * @param fieldId positive zero based integer number, field id where current player to be moved to.
     */
    public void makeAction(int fieldId) {
        GameField gameField = gameBoard.getField(fieldId);
        getCurrentPlayer().setPosition(fieldId);
        gameField.action(getCurrentPlayer());
    }

    /**
     * If current player has no money move to the @{@link Config.FieldType#START} field.
     */
    private void verifyCurrentPlayerHasMoney() {
        if (players.get(currentPlayer.getValue()).getMoney() <= 0) {
            new InformationalWindow("You are fucking broke mate. Next time you may want to sell you kidneys to get some money. For now wait for help");
            waitForScholarship();
        }
    }

    private void verifyCurrentPlayerIsWinner() {
        if (players.get(currentPlayer.getValue()).getCredits() >= 180) {
            new InformationalWindow("Congratulations! You just graduated from ZHAW! Now go and get a job in the real world!");
        }
    }

    private void waitForScholarship() {
        new InformationalWindow("You ran out of money so now you will apply for a scholarship. That usually takes up to 3 Weeks");
        getCurrentPlayer().setRoundsWaiting(3);
        if (getCurrentPlayer().getRoundsWaiting() == 0) {
            getCurrentPlayer().setMoney(100);
        }
    }


    /**
     * Property getter, used by UI to update if a player turn has been updated.
     *
     * @return playerTurn IntegerProperty
     */
    public IntegerProperty getPlayerTurnProperty() {
        return currentPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
