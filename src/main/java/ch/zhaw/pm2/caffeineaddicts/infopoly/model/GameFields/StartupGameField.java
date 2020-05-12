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

    public StartupGameField(int fieldId, Config.FieldType fieldType, String fieldName, int moneyNeeded, int moneyPayout) {
        super(fieldId, fieldType, fieldName);
        this.moneyNeeded = moneyNeeded;
        this.moneyPayout = moneyPayout;
    }

    public int getMoneyNeeded() {
        return moneyNeeded;
    }

    @Override
    public void action(Player currentPlayer) {
        final int NEEDED_AMOUNT_CREDITS = 0;

        if (hasOwner() && getOwner().equals(currentPlayer)) {
            final int extraMoney = 200;
            new InformationalWindow("Bravo!", String.format("Your startup made quite the turnover this week! You got %dCHF", extraMoney));
            currentPlayer.alterMoney(extraMoney);
        } else if (hasOwner() && !getOwner().equals(currentPlayer)) {
            new InformationalWindow("Too late!", "Startup is already created with your Idea...had to be fast!");
        } else {
            if (currentPlayer.getCredits() >= NEEDED_AMOUNT_CREDITS) {
                if (currentPlayer.getMoney() >= getMoneyNeeded()) {
                    QuestionWindow questionWindow = new QuestionWindow("Startup Manager", "Would you like to create your first startup?");
                    if (questionWindow.getAnswer()) {
                        setOwner(currentPlayer);
                        currentPlayer.setMoney(currentPlayer.getMoney() - getMoneyNeeded());
                    } else {
                        new InformationalWindow("", "I guess not everyone is up to the challenge...");
                    }
                } else {
                    new InformationalWindow("", "You require: " + getMoneyNeeded() + " in order to start your first Startup");
                }
            } else {
                new InformationalWindow("", "A successful startup requires the needed knowledge...(" + NEEDED_AMOUNT_CREDITS + " Credits)");
            }
        }
    }
}