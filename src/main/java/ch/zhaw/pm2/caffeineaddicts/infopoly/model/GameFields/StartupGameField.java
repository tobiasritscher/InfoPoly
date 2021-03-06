package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#STARTUP}.
 */
public class StartupGameField extends GameField {
    private static final int MONEY_TO_LAUNCH = 100;
    private static final int MONEY_GAIN_EACH_ROUND_AFTER_PURCHASE = 200;
    private static final int MIN_CREDITS_TO_LAUNCH = 100;

    public StartupGameField(int fieldId, String fieldName) {
        super(fieldId, fieldName);
    }

    @Override
    public void action(Player currentPlayer) {
        if (hasOwner()) {
            if (getOwner().equals(currentPlayer)) {
                new InformationalWindow("Bravo!",
                        String.format("Your startup made quite the turnover this week! You got %d.-CHF",
                                MONEY_GAIN_EACH_ROUND_AFTER_PURCHASE));
                currentPlayer.alterMoney(MONEY_GAIN_EACH_ROUND_AFTER_PURCHASE);
            } else {
                new InformationalWindow("Too late!",
                        "Startup was already created with a similar idea... You have to be fast!");
            }
        } else {
            if (currentPlayer.getCredits() >= MIN_CREDITS_TO_LAUNCH) {
                if (currentPlayer.getMoney() >= MONEY_TO_LAUNCH) {
                    QuestionWindow questionWindow = new QuestionWindow("Startup Manager",
                            "Would you like to create your first startup?");
                    if (questionWindow.getAnswer()) {
                        setOwner(currentPlayer);
                        currentPlayer.alterMoney(MONEY_TO_LAUNCH * -1);
                    } else {
                        new InformationalWindow("",
                                "Not everyone is up to the challenge...");
                    }
                } else {
                    new InformationalWindow("Insufficient funds",
                            String.format("You require: %d in order to start your first startup", MONEY_TO_LAUNCH));
                }
            } else {
                new InformationalWindow("Not enough credits",
                        String.format("A successful startup requires the needed knowledge. (%d credits)",
                                MIN_CREDITS_TO_LAUNCH));
            }
        }
    }
}