package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#STARTUP}.
 */
public class StartupGameField extends GameField {
    private static final int moneyNeeded = 100;
    private static final int moneyPayout = 200;
    private static final int creditsNeeded = 100;

    public StartupGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    @Override
    public void action(Player currentPlayer) {
        if (hasOwner()) {
            if (getOwner().equals(currentPlayer)) {
                new InformationalWindow("Bravo!", String.format("Your startup made quite the turnover this week! You got %dCHF", moneyPayout));
                currentPlayer.alterMoney(moneyPayout);
            } else {
                new InformationalWindow("Too late!", "Startup is already created with your Idea...had to be fast!");
            }
        } else {
            if (currentPlayer.getCredits() >= creditsNeeded) {
                if (currentPlayer.getMoney() >= moneyNeeded) {
                    QuestionWindow questionWindow = new QuestionWindow("Startup Manager", "Would you like to create your first startup?");
                    if (questionWindow.getAnswer()) {
                        setOwner(currentPlayer);
                        currentPlayer.alterMoney(moneyNeeded * -1);
                    } else {
                        new InformationalWindow("", "I guess not everyone is up to the challenge...");
                    }
                } else {
                    new InformationalWindow("No chance for poor...", "You require: " + moneyNeeded + " in order to start your first Startup");
                }
            } else {
                new InformationalWindow("Not smart enough...", "A successful startup requires the needed knowledge...(" + creditsNeeded + " Credits)");
            }
        }
    }
}