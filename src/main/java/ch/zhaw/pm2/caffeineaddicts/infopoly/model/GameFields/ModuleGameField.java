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
    public static int MONEY_CHARGE_ON_VISIT = 10;
    public static int CREDITS_GAIN_ON_PURCHASE = 10;
    public static int FIELD_PRICE = 100;
    public static int CREDITS_GAIN_ON_VISIT = 5;

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
        Player owner = getOwner();

        if (hasOwner()) {
            if (owner.equals(currentPlayer)) {
                new InformationalWindow("Hey Boss!", "Have a sit and listen to the lectures!");
            } else {
                new InformationalWindow("This course is so good!", String.format("You decided to pay for it %d CHF. You get %d credits for listening to smart people.", MONEY_CHARGE_ON_VISIT, CREDITS_GAIN_ON_VISIT));
                currentPlayer.alterMoney(-MONEY_CHARGE_ON_VISIT);
                owner.alterCredits(CREDITS_GAIN_ON_VISIT);
            }
        } else {
            if (currentPlayer.getMoney() >= FIELD_PRICE) {
                QuestionWindow questionWindow = new QuestionWindow(String.format("Purchase course? (%d CHF)", FIELD_PRICE), String.format("%s would you like to buy the course: %s%nYou will get: %d credits", currentPlayer.getName().toUpperCase(), getFieldName().toUpperCase(), CREDITS_GAIN_ON_PURCHASE));
                if (questionWindow.getAnswer()) {
                    currentPlayer.alterMoney(FIELD_PRICE * -1);
                    setOwner(currentPlayer);
                    currentPlayer.alterCredits(CREDITS_GAIN_ON_PURCHASE);
                }
            } else {
                new InformationalWindow("Get a job!", "You are to poor to buy this field.");
            }
        }
    }
}