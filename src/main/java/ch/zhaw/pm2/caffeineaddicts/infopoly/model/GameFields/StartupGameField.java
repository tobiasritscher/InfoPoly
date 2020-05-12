package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#STARTUP}.
 */
public class StartupGameField extends GameField {
    private static final int moneyNeeded = 10;
    private static final int moneyPayout = 200;
    private static final int NEEDED_AMOUNT_CREDITS = 200;

    public StartupGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    public int getMoneyNeeded() {
        return moneyNeeded;
    }

    @Override
    public void action(Player currentPlayer) {
        if (hasOwner() && getOwner().equals(currentPlayer)) {
            new InformationalWindow("Bravo!", String.format("Your startup made quite the turnover this week! You got %dCHF", moneyPayout));
            currentPlayer.alterMoney(moneyPayout);
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