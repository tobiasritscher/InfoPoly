package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Player {
    /**
     * Numerical player number. Ranges between 1 to 4
     */
    private int playerNumber;
    /**
     * Money of the player. Is SimpleIntegerProperty type to allow support for PropertyListener.
     */
    private IntegerProperty money = new SimpleIntegerProperty();
    /**
     * Credits of the player. Is SimpleIntegerProperty type to allow support for PropertyListener.
     */
    private IntegerProperty credits = new SimpleIntegerProperty();

    private IntegerProperty position = new SimpleIntegerProperty();

    private String name;
    private boolean isBroke = false;
    private boolean isWaiting = false;
    private boolean isWorking = false;
    private List<GameField> ownerShips;

    public Player(String name, int money, int credits, int playerNumber) {
        this.name = name;
        this.credits.setValue(credits);
        this.money.setValue(money);
        this.playerNumber = playerNumber;
        this.position.setValue(1);
        ownerShips = new ArrayList<>();
    }

    public Player() {
    }

    public List<GameField> getOwnerShips() {
        return ownerShips;
    }

    public void addOwnerShip(GameField field) {
        ownerShips.add(field);
    }

    public void removeWork() {
        for (GameField value: ownerShips) {
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

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getPosition() {
        return position.get();
    }

    public void setPosition(int position) {
        this.position.set(position);
    }

    public IntegerProperty positionProperty() {
        return position;
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

    public void winMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cannot earn negative amount or 0");
        }
        if ((money.getValue() - amount) <= 0) {
            isBroke = true;
        } else {
            money.set(money.getValue() - amount);
        }
    }

    public void move(int position) {
        int oldPosition = this.position.getValue();
        int newPosition = oldPosition + position;
        //TODO:
        this.position.setValue(newPosition);
    }
}
