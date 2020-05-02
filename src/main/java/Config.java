public class Config {
    public static final int START_MONEY = 100;
    //clockwise
    FieldType[] gameBoard = {FieldType.START, FieldType.MODULE, FieldType.MODULE, FieldType.MODULE,};

    public enum FieldType {
        MODULE("Module"), STARTUP("Startup"),

        JOB("Job"), CHANCE("Chance"),

        START("Start"), SCHOLARSHIP("Scholarship"), PARTY("Party"), FEE("FEE");

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

        public String getColorValue(){
            return colorValue;
        }
    }
}
