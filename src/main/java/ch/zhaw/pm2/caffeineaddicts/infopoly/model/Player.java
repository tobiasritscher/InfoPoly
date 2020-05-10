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
    /**
     * How many rounds a player must wait
     */
    private int roundsWaiting;
    /**
     * Numerical player number. Ranges between 1 to 4
     */
    private int playerId;
    /**
     * Money of the player. Is SimpleIntegerProperty type to allow support for PropertyListener.
     */
    private IntegerProperty money = new SimpleIntegerProperty();
    /**
     * Credits of the player. Is SimpleIntegerProperty type to allow support for PropertyListener.
     */
    private IntegerProperty credits = new SimpleIntegerProperty();

    /**
     * Current position of the player on the field
     */
    private IntegerProperty position = new SimpleIntegerProperty();

    /**
     * Name of the player
     */
    private String name;
    /**
     * Defines if the player has no money
     */
    private boolean isBroke = false;
    /**
     * Defines if the player is waiting due to being broke or failed exams
     */
    private boolean isWaiting = false;
    /**
     * Defines if the player has a job
     */
    private boolean isWorking = false;
    /**
     * List of all the courses and jobs that the player has
     */
    private List<GameField> ownerShips;

    public Player(String name, int money, int credits, int playerId) {
        this.name = name;
        this.credits.setValue(credits);
        this.money.setValue(money);
        this.playerNumber = playerNumber;
        this.position.setValue(1);
        ownerShips = new ArrayList<>();
        roundsWaiting = 0;
    }

    public int getRoundsWaiting() {
        return roundsWaiting;
    }

    public void setRoundsWaiting(int amount) {
        roundsWaiting += amount;
    }

    public List<GameField> getOwnerShips() {
        return ownerShips;
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

    public void setCredits(int credits) {
        this.credits.set(credits);
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

    public boolean getIsWaiting() {
        return isWaiting;
    }

    public void isWaiting() {
        isWaiting = true;
    }

    public void isNotWaiting() {
        isWaiting = false;
    }

    /**
     * Add or charge money.
     *
     * @param amount positive or negative integer.
     */
    public void alterMoney(int amount) {
        money.set(money.getValue() + amount);
    }

    /**
     * Add or charge credits.
     *
     * @param credits positive or negative integer.
     */
    public void alterCredits(int credits) {
        this.credits.set(this.credits.get() + credits);
    }

    public boolean isBroke() {
        return isBroke;
    }

}
