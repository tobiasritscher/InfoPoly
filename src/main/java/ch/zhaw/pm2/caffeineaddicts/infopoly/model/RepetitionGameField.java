package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

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

    /**
     * Will reduce the number of rounds to wait by one for each player waiting on the field.
     */
    public void countdownWaitingTime() {
        students.replaceAll((k, v) -> Math.min(0, v - 1));
    }

    /**
     * @param playerId player id
     * @return true, if waiting time for the player if greater than zero.
     */
    public boolean isRepeating(int playerId) {
        return students.containsKey(playerId) && (students.get(playerId) > 0);
    }

    /**
     * <p>Note: Call {@link GameField.RepetitionGameField#isRepeating(int)} before the method invocation. Otherwise may throw exception.</p>
     *
     * @param playerId player id
     */
    public void addStudent(int playerId) {
        if (students.containsKey(playerId) && !students.get(playerId).equals(0)) {
            throw new RuntimeException("invalid operation: the player is already repeating");
        }
        students.put(playerId, numberRoundsToWait);
    }

    public void removeStudent(int playerId) {
        if (!students.containsKey(playerId)) {
            throw new RuntimeException("invalid operation: the player is not repeating");
        }
        students.remove(playerId);
    }

    /**
     * @param playerId player id
     * @return 0, if never repeated or after the countdown; positive integer number of round player has to wait;
     */
    public int getRoundsToWait(int playerId) {
        if (!students.containsKey(playerId)) {
            return 0;
        }
        return students.get(playerId);
    }

    @Override
    public void action(Player currentPlayer) {
        //TODO: repeating
    }
}