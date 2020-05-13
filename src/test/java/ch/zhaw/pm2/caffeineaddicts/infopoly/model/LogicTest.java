package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogicTest {
    GameBoard gameBoard;
    private Logic logic;

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    void setUp() {
        player1 = new Player("EZ", 100, 0, 1);
        player2 = new Player("EZ", 100, 0, 2);
        player3 = new Player("EZ", 100, 0, 3);
        player4 = new Player("EZ", 100, 0, 4);
        logic = new Logic();
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
    void calculateNextFieldId() {
        final int boardSize = 32;
        Assertions.assertEquals(6 % boardSize, Logic.calculateNextFieldId(boardSize, 0, 6));
        Assertions.assertEquals((boardSize - 1 + 10) % boardSize, Logic.calculateNextFieldId(boardSize, boardSize - 1, 10));

    }

    @Test
    void jumpedOverField() {
        Assertions.assertTrue(Logic.jumpedOverField(0, 4, 1));
        Assertions.assertTrue(Logic.jumpedOverField(2, 1, 3));
        Assertions.assertFalse(Logic.jumpedOverField(0, 1, 2));
        Assertions.assertTrue(Logic.jumpedOverField(11, 10, 1));
    }

    @Test
    void calculateNextPlayerId() {
        Assertions.assertEquals(2, Logic.calculateNextPlayerId(4, 1));
        Assertions.assertEquals(0, Logic.calculateNextPlayerId(3, 2));

    }


    @Test
    void switchToNextPlayerTest(){
        Logic mock = mock(Logic.class);
        logic.addPlayer(player1);
        logic.addPlayer(player2);
        logic.addPlayer(player3);
        logic.addPlayer(player4);

        logic.setCurrentPlayerId(1);
        when(mock.calculateNextPlayerId(4,1)).thenReturn(2);
        logic.switchToNextPlayer();
        int now = logic.getPlayerTurnProperty().getValue();
        assertEquals(2,now);
    }

    @Test
    void playerHasToWaitTest(){

    }

    @Test
    void getScholarshipTest(){

    }

    @Test
    void movePlayerTest(){

    }

    @Test
    void parentsHelpTest(){

    }

    @Test
    public void DiceTest() {

        for (int i = 0; i < 100; i++) {
            logic.rollDice();
            int diceRoll = logic.getCurrentDiceRollProperty().getValue();
            assertTrue(diceRoll >= 2 && diceRoll <= 12);
        }
    }

}
