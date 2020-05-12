package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.GameField;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.StartGameField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import static ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config.NUMBER_DICE_SIDES;

public class Logic {
    private final static Logger logger = Logger.getLogger(Logic.class.getCanonicalName());
    private static IntegerProperty currentPlayer = new SimpleIntegerProperty(0);
    private final GameBoard gameBoard;
    private ArrayList<Player> players = new ArrayList<>();
    public IntegerProperty currentDiceRollProperty() {
        return currentDiceRoll;
    }

    IntegerProperty currentDiceRoll = new SimpleIntegerProperty();


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
        if (getCurrentPlayer().getIsWaiting()) {
            new InformationalWindow("Small carrot!",getCurrentPlayer().getName() + " has to sit this round out...You know why!");
            getCurrentPlayer().setRoundsWaiting(getCurrentPlayer().getRoundsWaiting() - 1);
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
    public void movePlayer(int rolledNumber, boolean moveAgain) {
        int fieldId = calculateNextFieldId(getCurrentPlayer().getPosition(), rolledNumber);
        if (getCurrentPlayer().getPosition() > fieldId) {
            transferMoneyOnRunThroughStartField();
        }
        logger.info(String.format("Rolled number: %d; Next field id: %d", rolledNumber, fieldId));
        moveCurrentPlayerToField(fieldId);
        verifyCurrentPlayerHasMoney();
        verifyCurrentPlayerIsWinner();
        if (!moveAgain) {
            switchToNextPlayer(players);
        }
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
            new InformationalWindow("Broke!", "You have no money left!");
            waitForScholarship();
        }
    }

    private void verifyCurrentPlayerIsWinner() {
        if (players.get(currentPlayer.getValue()).getCredits() >= 180) {
            new InformationalWindow("Bye bye dear school!", "Congratulations! You just graduated from ZHAW!\nNow go and get a job in the real world!");
        }
    }

    private void waitForScholarship() {
        new InformationalWindow("Scholarship!", "You ran out of money so now you will apply for a scholarship.\nThat usually takes up to 3 Weeks");
        getCurrentPlayer().setRoundsWaiting(3);
        if (getCurrentPlayer().getRoundsWaiting() == 0) {
            getCurrentPlayer().setMoney(100);
        }
    }

    private void transferMoneyOnRunThroughStartField() {
        int money = ((StartGameField) gameBoard.getStartGameField()).getBaseScholarship();

        new InformationalWindow("Parents help!", String.format("You got %d CHF from you parents!", money));
        getCurrentPlayer().alterMoney(money);
    }

    private void repeating(Player currentPlayer) {
        currentPlayer.setPosition(41);
        currentPlayer.setRoundsWaiting(3);
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

    public void rollDice() {
        Random random = new Random();
        boolean again = false;
        int firstDice;
        int secondDice;

        //IntegerProperty for final dice roll. Needed for UI

        firstDice = random.nextInt(NUMBER_DICE_SIDES) + 1;
        secondDice = random.nextInt(NUMBER_DICE_SIDES) + 1;
        int rolledNumber = firstDice + secondDice;
        currentDiceRoll.setValue(rolledNumber);

        if (firstDice == secondDice) {
            new InformationalWindow("Wow! Rolled a double!", "You can move again.");
            again = true;
        }
        movePlayer(rolledNumber, again);
    }

    public IntegerProperty getCurrentDiceRollProperty() {
        return currentDiceRoll;
    }
}
