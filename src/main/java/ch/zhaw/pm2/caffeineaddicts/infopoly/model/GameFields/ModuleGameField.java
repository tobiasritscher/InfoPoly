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
    public static int FIELD_PRICE = 30;
    public static int FIELD_MEDIUM_PRICE = 60;
    public static int FIELD_HIGH_PRICE = 90;
    public static int FIELD_HIGHEST_PRICE = 100;
    public static int CREDITS_GAIN_ON_VISIT = 5;
    public static int CREDITS_GAIN_ON_VISIT_MEDIUM = 10;
    public static int CREDITS_GAIN_ON_VISIT_HIGH = 15;
    public static int CREDITS_GAIN_ON_VISIT_HIGHEST = 20;

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
        int NEW_FIELD_PRICE = 0;
        int NEW_CREDITS_GAIN = 0;
        if(getFieldId() == 2 || getFieldId() == 3 || getFieldId() == 4 || getFieldId() == 7 || getFieldId() == 9 || getFieldId() == 10){
            NEW_FIELD_PRICE = FIELD_PRICE;
            NEW_CREDITS_GAIN = CREDITS_GAIN_ON_VISIT;
        } else if (getFieldId() == 12 || getFieldId() == 13 || getFieldId() == 14 || getFieldId() == 15 || getFieldId() == 18 || getFieldId() == 19){
            NEW_FIELD_PRICE = FIELD_MEDIUM_PRICE;
            NEW_CREDITS_GAIN = CREDITS_GAIN_ON_VISIT_MEDIUM;
        } else if(getFieldId() == 22 || getFieldId() == 23 || getFieldId() == 25 || getFieldId() == 26 || getFieldId() == 27 || getFieldId() == 28 || getFieldId() == 29 || getFieldId() == 30){
            NEW_FIELD_PRICE = FIELD_HIGH_PRICE;
            NEW_CREDITS_GAIN = CREDITS_GAIN_ON_VISIT_HIGH;
        } else if (getFieldId() == 32 || getFieldId() == 33 || getFieldId() == 34 || getFieldId() == 35 || getFieldId() == 38 || getFieldId() == 40){
            NEW_FIELD_PRICE = FIELD_HIGHEST_PRICE;
            NEW_CREDITS_GAIN = CREDITS_GAIN_ON_VISIT_HIGHEST;
        }
        if (hasOwner()) {
            if (owner.equals(currentPlayer)) {
                new InformationalWindow("Hey Boss!", "Have a sit and listen to the lectures!");
            } else {
                new InformationalWindow("This course is so good!", String.format("You decided to pay for it %d CHF. You get %d credits for listening to smart people.", MONEY_CHARGE_ON_VISIT, CREDITS_GAIN_ON_VISIT));
                currentPlayer.alterMoney(-NEW_CREDITS_GAIN);
                owner.alterCredits(NEW_CREDITS_GAIN);
            }
        } else {
            if (currentPlayer.getMoney() >= NEW_FIELD_PRICE) {
                QuestionWindow questionWindow = new QuestionWindow(String.format("Purchase course? (%d CHF)", NEW_FIELD_PRICE), String.format("%s would you like to buy the course: %s%nYou will get: %d credits", currentPlayer.getName().toUpperCase(), getFieldName().toUpperCase(), CREDITS_GAIN_ON_PURCHASE));
                if (questionWindow.getAnswer()) {
                    currentPlayer.alterMoney(NEW_FIELD_PRICE * -1);
                    setOwner(currentPlayer);
                    currentPlayer.alterCredits(CREDITS_GAIN_ON_PURCHASE);
                }
            } else {
                new InformationalWindow("Get a job!", "You are to poor to buy this field.");
            }
        }
    }
}