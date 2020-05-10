package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlayerTest {
    Player player;
    private GameField.ModuleGameField Analysis;

    @BeforeEach
    void setUp() {
        player = new Player("EZ",1000,100,1);
    }

    @Test
     void alterMoneyTest(){
        player.alterMoney(100);
        assertEquals(1100,player.getMoney());
        player.alterMoney(-100);
        assertEquals(1000,player.getMoney());
    }

    @Test
    void alterCreditsTest(){
        player.alterCredits(10);
        assertEquals(110,player.getCredits());
        player.alterCredits(-10);
        assertEquals(100,player.getCredits());
    }

    @Test
    void removeWorkTest(){
        assert(player.getOwnerShips().isEmpty());

    }

    @Test
    void moveTest(){
        player.move(2);
        assertEquals(2,player.getPositionProperty().getValue());
    }
    @Test
    void addOwnershipTest(){
        assert(player.getOwnerShips().isEmpty());
        player.addOwnerShip(Analysis);
        assertEquals(Analysis,player.getOwnerShips().get(0));
    }

}