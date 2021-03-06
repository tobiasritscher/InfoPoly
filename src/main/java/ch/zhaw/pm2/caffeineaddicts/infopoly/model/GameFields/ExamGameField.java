package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

/**
 * Representation of {@link Config.FieldType#EXAM}.
 */
public class ExamGameField extends GameField {
    private static final int REPETITION_TIME_ON_FAIL = 3;
    private static final int CREDITS_GAIN_ON_SUCCESS = 30;
    private final Random random = new Random();
    private final double probabilityToPass = 0.3;

    public ExamGameField(int fieldId, String fieldName) {
        super(fieldId, fieldName);
    }

    /**
     * <p>Decide weather exam passed or not.</p>
     * <p>The possibility is taken form {@link ExamGameField#probabilityToPass}.</p>
     *
     * @return a boolean value: true, if passed; false, else;
     */
    private boolean passed() {
        return 0 == random.nextInt((int) (1 / probabilityToPass));
    }

    /**
     * the action is called when a player enters the examGameField
     * the the currentPlayer != null will have a chance for repeating or for getting credits
     *
     * @param currentPlayer != null, the player who is standing on the field
     */
    @Override
    public void action(Player currentPlayer) {
        new InformationalWindow("Taking exam",
                "You are taking an exam, if you fail you have to repeat!");

        if (passed()) {
            new InformationalWindow("You passed!", "You have passed your exam!");
            currentPlayer.alterCredits(CREDITS_GAIN_ON_SUCCESS);
        } else {
            new InformationalWindow("You failed!", String.format(
                    "You have failed, you need to repeat this semester!%nIt will take you %d weeks!",
                    REPETITION_TIME_ON_FAIL));
            currentPlayer.setRoundsWaiting(REPETITION_TIME_ON_FAIL);
        }
    }
}