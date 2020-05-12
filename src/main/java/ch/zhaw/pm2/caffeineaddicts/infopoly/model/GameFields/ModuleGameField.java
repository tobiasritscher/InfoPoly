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
    private final int creditsGainFromVisitors = 10;
    private final int creditsGainFromPurchase = 10;

    public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    public int getFieldMoneyCharge() {
        return fieldMoneyCharge;
    }

    public int getFieldPrice() {
        return fieldPrice;
    }

    public int getCreditsGainFromVisitors() {
        return creditsGainFromVisitors;
    }

    @Override
    public void action(Player currentPlayer) {
        Player owner = getOwner();

        if (hasOwner()) {
            if (owner.equals(currentPlayer)) {
                new InformationalWindow("Hey Boss!", String.format("You got some credits: %d", creditsGainFromVisitors));

            } else {
                new InformationalWindow("This course is so good!", String.format("You must pay: %d", fieldMoneyCharge));
                owner.alterMoney(Math.min(0, Math.min(currentPlayer.getMoney(), fieldMoneyCharge)));
                currentPlayer.alterMoney(-fieldMoneyCharge);
            }
            owner.alterCredits(getCreditsGainFromVisitors());
        } else {
            if (currentPlayer.getMoney() >= getFieldPrice()) {
                QuestionWindow questionWindow = new QuestionWindow("Purchase course?", String.format(" %s would you like to buy the course: %s", currentPlayer.getName().toUpperCase(), getFieldName().toUpperCase()));
                if (questionWindow.getAnswer()) {
                    currentPlayer.alterMoney(-getFieldPrice());
                    setOwner(currentPlayer);
                    currentPlayer.alterCredits(creditsGainFromPurchase);
                    new InformationalWindow("Balance",String.format("You lost %dCHF and got %d credits from this purchase.", fieldMoneyCharge, creditsGainFromPurchase));
                }
            } else {
                new InformationalWindow("Get a job!", "You are to poor to buy this field.");
            }
        }
    }

}