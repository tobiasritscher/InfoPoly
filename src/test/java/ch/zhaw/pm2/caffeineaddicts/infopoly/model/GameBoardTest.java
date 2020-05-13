package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.GameField;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.ModuleGameField;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void loadGameBoard() {
        GameBoard gameBoard = new GameBoard();
        final List<GameField> testBoard = new ArrayList<>();
        Config.setFieldLayoutPath("src\\test\\resources\\test-field-layout.txt");
        testBoard.add(new ModuleGameField(0, "module"));
        testBoard.add(new ModuleGameField(1, "module"));
        testBoard.add(new ModuleGameField(2, "module"));
        gameBoard.loadGameBoard();
        assertEquals(testBoard, gameBoard.board);
    }


}