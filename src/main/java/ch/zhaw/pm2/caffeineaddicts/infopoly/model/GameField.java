package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class GameField {

    private final int fieldId;
    private final Config.FieldType fieldType;
    private final String fieldName;

    public GameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        this.fieldId = fieldId;
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public Config.FieldType getFieldType() {
        return fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldId() {
        return fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameField gameField = (GameField) o;
        return fieldId == gameField.fieldId &&
                fieldType == gameField.fieldType &&
                fieldName.equals(gameField.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldId, fieldType, fieldName);
    }

    public static class ModuleGameField extends GameField {
        private final int fieldPrice;
        private final int fieldMoneyCharge;
        private final int creditsGain;
        private final IntegerProperty fieldOwnerId = new SimpleIntegerProperty();

        public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName, int fieldPrice, int fieldMoneyCharge, int creditsGain) {
            super(fieldId, fieldType, fieldName);
            this.fieldPrice = fieldPrice;
            this.fieldMoneyCharge = fieldMoneyCharge;
            this.creditsGain = creditsGain;
            this.fieldOwnerId.set(-1);
        }

        public int getFieldMoneyCharge() {
            return fieldMoneyCharge;
        }

        public boolean fieldHasOwner() {
            return fieldOwnerId.get() != -1;
        }

        public int getFieldOwnerId() {
            return fieldOwnerId.get();
        }

        public IntegerProperty fieldOwnerIdProperty() {
            return fieldOwnerId;
        }

        public void setFieldOwner(int fieldOwnerId) {
            if (fieldHasOwner()) {
                throw new RuntimeException("invalid operation: the field already has owner");
            }
            this.fieldOwnerId.set(fieldOwnerId);
        }

        public int getFieldPrice() {
            return fieldPrice;
        }

        public int getCreditsGain() {
            return creditsGain;
        }
    }

    public static class StartupGameField {
        private final int moneyNeeded;
        private final int moneyPayout;
        private final IntegerProperty founderId = new SimpleIntegerProperty();

        public StartupGameField(int moneyNeeded, int moneyPayout) {
            this.moneyNeeded = moneyNeeded;
            this.moneyPayout = moneyPayout;
            this.founderId.set(-1);
        }

        public boolean isLaunched() {
            return founderId.get() != -1;
        }

        /**
         * Note: before calling call {@link StartupGameField#isLaunched()}.
         * Will throw exception if not launched.
         *
         * @return player id
         */
        public int getFounderId() {
            if (!isLaunched()) {
                throw new RuntimeException("invalid operation: field already has founder");
            }
            return founderId.get();
        }

        public void setFounderId(int founderId) {
            if (isLaunched()) {
                throw new RuntimeException("invalid operation: field already has founder");
            }
            this.founderId.set(founderId);
        }

        public int getMoneyNeeded() {
            return moneyNeeded;
        }

        public int getMoneyPayout() {
            if (!isLaunched()) {
                throw new RuntimeException("invalid operation: no may money may obtained, startup is not launched yet.");
            }
            return moneyPayout;
        }

        public IntegerProperty founderIdProperty() {
            return founderId;
        }
    }

    public class JobGameField {
        private final int baseWage;
        private final int wageIncreaseRate;
        private final IntegerProperty workerId = new SimpleIntegerProperty();
        private int numberTurnsWorked;

        public JobGameField(int baseWage, int wageIncreaseRate) {
            this.baseWage = baseWage;
            this.wageIncreaseRate = wageIncreaseRate;
            workerId.set(-1);
            numberTurnsWorked = 0;
        }

        public boolean isWorker(int playerId) {
            if (playerId < 0) {
                throw new IllegalArgumentException("invalid parameter: player index must be positive number");
            }
            return workerId.get() == playerId;
        }

        public IntegerProperty workerIdProperty() {
            return workerId;
        }

        /**
         * <p>Note: Call {@link JobGameField#isWorker(int)} before invocation of the method. If the player no assigned exception will be thrown.</p>
         *
         * @return payment for last turn
         */
        public int payWage(int playerId) {
            if (!hasWorker()) {
                throw new RuntimeException("invalid operation: no worker assigned yet.");
            }
            if (!isWorker(playerId)) {
                throw new RuntimeException("invalid operation: the player is not assigned for the work yet");
            }
            numberTurnsWorked++;
            return baseWage + wageIncreaseRate * baseWage * (numberTurnsWorked - 1);
        }

        /**
         * <p>Note: Must be called before each invocation of {@link JobGameField#setWorker(int)}</p>
         *
         * @return true, if there is some worker assigned
         */
        public boolean hasWorker() {
            return workerId.get() != -1;
        }

        /**
         * <p>Note: If player was already assigned last round, causes the worker wage to be increase.</p>
         * <p>Note: Call {@link JobGameField#hasWorker()} before invocation of the method. If no worker assigned exception will be thrown.</p>
         *
         * @param playerId player to be assigned
         */
        public void setWorker(int playerId) {
            if (hasWorker()) {
                throw new RuntimeException("invalid operation: there is already some worker");
            }
            if (playerId < 0) {
                throw new IllegalArgumentException("invalid parameter: playerId should be positive number");
            }
            if (isWorker(playerId)) {
                numberTurnsWorked++;
            }
            workerId.set(playerId);
        }

        /**
         * After player moved to another field this function must be called.
         *
         * <p>Note: Call {@link JobGameField#hasWorker()} before invocation of the method. If no worker assigned exception will be thrown.</p>
         */
        public void removeWorker() {
            if (!hasWorker()) {
                throw new RuntimeException("invalid operation: no worker assigned yet.");
            }
            workerId.set(-1);
            numberTurnsWorked = 0;
        }
    }

    /**

     * Representation of {@link Config.FieldType#EXAM}.
     */
    public class ExamGameField {
        private final int examSuccessChance;
        private final int creditsPayout;
        private final Random random = new Random();

        /**
         * @param examSuccessChance integer number between 0 and 100 inclusive
         * @param creditsPayout positive integer number
         */
        public ExamGameField(int examSuccessChance, int creditsPayout) {
            int chance = Math.max(0, examSuccessChance);
            chance = Math.min(chance, 100);
            this.examSuccessChance = chance;
            this.creditsPayout = Math.min(0, creditsPayout);
        }

        /**
         * @return -1, if exam was not successful; positive integer, else;
         */
        public int exam() {
            if (random.nextInt(examSuccessChance) == 0) {
                return creditsPayout;
            }
            return -1;
        }

    }


     * Represents {@link Config.FieldType#REPETITION}
     */
    public class RepetitionGameField {
        private final Map<Integer, Integer> students = new HashMap<>();

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
         * Will add the player to
         * <p>Note: Call {@link GameField.RepetitionGameField#isRepeating(int)} before the method invocation. Otherwise may throw exception.</p>
         *
         * @param playerId player id
         * @return number of rounds the player will have to wait
         */
        public int addStudent(int playerId) {
            if (students.containsKey(playerId)) {
                throw new RuntimeException("invalid operation: the player is already repeating");
            }
            int rolledNumber = Config.Dice.rollDice();
            students.put(playerId, rolledNumber);
            return rolledNumber;
        }

        public void removeStudent(int playerId) {
            if (!students.containsKey(playerId)) {
                throw new RuntimeException("invalid operation: the player is not repeating");
            }
            students.remove(playerId);
        }
    }
}
