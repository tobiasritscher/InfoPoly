package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;

import java.util.Random;

/**
 * Representation of {@link Config.FieldType#EXAM}.
 */
public class ExamGameField extends GameField {

    /**
     *
     */
    public ExamGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    /**
     * @return if exam was passed
     */
    public boolean passed() {
        return new Random().nextBoolean();
    }

    @Override
    public void action(Player currentPlayer) {
        new InformationalWindow("You are taking an exam, if you fail you have to repeat!");

        if (passed()) {
            new InformationalWindow("You have passed your exam! YAY");
            currentPlayer.alterCredits(Config.MANY_CREDITS);
        } else {
            new InformationalWindow("You have failed, you need to repeat this semester!");
            currentPlayer.setPosition(41); // TODO Field 41 is reserved for repetition. Change code if needed
            //TODO repeating?
        }
    }
}