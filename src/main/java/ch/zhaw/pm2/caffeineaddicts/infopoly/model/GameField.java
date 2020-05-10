package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public abstract class GameField {
    private final int fieldId;
    private final Config.FieldType fieldType;
    private final String fieldName;
    private Player owner = null;
    private IntegerProperty ownerProperty = new SimpleIntegerProperty(0);

    public GameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        this.fieldId = fieldId;
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public IntegerProperty getOwnerProperty() {
        return ownerProperty;
    }

    public Config.FieldType getFieldType() {
        return fieldType;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    /**
     * Sets a new owner and changes the ownerProperty to the according playerNumber.
     *
     * @param owner The Player object which is subject to change.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        ownerProperty.setValue(owner.getPlayerNumber());
    }

    /**
     * Resets the field owner and changes the ownerProperty to zero.
     */
    public void resetOwner() {
        owner = null;
        ownerProperty.setValue(0);
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public abstract void action(Player currentPlayer);

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
    public class ModuleGameField extends GameField {
        private final int fieldPrice;
        private final int fieldMoneyCharge;
        private final int creditsGain;

        public ModuleGameField(int fieldId, Config.FieldType fieldType, String fieldName, int fieldPrice, int fieldMoneyCharge, int creditsGain) {
            super(fieldId, fieldType, fieldName);
            this.fieldPrice = fieldPrice;
            this.fieldMoneyCharge = fieldMoneyCharge;
            this.creditsGain = creditsGain;
        }

        public int getFieldMoneyCharge() {
            return fieldMoneyCharge;
        }

        public int getFieldPrice() {
            return fieldPrice;
        }

        public int getCreditsGain() {
            return creditsGain;
        }

        @Override
        public void action(Player currentPlayer) {

            if (hasOwner()) {
                if (owner.equals(currentPlayer)) {
                    new InformationalWindow("You already own this field");

                } else if (currentPlayer.getMoney() < getFieldMoneyCharge()) {
                    owner.setMoney(currentPlayer.getMoney());
                    owner.setCredits(getCreditsGain());
                    currentPlayer.setMoney(0);
                } else {
                    currentPlayer.setMoney(currentPlayer.getMoney() - getFieldMoneyCharge());
                    owner.setMoney(getFieldMoneyCharge());
                    owner.setCredits(getCreditsGain());
                }
            } else {
                if (currentPlayer.getMoney() >= getFieldPrice()) {
                    QuestionWindow questionWindow = new QuestionWindow("Buy course", "Would you like to buy this course");
                    if (questionWindow.getAnswer()) {
                        currentPlayer.setMoney(currentPlayer.getMoney() - getFieldPrice());
                        setOwner(currentPlayer);
                    }
                } else {
                    new InformationalWindow("You are to poor to buy this field. Get a job!");
                }
            }
        }
    }

    /**
     * Represents {@link Config.FieldType#STARTUP}.
     */
    public class StartupGameField extends GameField {
        private final int moneyNeeded;
        private final int moneyPayout;
        private final IntegerProperty founderId = new SimpleIntegerProperty(); //TODO: for what????

        public StartupGameField(int fieldId, Config.FieldType fieldType, String fieldName, int moneyNeeded, int moneyPayout) {
            super(fieldId, fieldType, fieldName);
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

        @Override
        public void action(Player currentPlayer) {
            final int NEEDED_AMOUNT_CREDITS = 100;

            if (owner.equals(currentPlayer)) {
                if (isLaunched()) {
                    new InformationalWindow("Startup is already created with your Idea...had to be fast!");
                } else {
                    new InformationalWindow("Your startup made quite the turnover this week! +200CHF");
                    currentPlayer.alterMoney(200);
                }
            } else {
                if (currentPlayer.getCredits() >= NEEDED_AMOUNT_CREDITS) {
                    if (currentPlayer.getMoney() >= getMoneyNeeded()) {
                        QuestionWindow questionWindow = new QuestionWindow("Startup Manager", "Would you like to create your first startup?");
                        if (questionWindow.getAnswer()) {
                            setOwner(currentPlayer);
                            currentPlayer.setMoney(currentPlayer.getMoney() - getMoneyNeeded());
                        } else {
                            new InformationalWindow("I guess not everyone is up to the challenge...");
                        }
                    } else {
                        new InformationalWindow("You require: " + getMoneyNeeded() + " in order to start your first Startup");
                    }
                } else {
                    new InformationalWindow("A successful startup requires the needed knowledge...(" + NEEDED_AMOUNT_CREDITS + " Credits)");
                }
            }
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

        @Override
        public void action(Player currentPlayer) {
            //TODO
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
    public static class ChanceGameField extends GameField {
        private final Random random = new Random();
        private ChanceEvent event;

        public ChanceGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
            super(fieldId, fieldType, fieldName);
            generateEvent();
        }

        /**
         * Show message
         * Note: player may be broke or may win after the method was processed. Make sure in the caller function to consider the cases;
         *
         * @param currentPlayer the player who is standing on the field
         */
        @Override
        public void action(Player currentPlayer) {
            generateEvent();
            //todo just idea >> could be used listeners instead.
            new InformationalWindow(getMessage());
            currentPlayer.alterMoney(getMoneyDeviation());
            currentPlayer.alterCredits(getCreditsDeviation());
        }

        public void generateEvent() {
            int eventId = random.nextInt(ChanceEvent.values().length - 1);
            event = ChanceEvent.values()[eventId];
        }

        public String getMessage() {
            if (event == null) {
                throw new RuntimeException("invalid operation: generate event first.");
            }
            return event.getMessage();
        }

        public int getCreditsDeviation() {
            if (event == null) {
                throw new RuntimeException("invalid operation: generate event first.");
            }
            return event.creditsDeviation;
        }

        public int getMoneyDeviation() {
            if (event == null) {
                throw new RuntimeException("invalid operation: generate event first.");
            }
            return event.moneyDeviation;
        }

        private enum ChanceEvent {
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
                    -2, 0),
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
        private int baseWage;

        public JobGameField(int fieldId, Config.FieldType fieldType, String fieldName, int baseWage) {
            super(fieldId, fieldType, fieldName);
            this.baseWage = baseWage;
        }

        private void quitWork(Player player) {
            QuestionWindow questionWindow = new QuestionWindow("Quit job", "Do you really want to quit your job?");
            if (questionWindow.getAnswer()) {
                player.setWorking(false);
                player.removeWork();
            }
        }

        @Override
        public void action(Player currentPlayer) {

            if (hasOwner()) {
                if (currentPlayer.equals(owner)) {
                    new InformationalWindow("You are already working here. You made an extra shift: +" + baseWage + "CHF");
                    currentPlayer.alterMoney(baseWage);
                } else {
                    new InformationalWindow("Thank you for shopping with us!");
                    currentPlayer.alterMoney(baseWage * -1);
                    owner.alterMoney(baseWage);
                }
            } else {
                if (currentPlayer.isWorking()) {
                    QuestionWindow questionWindow = new QuestionWindow("Job Application " + fieldName + " (" + baseWage + "CHF)",
                            "You already have a job!\nWould you like to quit it?");
                    if (questionWindow.getAnswer()) {
                        quitWork(currentPlayer);
                    }
                } else {
                    QuestionWindow questionWindow = new QuestionWindow("Job Application " + fieldName + " (" + baseWage + "CHF)",
                            "Would you like to start working here?");
                    if (questionWindow.getAnswer()) {
                        setOwner(currentPlayer);
                        currentPlayer.setWorking(true);
                    }
                }
            }
        }
    }

    /**
     * Representation of {@link Config.FieldType#EXAM}.
     */
    public class ExamGameField extends GameField {

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
            new InformationalWindow("You are taking an exam, if you fail you have to repeat!");

            if (passed()) {
                new InformationalWindow("You have passed your exam! YAY");
                currentPlayer.alterCredits(Config.MANY_CREDITS);
            } else {
                new InformationalWindow("You have failed, you need to repeat this semester!");
                currentPlayer.setPosition(41); // TODO Field 41 is reserved for repetition. Change code if needed
                //TODO repeating?
            }
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

        @Override
        public void action(Player currentPlayer) {
            //TODO: repeating
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
}
