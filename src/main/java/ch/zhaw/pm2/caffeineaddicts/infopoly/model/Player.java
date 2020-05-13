package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.JobGameField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.logging.Logger;

/**
 * Creates each individual player
 */
public class Player {
    private static final Logger logger = Logger.getLogger(Player.class.getCanonicalName());
    private final int playerId;
    private final String name;
    private int roundsWaiting;
    private IntegerProperty money = new SimpleIntegerProperty();
    private final IntegerProperty credits = new SimpleIntegerProperty();
    private IntegerProperty position = new SimpleIntegerProperty();
    private boolean isWorking = false;
    private JobGameField job = null;
    private boolean waitingForScholarship = false;

    public Player(String name, int money, int credits, int playerId) {
        this.name = name;
        this.credits.setValue(credits);
        this.money.setValue(money);
        this.playerId = playerId;
        this.position.setValue(Config.PLAYER_START_POSITION);
        roundsWaiting = 0;
    }

    public int getRoundsWaiting() {
        return roundsWaiting;
    }

    public void setRoundsWaiting(int amount) {
        roundsWaiting = amount;
    }

    public JobGameField getJob() {
        return job;
    }

    public boolean isWaitingForScholarship() {
        return waitingForScholarship;
    }

    public void setWaitingForScholarship(boolean waitingForScholarship) {
        this.waitingForScholarship = waitingForScholarship;
    }

    /**
     * Adds the work which the player has decided to work
     *
     * @param job The field in which the player works
     */
    public void addJob(JobGameField job) {
        this.job = job;
        isWorking = true;
    }


    /**
     * Removes the job which the player had...Mainly if he decides for another job
     */
    /*
    public void removeJob() {
        for (GameField value : ownerShips) {
            if (value.getFieldType().equals(Config.FieldType.JOB)) {
                value.resetOwner();
                ownerShips.remove(value);
                return;
            }
        }
    }*/

    /**
     * removes the player's job
     */
    public void removeJob() {
        isWorking = false;
        job = null;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPosition() {
        return position.get();
    }

    /**
     * changes the players position on the field
     * @param position the new position
     */
    public void setPosition(int position) {
        logger.info(String.format("Player %s position changed from %d to %d", name.toUpperCase(), this.position.get(), position));
        this.position.set(position);
    }

    public int getMoney() {
        return money.getValue();
    }

    public int getCredits() {
        return credits.getValue();
    }

    public String getName() {
        return name;
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

    public IntegerProperty getMoneyProperty() {
        return money;
    }

    public IntegerProperty getCreditsProperty() {
        return credits;
    }

    public IntegerProperty getPositionProperty() {
        return position;
    }
}
