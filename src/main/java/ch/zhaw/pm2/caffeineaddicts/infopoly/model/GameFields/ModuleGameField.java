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
    private final int FIELD_PRICE = 10;
    private final int CREDITS_GAIN = 20;

    public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    public int getFieldPrice() {
        return FIELD_PRICE;
    }


    /**
     *
     *
     * @param currentPlayer
     */
    @Override
    public void action(Player currentPlayer) {
        int FIELD_MONEY_CHARGE = 10;
        Player owner = getOwner();

        if (hasOwner()) {
            if (owner.equals(currentPlayer)) {
                new InformationalWindow("Hey Boss!", "You alredy own this module.");
            } else {
                new InformationalWindow("This course is so good!", String.format("You must pay: %d", FIELD_MONEY_CHARGE));
                currentPlayer.alterMoney(-FIELD_MONEY_CHARGE);
                owner.alterCredits(CREDITS_GAIN);
            }
        } else {
            if (currentPlayer.getMoney() >= getFieldPrice()) {
                QuestionWindow questionWindow = new QuestionWindow(String.format("Purchase course? (%d CHF)", FIELD_PRICE), String.format(" %s would you like to buy the course: %s", currentPlayer.getName().toUpperCase(), getFieldName().toUpperCase()));
                if (questionWindow.getAnswer()) {
                    currentPlayer.alterMoney(-getFieldPrice());
                    setOwner(currentPlayer);
                    currentPlayer.alterCredits(CREDITS_GAIN);
                    new InformationalWindow("Balance",String.format("You lost %dCHF and got %d credits from this purchase.", FIELD_MONEY_CHARGE, CREDITS_GAIN));
                }
            } else {
                new InformationalWindow("Get a job!", "You are to poor to buy this field.");
            }
        }
    }
}