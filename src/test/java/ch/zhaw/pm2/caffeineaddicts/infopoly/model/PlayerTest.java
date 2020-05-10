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
        player = new Player("EZ",1000,100,1);
        Burger = new JobGameField(22, Config.FieldType.JOB,"Burger",30);
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
        player.addOwnerShip(Burger);
        assertNotNull(player.getOwnerShips());
        player.removeWork();
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
        player.addOwnerShip(Burger);
        assertEquals(Burger,player.getOwnerShips().get(0));
    }


    @Test
    void setRoundsWaitingTest(){
        assertEquals(0,player.getRoundsWaiting());
        player.setRoundsWaiting(3);
        assertEquals(3,player.getRoundsWaiting());
        player.setRoundsWaiting(-1);
        assertEquals(2,player.getRoundsWaiting());
    }

    @Test
    void addWorkTest(){
        assertFalse(player.isWorking());
        player.addWork(Burger);
        assertEquals(Burger,player.getOwnerShips().get(0));
    }
}