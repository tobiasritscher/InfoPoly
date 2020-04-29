/**
 * Enum class to simplify color values for each player.
 * Each color value is fixed. It is used to indicate which field is occupied by whom.
 */

public enum PlayerColor {
    PLAYER1("#003f84"), // RED
    PLAYER2("#861515"), // BLUE
    PLAYER3("#008631"), // GREEN
    PLAYER4("#930a7f"); // PURPLE

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
