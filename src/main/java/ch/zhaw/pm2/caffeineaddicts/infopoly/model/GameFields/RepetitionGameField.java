package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#REPETITION}.
 */
public class RepetitionGameField extends GameField {

    public RepetitionGameField(int fieldId, String fieldName) {
        super(fieldId, fieldName);
    }

    @Override
    public void action(Player currentPlayer) {
        new InformationalWindow("Visited repeating students", "You hang out with your repeating " +
                "student friends. You're glad you took studying seriously!");
    }

}