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

    public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    /**
     * the action is called when somebody gets on this field
     *
     * @param currentPlayer the current player
     */
    @Override
    public void action(Player currentPlayer) {
        final int FIELD_MONEY_CHARGE = 10;
        Player owner = getOwner();

        if (hasOwner()) {
            if (owner.equals(currentPlayer)) {
                new InformationalWindow("Hey Boss!", "You alredy own this module.");
            } else {
                new InformationalWindow("This course is so good!", String.format("You must pay: %d CHF", FIELD_MONEY_CHARGE));
                currentPlayer.alterMoney(-FIELD_MONEY_CHARGE);
                owner.alterCredits(MEDIUM_CREDITS);
            }
        } else {
            final int FIELD_PRICE = 10;

            if (currentPlayer.getMoney() >= FIELD_PRICE) {
                QuestionWindow questionWindow = new QuestionWindow(String.format("Purchase course? (%d CHF)", FIELD_PRICE), String.format(" %s would you like to buy the course: %s", currentPlayer.getName().toUpperCase(), getFieldName().toUpperCase()));
                if (questionWindow.getAnswer()) {
                    currentPlayer.alterMoney(FIELD_PRICE * -1);
                    setOwner(currentPlayer);
                    currentPlayer.alterCredits(MEDIUM_CREDITS);
                    new InformationalWindow("Balance", String.format("You lost %dCHF and got %d credits from this purchase.", FIELD_MONEY_CHARGE, MEDIUM_CREDITS));
                }
            } else {
                new InformationalWindow("Get a job!", "You are to poor to buy this field.");
            }
        }
    }
}