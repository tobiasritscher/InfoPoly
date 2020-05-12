package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.GameField;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.JobGameField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Player {
    private static final Logger logger = Logger.getLogger(Player.class.getCanonicalName());
    private int roundsWaiting;
    private final int playerId;
    private IntegerProperty money = new SimpleIntegerProperty();
    private IntegerProperty credits = new SimpleIntegerProperty();
    private IntegerProperty position = new SimpleIntegerProperty();
    private final String name;
    private boolean isWorking = false;
    private List<GameField> ownerShips;
    private boolean waitingForScholarship = false;

    public Player(String name, int money, int credits, int playerId) {
        this.name = name;
        this.credits.setValue(credits);
        this.money.setValue(money);
        this.playerId = playerId;
        this.position.setValue(Config.PLAYER_START_POSITION);
        ownerShips = new ArrayList<>();
        roundsWaiting = 0;
    }

    public int getRoundsWaiting() {
        return roundsWaiting;
    }

    public void setRoundsWaiting(int amount) {
        roundsWaiting = amount;
    }

    public List<GameField> getOwnerShips() {
        return ownerShips;
    }

    public boolean isWaitingForScholarship() {
        return waitingForScholarship;
    }

    public void setWaitingForScholarship(boolean waitingForScholarship) {
        this.waitingForScholarship = waitingForScholarship;
    }

    /**
     * Adds a new Field to the players list
     *
     * @param field the field which the player has bought
     */
    public void addOwnerShip(GameField field) {
        ownerShips.add(field);
    }

    /**
     * Adds the work which the player has decided to work
     *
     * @param job The field in which the player works
     */
    public void addWork(JobGameField job) {
        if (!isWorking()) {
            addOwnerShip(job);
            setWorking(true);
        }
    }

    /**
     * Removes the job which the player had...Mainly if he decides for another job
     */
    public void removeWork() {
        for (GameField value : ownerShips) {
            if (value.getFieldType().equals(Config.FieldType.JOB)) {
                value.resetOwner();
                ownerShips.remove(value);
                return;
            }
        }
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPosition() {
        return position.get();
    }

    public void setPosition(int position) {
        logger.info(String.format("Player %s position changed from %d to %d", name.toUpperCase(), this.position.get(), position));
        this.position.set(position);
    }


    public int getMoney() {
        return money.getValue();
    }

    public void setMoney(int money) {
        this.money.set(money);
    }

    public int getCredits() {
        return credits.getValue();
    }

    public String getName() {
        return name;
    }

    public IntegerProperty getMoneyProperty() {
        return money;
    }

    public IntegerProperty getCreditsProperty() {
        return credits;
    }

    public IntegerProperty getPositionProperty() {
        return position;
    }
    /**
     * Add or charge money.
     *
     * @param amount positive or negative integer.
     */
    public void alterMoney(int amount) {
        int oldState = money.getValue();
        money.set(money.getValue() + amount);
        logger.info(String.format("Money altered. Player: %s Old: %d New: %d", name, oldState, money.get()));
    }

    /**
     * Add or charge credits.
     *
     * @param creditChange positive or negative integer.
     */
    public void alterCredits(int creditChange) {
        int oldState = credits.getValue();
        int newState = oldState + creditChange;

        credits.set(Math.max(newState, 0));

        logger.info(String.format("Credits altered. Player: %s Old: %d New: %d", name, oldState, newState));
    }
}
