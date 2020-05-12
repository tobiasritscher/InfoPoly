package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import static ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config.CREDITS_TO_WIN;
import static ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config.NUMBER_DICE_SIDES;

public class Logic {
    private final static Logger logger = Logger.getLogger(Logic.class.getCanonicalName());
    private static IntegerProperty currentPlayerId = new SimpleIntegerProperty(0);
    private final GameBoard gameBoard;
    private final ArrayList<Player> players = new ArrayList<>();
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

    public Player getPlayer(int playerId) {
        return players.get(playerId);
    }

    /**
     * changes the currentPlayerId to the next player
     */
    private void switchToNextPlayer() {
        int nextPlayerId = (currentPlayerId.get() + 1) % players.size();
        currentPlayerId.setValue(nextPlayerId);

        if (playerHasToWait()) {
            switchToNextPlayer();
        }
    }

    private boolean playerHasToWait() {
        Player currentPlayer = getCurrentPlayer();
        boolean jumpPlayerTurn = false;
        int waitingRounds = currentPlayer.getRoundsWaiting();

        if (waitingRounds > 0) {
            waitingRounds--;
            new InformationalWindow("And we wait...", String.format("%S has to sit %d more rounds out...He knows why! Ask him!", currentPlayer.getName(), waitingRounds));
            currentPlayer.setRoundsWaiting(waitingRounds);
            jumpPlayerTurn = true;
        } else if (currentPlayer.isWaitingForScholarship()) {
            getScholarship(currentPlayer);
        }
        return jumpPlayerTurn;
    }

    private void getScholarship(Player currentPlayer) {
        final int SCHOLARSHIP_MONEY = 100;
        currentPlayer.alterMoney(SCHOLARSHIP_MONEY);
        currentPlayer.setWaitingForScholarship(false);
        new InformationalWindow("Poor guy!", String.format("You got some state help: %d", SCHOLARSHIP_MONEY));
    }

    private Player getCurrentPlayer() {
        if (players.isEmpty()) {
            throw new RuntimeException("invalid operation: no players!");
        }
        return players.get(currentPlayerId.getValue());
    }

    int calculateNextFieldId(int currentFieldId, int numberFieldToMove) {
        return (currentFieldId + numberFieldToMove) % gameBoard.getBoardSize();
    }

    /**
     * <p>Set new player position according to the given rolled number.</p>
     * <p></p>Checks if player has money or if he has won.</p>
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
        gameBoard.getField(fieldId).action(getCurrentPlayer());
        //todo remove below
        //gameBoard.getField(30).action(getCurrentPlayer());
        if ((getCurrentPlayer().getPosition() == gameBoard.getExamGameFieldId()) && (getCurrentPlayer().getRoundsWaiting() > 0)) {
            moveCurrentPlayerToField(gameBoard.getRepetitionGameFieldId());
        }
        verifyCurrentPlayerHasMoney();
        verifyCurrentPlayerIsWinner();
        if (!moveAgain) {
            switchToNextPlayer();
        }
    }

    private void moveCurrentPlayerToField(int fieldId) {
        getCurrentPlayer().setPosition(fieldId);
    }
    /*
    private void moveCurrentPlayerToField(int fieldId) {
        //todo remove test function
        if (getCurrentPlayer().getPosition() == 0) {
            getCurrentPlayer().setPosition(gameBoard.getExamGameFieldId());
        } else {
            getCurrentPlayer().setPosition(fieldId);
        }

    }*/

    /**
     * If current player has no money move to the @{@link Config.FieldType#START} field.
     */
    private void verifyCurrentPlayerHasMoney() {
        Player currentPlayer = getCurrentPlayer();
        if (!currentPlayer.isWaitingForScholarship() && currentPlayer.getMoney() <= 0) {
            new InformationalWindow("Broke!", "You have no money left!");
            currentPlayer.setPosition(gameBoard.getStartGameField().getFieldId());
            waitForScholarship();
        }
    }

    private void verifyCurrentPlayerIsWinner() {
        if (getCurrentPlayer().getCredits() >= CREDITS_TO_WIN) {
            new InformationalWindow("Bye bye dear school!", String.format("Congratulations %S! You just graduated from ZHAW!%nNow go and get a job in the real world!", getCurrentPlayer().getName()));
            //TODO: finish game
        }
    }


    private void waitForScholarship() {
        final int round = gameBoard.getStartGameField().SCHOLARSHIP_WAITING_TIME;
        new InformationalWindow("Scholarship!", String.format("You ran out of money so now you will apply for a scholarship.%nThat usually takes up to %d Weeks", round));
        getCurrentPlayer().setRoundsWaiting(round);
        getCurrentPlayer().setWaitingForScholarship(true);
    }

    private void transferMoneyOnRunThroughStartField() {
        int money = gameBoard.getStartGameField().PARENTS_HELP;

        new InformationalWindow("Parents help!", String.format("You got %d CHF from you parents!", money));
        getCurrentPlayer().alterMoney(money);
    }

    /**
     * Property getter, used by UI to update if a player turn has been updated.
     *
     * @return playerTurn IntegerProperty
     */
    public IntegerProperty getPlayerTurnProperty() {
        return currentPlayerId;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void rollDice() {
        Random random = new Random();
        boolean again = false;
        int firstDice;
        int secondDice;

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
