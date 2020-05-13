package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameBoardTest {
    private List<GameField> fields;
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        fields = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        fields = null;
        gameBoard = null;
    }

    @Test
    void loadGameBoardDifferentFields() {
        fields.add(new FeeGameField(0, "fee", FeeGameField.FeeType.RANDOM));
        fields.add(new ModuleGameField(1, "module"));
        fields.add(new StartupGameField(2, "startup"));
        fields.add(new RepetitionGameField(3, "repetition"));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-different-fields.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(0) instanceof FeeGameField);
        Assertions.assertTrue(gameBoard.getBoard().get(1) instanceof ModuleGameField);
        Assertions.assertTrue(gameBoard.getBoard().get(2) instanceof StartupGameField);
        Assertions.assertTrue(gameBoard.getBoard().get(3) instanceof RepetitionGameField);
        assertEquals(fields, gameBoard.board);
    }

    /**
     * Checks if {@link ChanceGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardChanceGameField() {
        final String moduleName = "test chance";
        final int fieldId = 0;
        fields.add(new ChanceGameField(fieldId, moduleName));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-chance.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof ChanceGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(moduleName, gameBoard.getBoard().get(fieldId).getFieldName());
    }

    /**
     * Checks if {@link ExamGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardExamGameField() {
        final String name = "test exam";
        final int fieldId = 0;
        fields.add(new ExamGameField(fieldId, name));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-exam.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof ExamGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(name, gameBoard.getBoard().get(fieldId).getFieldName());
    }

    /**
     * Checks if {@link FeeGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardFeeGameField() {
        final String name = "test fee";
        final int fieldIdOne = 0;
        final int fieldIdTwo = 1;
        fields.add(new FeeGameField(fieldIdOne, name, FeeGameField.FeeType.RANDOM));
        fields.add(new FeeGameField(fieldIdOne, name, FeeGameField.FeeType.FIXED));

        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-fee.txt");
        gameBoard = new GameBoard();

        Assertions.assertTrue(gameBoard.getBoard().get(fieldIdOne) instanceof FeeGameField);
        Assertions.assertEquals(fieldIdOne, gameBoard.getBoard().get(fieldIdOne).getFieldId());
        Assertions.assertEquals(name, gameBoard.getBoard().get(fieldIdOne).getFieldName());
        Assertions.assertEquals(FeeGameField.FeeType.RANDOM, ((FeeGameField) gameBoard.getBoard().get(fieldIdOne)).getFeeType());

        Assertions.assertTrue(gameBoard.getBoard().get(fieldIdTwo) instanceof FeeGameField);
        Assertions.assertEquals(fieldIdTwo, gameBoard.getBoard().get(fieldIdTwo).getFieldId());
        Assertions.assertEquals(name, gameBoard.getBoard().get(fieldIdTwo).getFieldName());
        Assertions.assertEquals(FeeGameField.FeeType.FIXED, ((FeeGameField) gameBoard.getBoard().get(fieldIdTwo)).getFeeType());

    }

    /**
     * Checks if {@link JobGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardJobGameField() {
        final String name = "test job";
        final int fieldId = 0;
        fields.add(new JobGameField(fieldId, name));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-job.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof JobGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(name, gameBoard.getBoard().get(fieldId).getFieldName());
    }

    /**
     * Checks if {@link ModuleGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardModuleGameField() {
        final String name = "test module";
        final int fieldId = 0;
        fields.add(new ModuleGameField(fieldId, name));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-module.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof ModuleGameField);
        Assertions.assertEquals(name, gameBoard.getBoard().get(fieldId).getFieldName());
    }

    /**
     * Checks if {@link RepetitionGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardRepetitionGameField() {
        final String name = "test repetition";
        final int fieldId = 0;
        fields.add(new RepetitionGameField(fieldId, name));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-repetition.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof RepetitionGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(name, gameBoard.getBoard().get(fieldId).getFieldName());
    }

    /**
     * Checks if {@link StartGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardStartGameField() {
        final String name = "test start";
        final int fieldId = 0;
        fields.add(new StartGameField(fieldId, name));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-start.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof StartGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(fields.get(fieldId).getFieldName(), name);
    }

    /**
     * Checks if {@link StartupGameField} is correctly initialized from the file.
     */
    @Test
    void loadGameBoardStartupGameField() {
        final String name = "test startup";
        final int fieldId = 0;
        fields.add(new StartupGameField(fieldId, name));
        Config.setFieldLayoutPath("src/test/resources/GameBoardTest/test-gameboard-startup.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof StartupGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(fields.get(fieldId).getFieldName(), name);
    }
}