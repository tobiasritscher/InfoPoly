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
    void loadGameBoard() {
        fields.add(new ModuleGameField(0, "module"));
        fields.add(new ModuleGameField(1, "module"));
        fields.add(new ModuleGameField(2, "module"));
        Config.setFieldLayoutPath("src/test/resources/test-gameboard-layout.txt");
        gameBoard = new GameBoard();
        assertEquals(fields, gameBoard.board);
    }

    @Test
    void loadGameBoardDifferentFields() {
        fields.add(new FeeGameField(0, "fee", FeeGameField.FeeType.RANDOM));
        fields.add(new ModuleGameField(1, "module"));
        fields.add(new StartupGameField(2, "startup"));
        fields.add(new RepetitionGameField(3, "repetition"));
        Config.setFieldLayoutPath("src/test/resources/test-gameboard-different-fields.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(0) instanceof FeeGameField);
        Assertions.assertTrue(gameBoard.getBoard().get(1) instanceof ModuleGameField);
        Assertions.assertTrue(gameBoard.getBoard().get(2) instanceof StartupGameField);
        Assertions.assertTrue(gameBoard.getBoard().get(3) instanceof RepetitionGameField);
        assertEquals(fields, gameBoard.board);
    }

    @Test
    void loadGameBoardModuleGameField() {
        final String moduleName = "test module";
        final int fieldId = 0;
        fields.add(new ModuleGameField(fieldId, moduleName));
        Config.setFieldLayoutPath("src/test/resources/test-gameboard-module.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof ModuleGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(fields.get(fieldId).getFieldName(), moduleName);
    }

    @Test
    void loadGameBoardChanceGameField() {
        final String moduleName = "test chance";
        final int fieldId = 0;
        fields.add(new ChanceGameField(fieldId, moduleName));
        Config.setFieldLayoutPath("src/test/resources/test-gameboard-chance.txt");
        gameBoard = new GameBoard();
        Assertions.assertTrue(gameBoard.getBoard().get(fieldId) instanceof ChanceGameField);
        Assertions.assertEquals(fieldId, gameBoard.getBoard().get(fieldId).getFieldId());
        Assertions.assertEquals(moduleName, gameBoard.getBoard().get(fieldId).getFieldName());
    }

    @Test
    void loadGameBoardFeeGameField() {
        final String moduleName = "test fee";
        final int fieldIdOne = 0;
        final int fieldIdTwo = 1;
        fields.add(new FeeGameField(fieldIdOne, moduleName, FeeGameField.FeeType.RANDOM));
        fields.add(new FeeGameField(fieldIdOne, moduleName, FeeGameField.FeeType.FIXED));

        Config.setFieldLayoutPath("src/test/resources/test-gameboard-fee.txt");
        gameBoard = new GameBoard();

        Assertions.assertTrue(gameBoard.getBoard().get(fieldIdOne) instanceof FeeGameField);
        Assertions.assertEquals(fieldIdOne, gameBoard.getBoard().get(fieldIdOne).getFieldId());
        Assertions.assertEquals(moduleName, gameBoard.getBoard().get(fieldIdOne).getFieldName());
        Assertions.assertEquals(FeeGameField.FeeType.RANDOM, ((FeeGameField) gameBoard.getBoard().get(fieldIdOne)).getFeeType());

        Assertions.assertTrue(gameBoard.getBoard().get(fieldIdTwo) instanceof FeeGameField);
        Assertions.assertEquals(fieldIdTwo, gameBoard.getBoard().get(fieldIdTwo).getFieldId());
        Assertions.assertEquals(moduleName, gameBoard.getBoard().get(fieldIdTwo).getFieldName());
        Assertions.assertEquals(FeeGameField.FeeType.FIXED, ((FeeGameField) gameBoard.getBoard().get(fieldIdTwo)).getFeeType());

    }
}