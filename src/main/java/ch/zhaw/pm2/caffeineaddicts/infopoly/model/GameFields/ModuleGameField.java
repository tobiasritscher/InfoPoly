package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import static ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config.MEDIUM_CREDITS;

/**
 * Representation of {@link Config.FieldType#MODULE}
 */
public class ModuleGameField extends GameField {
    private final int fieldPrice = 10;
    private final int fieldMoneyCharge = 10;
    private final int creditsGain = 10;

    public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    public int getFieldMoneyCharge() {
        return fieldMoneyCharge;
    }

    public int getFieldPrice() {
        return fieldPrice;
    }

    public int getCreditsGain() {
        return creditsGain;
    }

    @Override
    public void action(Player currentPlayer) {
        Player owner = getOwner();

        if (hasOwner()) {
            if (owner.equals(currentPlayer)) {
                new InformationalWindow("Forgot?", "You already own this field");

            } else if (currentPlayer.getMoney() < getFieldMoneyCharge()) {
                owner.setMoney(currentPlayer.getMoney());
                owner.setCredits(getCreditsGain());
                currentPlayer.setMoney(0);
            } else {
                currentPlayer.setMoney(currentPlayer.getMoney() - getFieldMoneyCharge());
                owner.setMoney(getFieldMoneyCharge());
                owner.setCredits(getCreditsGain());
            }
        } else {
            if (currentPlayer.getMoney() >= getFieldPrice()) {
                QuestionWindow questionWindow = new QuestionWindow("Purchase course?", String.format(" %s would you like to buy the course: %s", currentPlayer.getName().toUpperCase(), getFieldName().toUpperCase()));
                if (questionWindow.getAnswer()) {
                    currentPlayer.setMoney(currentPlayer.getMoney() - getFieldPrice());
                    setOwner(currentPlayer);
                    currentPlayer.alterCredits(MEDIUM_CREDITS);
                }
            } else {
                new InformationalWindow("Get a job!", "You are to poor to buy this field.");
            }
        }
    }

}