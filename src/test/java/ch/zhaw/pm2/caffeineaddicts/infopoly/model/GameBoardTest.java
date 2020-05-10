package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    //@BeforeAll
    void setUp() {
    }

    //@AfterAll
    void tearDown() {
    }

    @Test
    void loadGameBoard() {
        GameBoard gameBoard = new GameBoard();
        final List<GameField> testBoard = new ArrayList<>();
        Config.setFieldLayoutPath("src\\test\\resources\\test-field-layout.txt");
        //testBoard.add(new GameField(0, Config.FieldType.MODULE, "module"));
        //testBoard.add(new GameField(1, Config.FieldType.MODULE, "module"));
        //testBoard.add(new GameField(2, Config.FieldType.MODULE, "module"));
        gameBoard.loadGameBoard();
        assertEquals(testBoard, gameBoard.board);
    }
}