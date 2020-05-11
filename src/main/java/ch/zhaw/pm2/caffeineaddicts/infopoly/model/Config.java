package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Config {
    public static final int NUMBER_DICES = 2;
    public static final int START_MONEY = 100;
    public static final int START_CREDITS = 0;
    public static final int MINIMUM_CREDITS = 4;
    public static final int MEDIUM_CREDITS = 8;
    public static final int MANY_CREDITS = 12;
    public static final int BOARD_SIZE = 40;
    public static final int NUMBER_DICE_SIDES = 6;
    public static final int PLAYER_START_POSITION = 0;

    private static String fieldLayoutPath = "src/main/resources/field-layout.txt";

    //clockwise
    public static String getFieldLayoutPath() {
        return fieldLayoutPath;
    }

    public static void setFieldLayoutPath(String fieldLayoutPath) {
        Config.fieldLayoutPath = fieldLayoutPath;
    }

    public enum FieldType {
        /**
         * Field which can be purchased for money. Owner gets credits if some other player land on it. The player will lose money.
         */
        MODULE("Module"),
        /**
         * Field which, when purchased by player, will give some extra money when passing @{@link FieldType#START} field.
         */
        STARTUP("Startup"),
        /**
         * Field with possibility to get money by skipping the move.
         */
        JOB("Job"),
        /**
         * Field with random possibility to create some event.
         */
        CHANCE("ChanceGameField"),
        /**
         * Field all players to start on.
         * <p>Players which have no possibility to pay will be moved here. Random waiting time will be applied in order to get money.</p>
         * <p>Passing by the field gives fixed amount of money for the player.</p>
         */
        START("Start"),
        /**
         * A player will lose fixed amount of money on the field.
         */
        FEE("Fee"),
        /**
         * Field with randomly set waiting-timer for player on it.
         */
        REPETITION("Repeat the semester"),
        /**
         * Field with random chance weather to get extra credits or to send player on @{@link Config.FieldType#REPETITION} field.
         */
        EXAM("Exam");

        private final String fieldType;

        FieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        @Override
        public String toString() {
            return fieldType;
        }
    }

    /**
     * Enum class to simplify color values for each player.
     * Each color value is fixed. It is used to indicate which field is occupied by whom.
     *
     * @author corrooli
     */
    public enum PlayerColor {
        PLAYER1("#003f84"),    // RED
        PLAYER2("#861515"),    // BLUE
        PLAYER3("#008631"),    // GREEN
        PLAYER4("#930a7f"),    // PURPLE
        UNOCCUPIED("#000000"); // BLACK

        /**
         * Hexadecimal color value to be used by FXML.
         */
        private String colorValue;

        PlayerColor(String colorValue) {
            this.colorValue = colorValue;
        }

        public String getColorValue() {
            return colorValue;
        }
    }


    /**
     * Logical representation of physical dice.
     *
     * @author Corroli
     */
    public static class Dice {
        private static final Random random = new Random();

        /**
         * IntegerProperty for final dice roll. Needed for UI
         */
        private static IntegerProperty finalRoll = new SimpleIntegerProperty();

        /**
         * rolls two dices and moves the current player
         * if the player has three doubles, he has to repeate.
         */
        public static void rollDice(Logic logic) {
            boolean again;
            int counter = 0;
            int firstDice;
            int secondDice;

            do {
                firstDice = random.nextInt(NUMBER_DICE_SIDES) + 1;
                secondDice = random.nextInt(NUMBER_DICE_SIDES) + 1;
                int rolledNumber = firstDice + secondDice;

                if (firstDice == secondDice) {
                    new InformationalWindow("You rolled a double! YAY\nYou can move again.");
                    again = true;
                    ++counter;
                } else {
                    again = false;
                }

                if (counter == 3) {
                    new InformationalWindow("You rolled three times doubles, you have to repeate this semester.");
                    //todo not part of config!! > Logik
                    logic.moveCurrentPlayerToField(41);
                } else {
                    finalRoll.setValue(rolledNumber);
                    logic.movePlayer(rolledNumber);
                }
            } while (again);
        }

        public static IntegerProperty getFinalRollProperty() {
            return finalRoll;
        }
    }
}
