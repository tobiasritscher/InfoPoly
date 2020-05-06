package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import java.util.Random;

public class Config {
    public static final int START_MONEY = 100;
    public static final int START_CREDITS = 0;
    private static String fieldLayoutPath = "src\\main\\resources\\field-layout.txt";

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
        CHANCE("Chance"),
        /**
         * Field all players to start on.
         * <p>Players which have no possibility to pay will be moved here. Random waiting time will be applied in order to get money.</p>
         * <p>Passing by the field gives fixed amount of money for the player.</p>
         */
        START("Start"),
        /**
         * A player will lose fixed amount of money on the field.
         */
        FEE_TYPE_ONE("Fee"),
        /**
         * A player will lose fixed amount <i>money</i> an get some amount of <i>credits</i> on the field.
         */
        FEE_TYPE_TWO("Fee"),
        /**
         * A player will lose <i>random</i> amount of money.
         */
        FEE_TYPE_THREE("Fee"),
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
     * Static Dice class. Due to the nature of how java.util.Random works, the class will have to be instanced and can't
     * be fully static
     *
     * @author Corroli
     */
    public static class Dice {
        private final Random random = new Random();
        private final int sides = 6;

        /**
         * Returns a dice value.
         *
         * @return Random value between 1 and 6
         */
        public int rollDice() {
            return random.nextInt(sides) + 1;
        }
    }
}
