package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Logic {
    private int roundsWaiting;
    private static IntegerProperty currentPlayer = new SimpleIntegerProperty(0);
    private Chance chance;

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

    public void movePlayer(int rolledNumber) {
        players.get(currentPlayer.getValue()).setPosition((players.get(currentPlayer.getValue()).getPosition() + rolledNumber) % gameBoard.getBoardSize());
        int fieldId = players.get(currentPlayer.getValue()).getPosition();
        Config.FieldType fieldType = gameBoard.getFieldType(fieldId);
        switch (fieldType) {
            case MODULE:
                processModule(fieldId);
                break;
            case STARTUP:
                startup();
                break;
            case JOB:
                break;
            case CHANCE:
                getChance();
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

    private void processModule(int fieldId) {
        ModuleGameField gameField = (ModuleGameField) gameBoard.getField(fieldId);
        if (gameField.fieldHasOwner()) {
            if (gameField.getFieldOwnerId() == players.get(currentPlayer.getValue()).getPlayerNumber()) {
            } else if (players.get(currentPlayer.getValue()).getMoney() < gameField.getFieldMoneyCharge()) {
                players.get(currentPlayer.getValue()).setMoney(0);
                waitForScholarship();
            } else {
                players.get(currentPlayer.getValue()).setMoney(players.get(currentPlayer.getValue()).getMoney() - gameField.getFieldMoneyCharge());
                players.get(gameField.getFieldOwnerId()).setMoney(gameField.getFieldMoneyCharge());
                players.get(gameField.getFieldOwnerId()).setCredits(gameField.getCreditsGain());
            }
        } else {
            QuestionWindow questionWindow = new QuestionWindow("Buy course","Would you like to buy this course");
            if(questionWindow.getAnswer()){
                players.get(currentPlayer.getValue()).setMoney(players.get(currentPlayer.getValue()).getMoney() - gameField.getFieldPrice());
                gameField.setFieldOwner(players.get(currentPlayer.getValue()).getPlayerNumber());
            }
        }
    }

    private void startup() {
        if(players.get(currentPlayer.getValue()).getCredits() >= 100){
            QuestionWindow questionWindow = new QuestionWindow("Startup Manager","Would you like to create your first startup?");
            if(questionWindow.getAnswer()){

            }
        }
    }

    private void getChance() {
        Chance.ChanceEvent chanceEvent = chance.getChanceEvent();
        new InformationalWindow(chanceEvent.getMessage());
        if (players.get(currentPlayer.getValue()).getMoney() + chanceEvent.getMoneyDeviation() < 0) {
            waitForScholarship();
        } else {
            players.get(currentPlayer.getValue()).setMoney(players.get(currentPlayer.getValue()).getMoney() + chanceEvent.getMoneyDeviation());
        }
        players.get(currentPlayer.getValue()).setCredits(chanceEvent.getCreditsDeviation());
    }

    private void waitForScholarship() {
        new InformationalWindow("You ran out of money so now you will apply for a scholarship. That usually takes up to 3 Weeks");
        setRoundsWaiting(3);
        if (roundsWaiting == 0) {
            players.get(currentPlayer.getValue()).setMoney(100);
        }
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
