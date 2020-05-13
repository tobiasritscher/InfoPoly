package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void switchToNextPlayerTest() {
        logic.addPlayer(player1);
        logic.addPlayer(player2);
        logic.addPlayer(player3);
        logic.addPlayer(player4);

        Logic.setCurrentPlayerId(1);
        Assertions.assertEquals(Logic.calculateNextPlayerId(4, 1), 2);
        logic.switchToNextPlayer();
        int now = logic.getPlayerTurnProperty().getValue();
        assertEquals(2, now);
    }

    @Test
    void playerHasToWaitTest() {
        player1.setWaitingForScholarship(false);
        Assertions.assertFalse(logic.playerHasToWait(player1));
    }

    @Test
    void getScholarshipTest() {
        player1.alterMoney(-100);
        Assertions.assertEquals(player1.getMoney(), 0);

        logic.getScholarship(player1);
        Assertions.assertEquals(player1.getMoney(), 100);
        Assertions.assertFalse(player1.isWaitingForScholarship());
    }

    @Test
    void movePlayerTest() {
        gameBoard = new GameBoard();
        player1.setPosition(gameBoard.getExamGameFieldId());
        player1.setRoundsWaiting(2);
        Assertions.assertTrue(logic.playerHasToWaitOnExam(player1));

        player2.alterMoney(-200);
        Assertions.assertTrue(logic.playerIsBroke(player2));
        Assertions.assertFalse(logic.playerIsBroke(player1));

        player3.alterCredits(180);
        Assertions.assertTrue(logic.playerHasWon(player3));
        Assertions.assertFalse(logic.playerHasWon(player2));
    }

    @Test
    public void DiceNumberTest() {
        for (int i = 0; i < 100; i++) {
            int diceRoll = logic.diceNumber();
            assertTrue(diceRoll >= 1 && diceRoll <= 6);
        }
    }
}
