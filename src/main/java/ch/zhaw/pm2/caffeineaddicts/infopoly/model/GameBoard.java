package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class stores game fields of the game board.
 * <p>
 * Allows to access a field using it's id.
 */
public class GameBoard {
    private final static Logger logger = Logger.getLogger(GameBoard.class.getCanonicalName());

    final List<GameField> board = new ArrayList<>();

    public GameBoard() {
        //loadGameBoard();
    }

    GameField getField(int fieldId) {
        validateFieldId(fieldId);
        return board.get(fieldId);
    }

    private void validateFieldId(int fieldId) {
        if (board.isEmpty()) {
            throw new RuntimeException("invalid operation: game bord must be initialized first");
        }
        if (fieldId < 0) {
            throw new RuntimeException("invalid index: must be positive");
        }
        if (fieldId >= board.size()) {
            throw new RuntimeException("invalid index: out of bound");
        }
    }

    public Config.FieldType getFieldType(int fieldId) {
        validateFieldId(fieldId);
        return board.get(fieldId).getFieldType();
    }

    public String getFieldName(int fieldId) {
        validateFieldId(fieldId);
        return board.get(fieldId).getFieldName();
    }

    public int getBoardSize() {
        if (board.isEmpty()) {
            throw new RuntimeException("invalid operation: game field must be initialized first");
        }
        return board.size();
    }

    public void loadGameBoard() {
        File file = null;
        Scanner sc = null;
        String pathName = Config.getFieldLayoutPath();
        file = new File(pathName);
        logger.log(Level.FINE, "starting reading");
        try {
            sc = new Scanner(file);
            //read number of fields
            if (!sc.hasNext()) {
                throw new RuntimeException("invalid file format: number of fields is expected");
            }
            int numberFields = Integer.parseInt(sc.nextLine().strip());
            if (numberFields < 1) {
                throw new RuntimeException("invalid file format: number of fields should be greater than one");
            }
            int fieldId = -1;
            for (int i = 0; i < numberFields; i++) {
                fieldId++;

                //read number attributes
                int numberAttributes = Integer.parseInt(sc.nextLine().strip());
                if (numberAttributes < 1) {
                    throw new RuntimeException("invalid file format: number of field attributes should be greater than one");
                }

                //read field type
                if (!sc.hasNext()) {
                    throw new RuntimeException("invalid file format: field type is expected");
                }
                String fieldTypeString = sc.nextLine().strip();
                Config.FieldType fieldType = Config.FieldType.valueOf(fieldTypeString.toUpperCase());

                numberAttributes--;

                //read field name
                if (!sc.hasNext()) {
                    throw new RuntimeException("invalid file format: field name is expected");
                }
                String fieldName = sc.nextLine().strip();

                numberAttributes--;

                for (int a = 0; a < numberAttributes; a++) {
                    if (!sc.hasNext()) {
                        throw new RuntimeException("invalid file format: an attribute is expected");
                    }
                    sc.nextLine().strip();
                }


                //TODO: add all fields with the right subclas
                //board.add(new GameField.StartupGameField(fieldId, fieldType, fieldName, 10, 10));

            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        } finally {
            sc.close();
            logger.log(Level.FINE, "stopped reading");
        }

    }

    /**
     * Board getter. Needed for UI Listeners
     *
     * @return entire GameBoard list
     */
    public List<GameField> getBoard() {
        return board;
    }


}
