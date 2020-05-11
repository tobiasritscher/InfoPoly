package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents {@link Config.FieldType#STARTUP}.
 */
public class StartupGameField extends GameField {
    private final int moneyNeeded;
    private final int moneyPayout;
    private final IntegerProperty founderId = new SimpleIntegerProperty(); //TODO: for what????

    public StartupGameField(int fieldId, Config.FieldType fieldType, String fieldName, int moneyNeeded, int moneyPayout) {
        super(fieldId, fieldType, fieldName);
        this.moneyNeeded = moneyNeeded;
        this.moneyPayout = moneyPayout;
        this.founderId.set(-1);
    }

    public boolean isLaunched() {
        return founderId.get() != -1;
    }

    /**
     * Note: before calling call {@link StartupGameField#isLaunched()}.
     * Will throw exception if not launched.
     *
     * @return player id
     */
    public int getFounderId() {
        if (!isLaunched()) {
            throw new RuntimeException("invalid operation: field already has founder");
        }
        return founderId.get();
    }

    public void setFounderId(int founderId) {
        if (isLaunched()) {
            throw new RuntimeException("invalid operation: field already has founder");
        }
        this.founderId.set(founderId);
    }

    public int getMoneyNeeded() {
        return moneyNeeded;
    }

    public int getMoneyPayout() {
        if (!isLaunched()) {
            throw new RuntimeException("invalid operation: no may money may obtained, startup is not launched yet.");
        }
        return moneyPayout;
    }

    public IntegerProperty founderIdProperty() {
        return founderId;
    }

    @Override
    public void action(Player currentPlayer) {
        final int NEEDED_AMOUNT_CREDITS = 100;

        if (getOwner().equals(currentPlayer)) {
            if (isLaunched()) {
                new InformationalWindow("", "Startup is already created with your Idea...had to be fast!");
            } else {
                new InformationalWindow("", "Your startup made quite the turnover this week! +200CHF");
                currentPlayer.alterMoney(200);
            }
        } else {
            if (currentPlayer.getCredits() >= NEEDED_AMOUNT_CREDITS) {
                if (currentPlayer.getMoney() >= getMoneyNeeded()) {
                    QuestionWindow questionWindow = new QuestionWindow("Startup Manager", "Would you like to create your first startup?");
                    if (questionWindow.getAnswer()) {
                        setOwner(currentPlayer);
                        currentPlayer.setMoney(currentPlayer.getMoney() - getMoneyNeeded());
                    } else {
                        new InformationalWindow("","I guess not everyone is up to the challenge...");
                    }
                } else {
                    new InformationalWindow("","You require: " + getMoneyNeeded() + " in order to start your first Startup");
                }
            } else {
                new InformationalWindow("" ,"A successful startup requires the needed knowledge...(" + NEEDED_AMOUNT_CREDITS + " Credits)");
            }
        }
    }
}