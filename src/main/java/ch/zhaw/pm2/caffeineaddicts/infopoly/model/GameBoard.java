package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * The class stores game fields of the game board.
 * <p>
 * Allows to access a field using it's id.
 */
public class GameBoard {
    private final static Logger logger = Logger.getLogger(GameBoard.class.getCanonicalName());

    final List<GameField> board = new ArrayList<>();
    private int startGameFieldId;
    private int repetitionGameFieldId;
    private int examGameFieldId;

    public GameBoard() {
        InputStream logConfig = this.getClass().getClassLoader().getResourceAsStream("log.properties");
        try {
            LogManager.getLogManager().readConfiguration(logConfig);
        } catch (IOException e) {
            logger.log(Level.WARNING, "No log.properties", e);
        }
        Logger.getLogger(GameBoard.class.getPackageName());
        loadGameBoard();
    }

    public StartGameField getStartGameField() {
        return (StartGameField) board.get(startGameFieldId);
    }

    public GameField getField(int fieldId) {
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

    public int getBoardSize() {
        if (board.isEmpty()) {
            throw new RuntimeException("invalid operation: game field must be initialized first");
        }
        return board.size();
    }

    void loadGameBoard() {
        File file;
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

                String[] gameFieldArgs = new String[numberAttributes];
                gameFieldArgs[0] = Integer.toString(fieldId);
                for (int a = 1; a < numberAttributes; a++) {
                    if (!sc.hasNext()) {
                        throw new RuntimeException("invalid file format: an attribute is expected");
                    }
                    gameFieldArgs[a] = sc.nextLine().strip();
                }
                initializeGameField(fieldType, gameFieldArgs);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        } finally {
            assert sc != null;
            sc.close();
            logger.log(Level.FINE, "stopped reading");
        }
    }

    private void initializeGameField(Config.FieldType fieldType, String[] gameFieldArgs) {
        final int fieldId = Integer.parseInt(gameFieldArgs[0]);
        final String fieldName = gameFieldArgs[1];
        GameField gameField;
        switch (fieldType) {
            case MODULE:
                gameField = new ModuleGameField(fieldId, fieldName);
                break;
            case STARTUP:
                gameField = new StartupGameField(fieldId, fieldName);
                break;
            case JOB:
                gameField = new JobGameField(fieldId, fieldName);
                break;
            case CHANCE:
                gameField = new ChanceGameField(fieldId, fieldName);
                break;
            case START:
                gameField = new StartGameField(fieldId, fieldName);
                startGameFieldId = fieldId;
                break;
            case FEE:
                FeeGameField.FeeType feeType = FeeGameField.FeeType.FIXED;
                if ("ran".equals(gameFieldArgs[2])) {
                    feeType = FeeGameField.FeeType.RANDOM;
                }
                gameField = new FeeGameField(fieldId, fieldName, feeType);
                break;
            case REPETITION:
                gameField = new RepetitionGameField(fieldId, fieldName);
                repetitionGameFieldId = fieldId;
                break;
            case EXAM:
                gameField = new ExamGameField(fieldId, fieldName);
                examGameFieldId = fieldId;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fieldType);
        }
        board.add(gameField);
        logger.log(Level.FINE, "Field added: " + gameField.toString());
    }

    /**
     * Board getter. Needed for UI Listeners
     *
     * @return entire GameBoard list
     */
    public List<GameField> getBoard() {
        return board;
    }


    public int getRepetitionGameFieldId() {
        return repetitionGameFieldId;
    }

    public int getExamGameFieldId() {
        return examGameFieldId;
    }
}
