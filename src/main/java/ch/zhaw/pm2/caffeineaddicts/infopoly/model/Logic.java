package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.MainWindowController;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.GameField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Logic {
    private final static Logger logger = Logger.getLogger(Logic.class.getCanonicalName());
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


    public void switchToNextPlayer(ArrayList<Player> player) {
        currentPlayer.setValue((1 + currentPlayer.get()) % player.size());
        if (player.get(currentPlayer.get()).getIsWaiting()) {
            new InformationalWindow(player.get(currentPlayer.getValue()).getName() + " has to sit this round out...You know why...\nSmall carrot!");
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

    int calculateNextFieldId(int currentFieldId, int numberFieldToMove) {
        return (currentFieldId + numberFieldToMove) % gameBoard.getBoardSize();
    }

    /**
     * <p>Set new player position according to the given rolled number.</p>
     * <p></p>Checks if player has money of if he has won.</p>
     *
     * @param rolledNumber the number between 1 and {@link Config#NUMBER_DICES} * {@link Config#NUMBER_DICE_SIDES} inclusive.
     */
    public void movePlayer(int rolledNumber) {
        int fieldId = calculateNextFieldId(getCurrentPlayer().getPosition(), rolledNumber);
        logger.info(String.format("Rolled number: %d; Next field id: %d", rolledNumber, fieldId));
        moveCurrentPlayerToField(fieldId);
        verifyCurrentPlayerHasMoney();
        verifyCurrentPlayerIsWinner();
        switchToNextPlayer(players);
    }


    /**
     * Set current player position to the give field id.
     *
     * @param fieldId positive zero based integer number, field id where current player to be moved to.
     */
    public void moveCurrentPlayerToField(int fieldId) {
        GameField gameField = gameBoard.getField(fieldId);
        getCurrentPlayer().setPosition(fieldId);
        gameField.action(getCurrentPlayer());
    }

    /**
     * If current player has no money move to the @{@link Config.FieldType#START} field.
     */
    private void verifyCurrentPlayerHasMoney() {
        if (players.get(currentPlayer.getValue()).getMoney() <= 0) {
            new InformationalWindow("You are fucking broke mate.\nNext time you may want to sell you kidneys to get some money.\nFor now wait for help");
            waitForScholarship();
        }
    }

    private void verifyCurrentPlayerIsWinner() {
        if (players.get(currentPlayer.getValue()).getCredits() >= 180) {
            new InformationalWindow("Congratulations! You just graduated from ZHAW!\nNow go and get a job in the real world!");
        }
    }

    private void waitForScholarship() {
        new InformationalWindow("You ran out of money so now you will apply for a scholarship.\nThat usually takes up to 3 Weeks");
        getCurrentPlayer().setRoundsWaiting(3);
        if (getCurrentPlayer().getRoundsWaiting() == 0) {
            getCurrentPlayer().setMoney(100);
        }
    }


    private void repeating(Player currentPlayer){
        currentPlayer.setPosition(41);
        currentPlayer.setRoundsWaiting(3);
        if(currentPlayer.getRoundsWaiting() == 0){
            currentPlayer.setPosition(11);
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
