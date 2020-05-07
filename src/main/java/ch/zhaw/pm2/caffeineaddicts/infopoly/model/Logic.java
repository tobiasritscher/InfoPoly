package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Logic {
    private static IntegerProperty currentPlayer = new SimpleIntegerProperty(0);
    GameBoard gameBoard = new GameBoard();
    private int roundsWaiting;
    private GameField.ChanceGameField chanceGameField;
    private GameField.StartupGameField startupGameField;
    private GameField.JobGameField jobGameField;
    private ArrayList<Player> players = new ArrayList<>();

    public ArrayList<Player> getPlayers() {
        return players;
    }

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
                job(fieldId);
                break;
            case CHANCE:
                getChance();
                break;
            case START:
                start();
                break;
            case FEE:
                break;
            case REPETITION:
                break;
            case EXAM:
                break;
        }
    }

    private void job(int fieldId) {
        if (jobGameField.hasWorker()) {
            if (players.get(currentPlayer.getValue()).getPlayerNumber() == jobGameField.workerIdProperty().getValue()) {
            } else {
                new InformationalWindow("Thank you for shopping with us!");
                players.get(currentPlayer.getValue()).addMoney(-10);
                players.get(jobGameField.workerIdProperty().getValue()).addMoney(10);
            }

        } else {
            QuestionWindow questionWindow = new QuestionWindow("Job Application", "Would you like to start working here?");
            if (questionWindow.getAnswer()) {
                jobGameField.setWorker(players.get(currentPlayer.getValue()).getPlayerNumber());
                jobGameField.payWage(players.get(currentPlayer.getValue()).getPlayerNumber());
            }
        }
    }

    private void quitWork() {
        if (jobGameField.isWorker(currentPlayer.getValue())) {
            QuestionWindow questionWindow = new QuestionWindow("Quit job", "Do you really want to quit your job?");
            if (questionWindow.getAnswer()) {
                jobGameField.removeWorker();
            }
        }
    }

    private void processModule(int fieldId) {
        GameField.ModuleGameField gameField = (GameField.ModuleGameField) gameBoard.getField(fieldId);
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
            QuestionWindow questionWindow = new QuestionWindow("Buy course", "Would you like to buy this course");
            if (questionWindow.getAnswer()) {
                players.get(currentPlayer.getValue()).setMoney(players.get(currentPlayer.getValue()).getMoney() - gameField.getFieldPrice());
                gameField.setFieldOwner(players.get(currentPlayer.getValue()).getPlayerNumber());
            }
        }
    }

    private void startup() {
        if (startupGameField.getFounderId() == players.get(currentPlayer.getValue()).getPlayerNumber()) {
            new InformationalWindow("Your startup made quite the turnover this week! +200CHF");
            players.get(currentPlayer.getValue()).addMoney(200);
        } else if (startupGameField.isLaunched()) {
            new InformationalWindow("Startup is already created with your Idea...had to be fast!");
        } else {
            if (players.get(currentPlayer.getValue()).getCredits() >= 100) {
                if (players.get(currentPlayer.getValue()).getMoney() > startupGameField.getMoneyNeeded()) {
                    QuestionWindow questionWindow = new QuestionWindow("Startup Manager", "Would you like to create your first startup?");
                    if (questionWindow.getAnswer()) {
                        startupGameField.setFounderId(players.get(currentPlayer.getValue()).getPlayerNumber());
                        players.get(currentPlayer.getValue()).setMoney(players.get(currentPlayer.getValue()).getMoney() - startupGameField.getMoneyNeeded());
                    } else {
                        new InformationalWindow("I guess not everyone is up to the challenge...");
                    }
                } else {
                    new InformationalWindow("You require: " + startupGameField.getMoneyNeeded() + " in order to start your first Startup");
                }
            } else {
                new InformationalWindow("A successful startup requires the needed knowledge...(100 Credits)");
            }
        }
    }

    private void start() {
        players.get(currentPlayer.getValue()).addMoney(200);
    }

    private void getChance() {
        GameField.ChanceGameField.ChanceEvent chanceEvent = chanceGameField.getChanceEvent();
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

    public void setRoundsWaiting(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Makes no sense to wait negative amount dude");
        }
        roundsWaiting = amount;
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
