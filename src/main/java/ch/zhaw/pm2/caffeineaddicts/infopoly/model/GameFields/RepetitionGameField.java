package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents {@link Config.FieldType#REPETITION}.
 */
public class RepetitionGameField extends GameField {
    private final Map<Integer, Integer> students = new HashMap<>();
    private final int numberRoundsToWait = 3;


    public RepetitionGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }


    @Override
    public void action(Player currentPlayer) {
        //todo timer
        new InformationalWindow("Such a nerd!", "You are laughing at the students that are repeating!");
    }

    public int getNumberRoundsToWait() {
        return numberRoundsToWait;
    }
}