package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.*;

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

    /**
     * Representation of {@link Config.FieldType#MODULE}
     */
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

    /**
     * Represents {@link Config.FieldType#STARTUP}.
     */
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

    public static class FeeGameField extends GameField {
        private final int baseFee;
        private final FeeType feeType;
        private final Random random = new Random();
        private final int RATE = 3;

        public FeeGameField(int fieldId, Config.FieldType fieldType, String fieldName, FeeType feeType, int baseFee) {
            super(fieldId, fieldType, fieldName);
            this.feeType = feeType;
            this.baseFee = baseFee;
        }

        public FeeType getFeeType() {
            return feeType;
        }

        public int getFee() {
            int value = baseFee;
            if (feeType.equals(FeeType.RANDOM)) {
                value += random.nextInt(RATE) * baseFee;
            }
            return value;
        }

        public enum FeeType {
            /**
             * Money will be charged at fixed rate.
             */
            FIXED,
            /**
             * Random amount of money will be charged.
             */
            RANDOM,
            /**
             * Fixed amount of money will be charged in return of some credits; Money go to the bank;
             */
            BANK_CREDIT
        }
    }

    /**
     * Represents @{@link Config.FieldType#CHANCE}.
     */
    public static class ChanceGameField{
        private final Random random = new Random();



        public enum ChanceEvent {
            EVENT1("You got run over by a bus. The medical fees cost CHF 150.-",
                    0, -150),
            EVENT2(
                    "You got caught with no ticket on a tram. You lose CHF 80.-",
                    0, -80),
            EVENT3(
                    "You got caught surfing a nasty website on the ZHAW network. You lose 5 credits.",
                    -5, 0),
            EVENT4(
                    "Your new game app went viral. You earn CHF 500.- from ad revenue",
                    0, 500),
            EVENT5(
                    "Your new fitness app went viral. You earn CHF 700.- from ad revenue",
                    0, 700),
            EVENT6(
                    "You win a programming contest. You get the prize money of CHF 1000.-",
                    0, 1000),
            EVENT7(
                    "Your program code was stolen by hackers. You lose CHF 200.- and your hard work.",
                    0, -200),
            EVENT8(
                    "Your new game app gained some traction, but you got sued because you used copyrighted assets. You lose CHF 900.-",
                    0, -900),
            EVENT9(
                    "Even though you have exams tomorrow, you went to a party. You lose 2 credits and spent CHF 100.-",
                    -2, -100),
            EVENT10(
                    "You overslept an exam. You lose 2 credits.",
                    -10, -100),
            EVENT11(
                    "Since you study Information Technology, your relatives confuse you with a PC repairman. You earn CHF 50.- by fixing your grandmother's Internet Explorer.",
                    0, 50);

            private final String message;
            private final int creditsDeviation;
            private final int moneyDeviation;

            /**
             * Constructor of ChanceEvent inner class.
             *
             * @param message          The message of the chance field.
             * @param creditsDeviation Addition/Reduction of credits
             * @param moneyDeviation   Addition/Reduction of money
             */
            ChanceEvent(String message, int creditsDeviation, int moneyDeviation) {
                this.message = message;
                this.creditsDeviation = creditsDeviation;
                this.moneyDeviation = moneyDeviation;
            }

            public String getMessage() {
                return message;
            }

            public int getCreditsDeviation() {
                return creditsDeviation;
            }

            public int getMoneyDeviation() {
                return moneyDeviation;
            }
        }
    }

    /**
     * Represents {@link Config.FieldType#JOB}.
     */
    public class JobGameField extends GameField {
        private final int baseWage;
        private final int wageIncreaseRate;
        private final IntegerProperty workerId = new SimpleIntegerProperty();
        private int numberTurnsWorked;

        public JobGameField(int fieldId, Config.FieldType fieldType, String fieldName, int baseWage, int wageIncreaseRate) {
            super(fieldId, fieldType, fieldName);
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
    public class ExamGameField extends GameField {
        private final int examSuccessChance;
        private final int creditsPayout;
        private final Random random = new Random();

        /**
         * @param examSuccessChance integer number between 0 and 100 inclusive
         * @param creditsPayout     positive integer number
         */
        public ExamGameField(int fieldId, Config.FieldType fieldType, String fieldName, int examSuccessChance, int creditsPayout) {
            super(fieldId, fieldType, fieldName);
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
    }

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
            students.put(playerId, Config.Dice.rollDice());
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
    }
}
