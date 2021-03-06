package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import javafx.scene.paint.Color;

public class Config {
    public static final int NUMBER_DICES = 2;
    public static final int START_MONEY = 100;
    public static final int START_CREDITS = 0;
    public static final int NUMBER_DICE_SIDES = 6;
    public static final int PLAYER_START_POSITION = 0;
    public static final int CREDITS_TO_WIN = 180;

    private static String fieldLayoutPath = "src/main/resources/field-layout.txt";

    //clockwise
    public static String getFieldLayoutPath() {
        return fieldLayoutPath;
    }

    public static void setFieldLayoutPath(String fieldLayoutPath) {
        Config.fieldLayoutPath = fieldLayoutPath;
    }

    /**
     * the enum with all possible fieldTypes and there name
     */
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
        PLAYER1("#3D86BA"),    //
        PLAYER2("#861515"),    // BLUE
        PLAYER3("#008631"),    // GREEN
        PLAYER4("#930a7f"),    // PURPLE
        UNOCCUPIED("#000000"); // BLACK

        /**
         * Hexadecimal color value to be used by FXML.
         */
        private final String colorValue;

        PlayerColor(String colorValue) {
            this.colorValue = colorValue;
        }

        public String getColorValue() {
            return colorValue;
        }

        public Color getColor() {
            return Color.web(colorValue);
        }
    }

}
