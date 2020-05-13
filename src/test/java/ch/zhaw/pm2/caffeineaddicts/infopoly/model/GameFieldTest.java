package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.ChanceGameField;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.GameField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameFieldTest {
    GameField gameField;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void chanceActionTest() {
        gameField = new ChanceGameField(1, "testChance");
        gameField.action(new Player("testPlayer", 100, 100, 1));
    }


    @Test
    void nextPlayerTest() {

    }

    @Test
    void getCurrentPlayerTest() {
    }

    @Test
    void calculateNextFieldId() {

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
}
