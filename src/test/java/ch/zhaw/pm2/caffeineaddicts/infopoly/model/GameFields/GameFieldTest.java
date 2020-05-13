package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameFieldTest {
    GameField gameField;
    Player player;
    Player playerBroke;

    @BeforeEach
    void setUp() {
        player = new Player("TestNormal", 1000, 100, 1);
        playerBroke = new Player("TestBroke", 0, 0, 2);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void chanceActionTest() {
        gameField = new ChanceGameField(1, "testChance");
        ((ChanceGameField) gameField).makeChance(playerBroke);
        Assertions.assertEquals(((ChanceGameField) gameField).getMoneyDeviation(), playerBroke.getMoney());
    }


    @Test
    void initModuleGameField() {
        gameField = new ModuleGameField(22, "TestModule");
        Assertions.assertEquals(((ModuleGameField) gameField).getFieldPrice(), 90);
        Assertions.assertEquals(((ModuleGameField) gameField).getCreditsGain(), 15);
    }

    @Test
    void jobGameFieldTest() {
        gameField = new JobGameField(1, "TestJob");
        gameField.setOwner(player);
        Assertions.assertEquals(gameField.getOwner(), player);

        ((JobGameField) gameField).quitWork(player);
        Assertions.assertNull(gameField.getOwner());
        Assertions.assertNull(player.getJob());
    }

    @Test
    void childSupportTest() {
        gameField = new StartGameField(1, "TestStart");
        ((StartGameField) gameField).getChildSupport(playerBroke);

        Assertions.assertEquals(playerBroke.getMoney(), 200);
    }
}
