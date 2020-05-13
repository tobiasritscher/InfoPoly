package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields.JobGameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player;
    private JobGameField Burger;

    @BeforeEach
    void setUp() {
        player = new Player("EZ", 1000, 100, 1);
        Burger = new JobGameField(22, "Burger");
    }


    @Test
    void addJobTest() {
        assertNull(player.getJob());
        player.addJob(Burger);
        assertEquals(Burger, player.getJob());
    }

    @Test
    void removeJobTest() {
        player.addJob(Burger);
        player.removeJob();
        assertFalse(player.isWorking());
        assertNull(player.getJob());
    }

    @Test
    void setPositionTest() {
        player.setPosition(10);
        assertEquals(10, player.getPosition());
    }


    @Test
    void alterMoneyTest() {
        player.alterMoney(100);
        assertEquals(1100, player.getMoney());
        player.alterMoney(-100);
        assertEquals(1000, player.getMoney());
    }

    @Test
    void alterCreditsTest() {
        player.alterCredits(10);
        assertEquals(110, player.getCredits());
        player.alterCredits(-10);
        assertEquals(100, player.getCredits());
    }
}