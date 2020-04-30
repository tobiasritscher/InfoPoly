import java.util.ArrayList;

/**
 * The class stores game fields of the game board.
 * <p>
 * Allows to access a field using it's id.
 */
public class GameBoard {

    private ArrayList<GameField> board;

    GameField getField(int fieldId) {
        if (fieldId < 0) {
            throw new RuntimeException("invalid index: must be positive");
        }
        if (fieldId >= board.size()) {
            throw new RuntimeException("invalid index: out of bound");
        }
        return board.get(fieldId);
    }

}
