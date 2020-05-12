package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Logic;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

/**
 * Representation of {@link Config.FieldType#EXAM}.
 */
public class ExamGameField extends GameField {
    private Logic logic;

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
        new InformationalWindow("Noooo...","You are taking an exam, if you fail you have to repeat!");

        if (passed()) {
            new InformationalWindow("Impossible!","You have passed your exam! YAY");
            currentPlayer.alterCredits(Config.MANY_CREDITS);
        } else {
            new InformationalWindow("As expected!","You have failed, you need to repeat this semester!");
            logic.repeating(currentPlayer);
        }
    }
}