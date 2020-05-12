package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

/**
 * Representation of {@link Config.FieldType#EXAM}.
 */
public class ExamGameField extends GameField {
    private final Random random = new Random();
    private final int repetitionTime = 3;
    private final double probabilityToPass = 0.2;

    public ExamGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    /**
     * <p>Decide weather exam passed or not.</p>
     * <p>The possibility is taken form {@link ExamGameField#probabilityToPass}.</p>
     *
     * @return a boolean value: true, if passed; false, else;
     */
    public boolean passed() {
        return (0 == random.nextInt((int) (1 / probabilityToPass))) ? true : false;
    }

    @Override
    public void action(Player currentPlayer) {
        new InformationalWindow("Noooo...", "You are taking an exam, if you fail you have to repeat!");

        if (passed()) {
            new InformationalWindow("Impossible!", "You have passed your exam! YAY");
            currentPlayer.alterCredits(Config.MANY_CREDITS);
        } else {
            new InformationalWindow("As expected!", "You have failed, you need to repeat this semester!");
            currentPlayer.setRoundsWaiting(repetitionTime);
        }
    }

}