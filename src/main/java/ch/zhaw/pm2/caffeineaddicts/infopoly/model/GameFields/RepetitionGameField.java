package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents {@link Config.FieldType#REPETITION}.
 */
public class RepetitionGameField extends GameField {
    private final Map<Integer, Integer> students = new HashMap<>();
    private final int numberRoundsToWait;


    /**
     * @param numberRoundsToWait positive integer number
     */
    public RepetitionGameField(int fieldId, Config.FieldType fieldType, String fieldName, int numberRoundsToWait) {
        super(fieldId, fieldType, fieldName);
        this.numberRoundsToWait = Math.min(0, numberRoundsToWait);
    }


    @Override
    public void action(Player currentPlayer) {
        //TODO: repeating
    }
}