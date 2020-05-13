package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.JobGameField;
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

/**
 * Handles the logic between the players and the board
 */
public class Logic {
    private final static Logger logger = Logger.getLogger(Logic.class.getCanonicalName());
    private static final IntegerProperty currentPlayerId = new SimpleIntegerProperty(0);
    private final GameBoard gameBoard;
    private final ArrayList<Player> players = new ArrayList<>();
    private final BooleanProperty gameWasWon = new SimpleBooleanProperty();
    IntegerProperty currentDiceRoll = new SimpleIntegerProperty();

    public Logic() {
        gameBoard = new GameBoard();
    }

    /**
     * checks if a player jumped over start to give him 100 CHF
     *
     * @param fieldId       player's current position
     * @param startPosition where the player begins
     * @param endPosition   the player's new position
     * @return true if player passed the start field
     */
    static boolean jumpedOverField(int fieldId, int startPosition, int endPosition) {
        boolean result;

        if ((startPosition < fieldId && fieldId < endPosition)) {
            result = true;
        } else {
            result = startPosition >
                    endPosition && (startPosition < fieldId || startPosition > fieldId && fieldId < endPosition);
        }

        return result;
    }

    /**
     * calculates the new position of the player
     *
     * @param boardSize         all fields of the board
     * @param currentFieldId    the current position of the player
     * @param numberFieldToMove the rolled number for the player to move
     * @return the new field for the player
     */
    static int calculateNextFieldId(int boardSize, int currentFieldId, int numberFieldToMove) {
        return (currentFieldId + numberFieldToMove) % boardSize;
    }

    /**
     * calculates the next player
     *
     * @param numberPlayers   total number of players
     * @param currentPlayerId the current player
     * @return the next player
     */
    static int calculateNextPlayerId(int numberPlayers, int currentPlayerId) {
        return (currentPlayerId + 1) % numberPlayers;
    }

    public IntegerProperty getCurrentDiceRollProperty() {
        return currentDiceRoll;
    }

