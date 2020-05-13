package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    List<GameField> board;

    @BeforeEach
    void setUp() {
        board = new ArrayList<>();
    }

    @Test
    void loadGameBoard() {
        board.add(new ModuleGameField(0, "module"));
        board.add(new ModuleGameField(1, "module"));
        board.add(new ModuleGameField(2, "module"));
        Config.setFieldLayoutPath("src/test/resources/test-field-layout.txt");
        GameBoard gameBoard = new GameBoard();
        assertEquals(board, gameBoard.board);
    }

    @Test
    void loadGameBoardDifferentFields() {
        board.add(new FeeGameField(0, "fee", FeeGameField.FeeType.RANDOM));
        board.add(new ModuleGameField(1, "module"));
        board.add(new StartupGameField(2, "startup"));
        board.add(new RepetitionGameField(3, "repetition"));
        Config.setFieldLayoutPath("src/test/resources/test-field-layout-different-fields-txt");
        GameBoard gameBoard = new GameBoard();
        assertEquals(board, gameBoard.board);
    }
}