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
        final List<GameField> testBoard = new ArrayList<>();
        testBoard.add(new ModuleGameField(0, "module"));
        testBoard.add(new ModuleGameField(1, "module"));
        testBoard.add(new ModuleGameField(2, "module"));
        Config.setFieldLayoutPath("src/test/resources/test-field-layout.txt");
        GameBoard gameBoard = new GameBoard();
        assertEquals(testBoard, gameBoard.board);
    }


}