    public BooleanProperty getGameWasWonProperty() {
        return gameWasWon;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public IntegerProperty getPlayerTurnProperty() {
        return currentPlayerId;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayer(int playerId) {
        return players.get(playerId);
    }

    public static void setCurrentPlayerId(int currentPlayerId) {
        Logic.currentPlayerId.set(currentPlayerId);
    }

    /**
     * changes the currentPlayerId to the next player
     */
    void switchToNextPlayer() {
        if (!gameWasWon.get()) {
            final int nextPlayerId = calculateNextPlayerId(players.size(), players.indexOf(getCurrentPlayer()));

            currentPlayerId.setValue(nextPlayerId);

            if (playerHasToWait()) {
                switchToNextPlayer();
            }
        }
    }

    /**
     * defines if a player has to wait a certain amount of turns
     *
     * @return if the player has to wait
     */
    private boolean playerHasToWait() {
        Player currentPlayer = getCurrentPlayer();
        boolean jumpPlayerTurn = false;
        int waitingRounds = currentPlayer.getRoundsWaiting();

        if (waitingRounds > 0) {
            new InformationalWindow("You have to wait!", String.format("%S has to sit out %d more rounds.",
                    currentPlayer.getName(), waitingRounds));
            currentPlayer.setRoundsWaiting(waitingRounds - 1);
            jumpPlayerTurn = true;
        } else if (currentPlayer.isWaitingForScholarship()) {
            getScholarship(currentPlayer);
        }

        return jumpPlayerTurn;
    }

    /**
     * Gives a player his scholarship money after waiting
     *
     * @param currentPlayer the player that needs the scholarship
     */
    private void getScholarship(Player currentPlayer) {
        final int money = StartGameField.getScholarshipMoney();
        new InformationalWindow("Received scholarship!", String.format(
                "You got some money from the public: %d", money));
        currentPlayer.alterMoney(money);
        currentPlayer.setWaitingForScholarship(false);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerId.getValue());
    }

    /**
     * <p>Set new player position according to the given rolled number.</p>
     * <p></p>Checks if player has money or if he has won.</p>
     *
     * @param rolledNumber the number between 1 and {@link Config#NUMBER_DICES} * {@link Config#NUMBER_DICE_SIDES}
     *                     inclusive.
     */
    public void movePlayer(int rolledNumber, boolean moveAgain) {
        Player currentPlayer = getCurrentPlayer();
        int currentPosition = currentPlayer.getPosition();

        int nextPosition = calculateNextFieldId(gameBoard.getBoardSize(), currentPosition, rolledNumber);
        int startFieldId = gameBoard.getStartGameField().getFieldId();

        if (jumpedOverField(startFieldId, currentPosition, nextPosition)) {
            parentsHelp();
        }

        if (currentPlayer.isWorking()) {
            int currentPlayerJobFieldId = currentPlayer.getJob().getFieldId();

            if ((currentPosition < currentPlayerJobFieldId) && (currentPlayerJobFieldId < nextPosition)) {
                payday();
            }
        }

        logger.info(String.format("Rolled number: %d; Next field id: %d", rolledNumber, nextPosition));
        moveCurrentPlayerToField(nextPosition);
        gameBoard.getField(nextPosition).action(currentPlayer);

        if ((currentPlayer.getPosition() == gameBoard.getExamGameFieldId()) && (currentPlayer.getRoundsWaiting() > 0)) {
            repetition();
        }
        if (currentPlayer.getMoney() < 0 && !currentPlayer.isWaitingForScholarship()) {
            broke();
        }
        if (currentPlayer.getCredits() >= CREDITS_TO_WIN) {
            winner();
        }
        if (!gameWasWon.get() && !moveAgain) {
            switchToNextPlayer();
        }
    }

    /**
     * Makes a player repeat after failing the exams
     */
    private void repetition() {
        moveCurrentPlayerToField(gameBoard.getRepetitionGameFieldId());
    }

    /**
     * Moves a player to a new field
     *
     * @param fieldId the new field
     */
    private void moveCurrentPlayerToField(int fieldId) {
        getCurrentPlayer().setPosition(fieldId);
    }

    /**
     * If current player has no money move to the @{@link Config.FieldType#START} field.
     */
    private void broke() {
        new InformationalWindow("Funds ran dry!", String.format("%S, you ran out of money!",
                getCurrentPlayer().getName()));
        getCurrentPlayer().setPosition(gameBoard.getStartGameField().getFieldId());
        scholarship();
    }

    /**
     * Ends the game when someone has won
     */
    private void winner() {
        new InformationalWindow("Graduated!", String.format(
                "Congratulations %S! You just graduated from ZHAW!%nMany great job opportunities await!",
                getCurrentPlayer().getName()));
        gameWasWon.set(true);
    }

    /**
     * Pays the wage if a player lands on his job field
     */
    private void payday() {
        int wage = JobGameField.getBaseWage();
        new InformationalWindow("Payday", String.format("You earned this week: %d.-CHF.", wage));
        getCurrentPlayer().alterMoney(wage);
    }

    /**
     * Makes a player wait for scholarship after being broke
     */
    private void scholarship() {
        Player currentPlayer = getCurrentPlayer();
        int round = StartGameField.getScholarshipWaitingTime();
        new InformationalWindow("Applied for Scholarship", String.format(
                "%S, you ran out of money. You have to apply for a scholarship.%nThat usually takes up to %d Weeks",
                currentPlayer.getName(), round));
        currentPlayer.setRoundsWaiting(round);
        currentPlayer.setWaitingForScholarship(true);
    }

    /**
     * Gives a player money if landing on START game field
     */
    private void parentsHelp() {
        int moneyHelp = StartGameField.getParentsHelp();
        new InformationalWindow("Financial support from parents", String.format("%S, you got %d CHF from your parents!",
                getCurrentPlayer().getName(), moneyHelp));
        getCurrentPlayer().alterMoney(moneyHelp);
    }

    /**
     * rolls dice and checks if you rolled a double which gives you an extra move
     */
    public void rollDice() {
        boolean again = false;
        int firstDice = diceNumber();
        int secondDice = diceNumber();
        int rolledNumber = firstDice + secondDice;

        currentDiceRoll.setValue(rolledNumber);

        if (firstDice == secondDice) {
            new InformationalWindow("Rolled a double!", String.format("%S can move again.",
                    getCurrentPlayer().getName()));
            again = true;
        }
        movePlayer(rolledNumber, again);
    }

    int diceNumber() {
        Random random = new Random();
        return random.nextInt(NUMBER_DICE_SIDES) + 1;
    }
}
