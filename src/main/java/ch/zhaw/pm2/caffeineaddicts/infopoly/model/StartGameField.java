package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents {@link Config.FieldType#START}.
 */
public class StartGameField extends GameField {
    private final Map<Integer, Integer> students = new HashMap<>();
    private final int baseScholarship;

    public StartGameField(int fieldId, Config.FieldType fieldType, String fieldName, int baseScholarship) {
        super(fieldId, fieldType, fieldName);
        this.baseScholarship = baseScholarship;
    }

    public void applyForScholarship(int playerId) {
        if (students.containsKey(playerId) && !students.get(playerId).equals(0)) {
            throw new RuntimeException("invalid operation: the player already applied for scholarship");
        }
        //students.put(playerId, Config.Dice.rollDice());
        //TODO: chume chume nöd drus, chume chume nöd drus????? Was macht die Zeile obe?
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

    public void removeStudent(int playerId) {
        if (!students.containsKey(playerId)) {
            throw new RuntimeException("invalid operation: the player is not waiting");
        }
        students.remove(playerId);
    }

    /**
     * @param playerId player id
     * @return positive integer, which is the sum of baseScholarShip + money from the interaction with @{@link Config.FieldType#MODULE}
     */
    public int getScholarship(int playerId) {
        if (!students.containsKey(playerId)) {
            throw new RuntimeException("invalid operation: the player never applied");
        }
        removeStudent(playerId);
        //todo implement bank
        return baseScholarship;
    }

    @Override
    public void action(Player currentPlayer) {
        currentPlayer.alterMoney(200);
        //TODO: what is when walking over it?
    }
}