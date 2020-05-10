package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicTest {
    GameBoard gameBoard;

    private Player player1;
    private Logic logic;
    private Player player2;
    private Player player3;
    private Player player4;
    private int number = 0;

    @BeforeEach
    void setUp() {
        player1 = new Player("EZ", 100, 0, 1);
        player2 = new Player("EZ", 100, 0, 1);
        player3 = new Player("EZ", 100, 0, 1);
        player4 = new Player("EZ", 100, 0, 1);
        logic = new Logic();
        logic.addPlayer(player1);
        logic.addPlayer(player2);
        logic.addPlayer(player3);
        logic.addPlayer(player4);
        ArrayList<Player> players = new ArrayList<>(logic.getPlayers());
    }

    @AfterEach
    public void tearDown() {
        gameBoard = null;
    }

    @Test
    void addPlayerTest() {
        logic.addPlayer(player1);
        assertEquals(player1, logic.getPlayers().get(0));
    }


    @Test
    void nextPlayerTest() {

    }

    @Test
    void getCurrentPlayerTest() {
    }

    @Test
    void calculateNextFieldId() {
        final int boardSize = logic.getGameBoard().getBoardSize();
        Assertions.assertEquals(6 % boardSize, logic.calculateNextFieldId(0, 6));
        Assertions.assertEquals((boardSize - 1 + 10) % boardSize, logic.calculateNextFieldId(boardSize - 1, 10));

    }
}
