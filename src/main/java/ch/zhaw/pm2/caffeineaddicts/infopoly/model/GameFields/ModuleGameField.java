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
    public final static int MONEY_CHARGE_ON_VISIT = 10;
    public final static int CREDITS_GAIN_ON_PURCHASE = 10;
    public final static int FIELD_PRICE = 30;
    public final static int FIELD_MEDIUM_PRICE = 60;
    public final static int FIELD_HIGH_PRICE = 90;
    public final static int FIELD_HIGHEST_PRICE = 100;
    public final static int CREDITS_GAIN_ON_VISIT = 5;
    public final static int CREDITS_GAIN_ON_VISIT_MEDIUM = 10;
    public final static int CREDITS_GAIN_ON_VISIT_HIGH = 15;
    public final static int CREDITS_GAIN_ON_VISIT_HIGHEST = 20;
    public final int fieldPrice;
    public final int creditsGain;

    public ModuleGameField(int fieldId, String fieldName) {
        super(fieldId, fieldName);

        if(fieldId >= 32){
            fieldPrice = FIELD_HIGHEST_PRICE;
            creditsGain = CREDITS_GAIN_ON_VISIT_HIGHEST;
        } else if (fieldId >= 22){
            fieldPrice = FIELD_HIGH_PRICE;
            creditsGain = CREDITS_GAIN_ON_VISIT_HIGH;
        } else if(fieldId >= 12){
            fieldPrice = FIELD_MEDIUM_PRICE;
            creditsGain = CREDITS_GAIN_ON_VISIT_MEDIUM;
        } else {
            fieldPrice = FIELD_PRICE;
            creditsGain = CREDITS_GAIN_ON_VISIT;
        }
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
                new InformationalWindow("Course taken", "Have a sit and listen to the lectures!");
            } else {
                new InformationalWindow("Course taken", String.format(
                        "You decided to pay %d.- CHF. You get %d credits for listening to smart people.",
                        MONEY_CHARGE_ON_VISIT, CREDITS_GAIN_ON_VISIT));
                currentPlayer.alterMoney(creditsGain * -1);
                owner.alterCredits(creditsGain);
            }
        } else {
            if (currentPlayer.getMoney() >= fieldPrice) {
                QuestionWindow questionWindow = new QuestionWindow(String.format("Purchase course? (%d CHF)",
                        fieldPrice), String.format("%s, would you like to buy the course %s"+
                                "?\nYou will get %d credits.",
                        currentPlayer.getName().toUpperCase(), getFieldName().toUpperCase(), CREDITS_GAIN_ON_PURCHASE));
                if (questionWindow.getAnswer()) {
                    currentPlayer.alterMoney(fieldPrice * -1);
                    setOwner(currentPlayer);
                    currentPlayer.alterCredits(CREDITS_GAIN_ON_PURCHASE);
                }
            } else {
                new InformationalWindow("Not enough money!",
                        "You have insufficient funds to buy this field.");
            }
        }
    }
}