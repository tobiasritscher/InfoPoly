package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.StartGameField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private final BooleanProperty gameWasWon = new SimpleBooleanProperty();
    IntegerProperty currentDiceRoll = new SimpleIntegerProperty();

    public Logic() {
        gameBoard = new GameBoard();
    }

    static boolean jumpedOverField(int fieldId, int startPosition, int endPosition) {
        boolean result;
        if (startPosition < endPosition && (startPosition < fieldId && fieldId < endPosition)) {
            result = true;
        } else if (startPosition > endPosition && (startPosition < fieldId && fieldId > endPosition || startPosition > fieldId && fieldId < endPosition)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    static int calculateNextFieldId(int boardSize, int currentFieldId, int numberFieldToMove) {
        return (currentFieldId + numberFieldToMove) % boardSize;
    }

    static int calculateNextPlayerId(int numberPlayers, int currentPlayerId) {
        return (currentPlayerId + 1) % numberPlayers;
    }

    public BooleanProperty getGameWasWonProperty() {
        return gameWasWon;
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
        final int nextPlayerId = calculateNextPlayerId(players.size(), players.indexOf(getCurrentPlayer()));
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
            new InformationalWindow("Sit down!", String.format("%S has to sit out %d more rounds ...He knows why! Yes! Tell them!", currentPlayer.getName(), waitingRounds));
            waitingRounds--;
            currentPlayer.setRoundsWaiting(waitingRounds);
            jumpPlayerTurn = true;
        } else if (currentPlayer.isWaitingForScholarship()) {
            getScholarship(currentPlayer);
        }
        return jumpPlayerTurn;
    }

    private void getScholarship(Player currentPlayer) {
        final int money = StartGameField.SCHOLARSHIP_MONEY;
        new InformationalWindow("Poor guy!", String.format("You got some money from the state: %d", money));
        currentPlayer.alterMoney(money);
        currentPlayer.setWaitingForScholarship(false);
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayerId.getValue());
    }

    /**
     * <p>Set new player position according to the given rolled number.</p>
     * <p></p>Checks if player has money or if he has won.</p>
     *
     * @param rolledNumber the number between 1 and {@link Config#NUMBER_DICES} * {@link Config#NUMBER_DICE_SIDES} inclusive.
     */
    public void movePlayer(int rolledNumber, boolean moveAgain) {
        final int nextPosition = calculateNextFieldId(gameBoard.getBoardSize(), getCurrentPlayer().getPosition(), rolledNumber);
        final int startFieldId = gameBoard.getStartGameField().getFieldId();
        if (jumpedOverField(startFieldId, getCurrentPlayer().getPosition(), nextPosition)) {
            parentsHelp();
        }
        if (getCurrentPlayer().isWorking()) {
            final int currentPlayerJobFieldId = getCurrentPlayer().getJob().getFieldId();
            if ((getCurrentPlayer().getPosition() < currentPlayerJobFieldId) && (currentPlayerJobFieldId < nextPosition)) {
                payday();
            }
        }
        logger.info(String.format("Rolled number: %d; Next field id: %d", rolledNumber, nextPosition));
        moveCurrentPlayerToField(nextPosition);
        gameBoard.getField(nextPosition).action(getCurrentPlayer());
        if ((getCurrentPlayer().getPosition() == gameBoard.getExamGameFieldId()) && (getCurrentPlayer().getRoundsWaiting() > 0)) {
            repetition();
        }
        if (getCurrentPlayer().getMoney() < 0 && !getCurrentPlayer().isWaitingForScholarship()) {
            broke();
        }
        if (getCurrentPlayer().getCredits() >= CREDITS_TO_WIN) {
            winner();
        }
        if (!gameWasWon.get() && !moveAgain) {
            switchToNextPlayer();
        }
    }

    private void repetition() {
        moveCurrentPlayerToField(gameBoard.getRepetitionGameFieldId());
    }

    private void moveCurrentPlayerToField(int fieldId) {
        getCurrentPlayer().setPosition(fieldId);
    }

    /**
     * If current player has no money move to the @{@link Config.FieldType#START} field.
     */
    private void broke() {
        new InformationalWindow("Broke!", String.format("%S, you have no money left!", getCurrentPlayer().getName()));
        getCurrentPlayer().setPosition(gameBoard.getStartGameField().getFieldId());
        scholarship();
    }

    private void winner() {
        new InformationalWindow("Bye bye dear school!", String.format("Congratulations %S! You just graduated from ZHAW!%nNow go and get a job in the real world!", getCurrentPlayer().getName()));
        gameWasWon.set(true);
    }

    private void payday() {
        final int wage = getCurrentPlayer().getJob().BASE_WAGE;
        new InformationalWindow("Payday!", String.format("Here are your money for the last week: %d.-CHF.", wage));
        getCurrentPlayer().alterMoney(wage);
    }

    private void scholarship() {
        final int round = gameBoard.getStartGameField().SCHOLARSHIP_WAITING_TIME;
        new InformationalWindow("Scholarship!", String.format("%S, you ran out of money so now you will apply for a scholarship.%nThat usually takes up to %d Weeks", getCurrentPlayer().getName(), round));
        //todo mb random time to wait "up to" ...
        getCurrentPlayer().setRoundsWaiting(round);
        getCurrentPlayer().setWaitingForScholarship(true);
    }

    private void parentsHelp() {
        final int moneyHelp = gameBoard.getStartGameField().PARENTS_HELP;
        new InformationalWindow("Parents help!", String.format("%S, you got %d CHF from you parents!", getCurrentPlayer().getName(), moneyHelp));
        getCurrentPlayer().alterMoney(moneyHelp);
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
            new InformationalWindow(String.format("Wow! Has rolled a double!"), String.format("%S can move again.", getCurrentPlayer().getName()));
            again = true;
        }
        movePlayer(rolledNumber, again);
    }

    public IntegerProperty getCurrentDiceRollProperty() {
        return currentDiceRoll;
    }
}
