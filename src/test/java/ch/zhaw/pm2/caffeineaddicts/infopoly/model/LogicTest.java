package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LogicTest {
    private Player player1;
    private Logic logic;
    private Player player2;
    private Player player3;
    private Player player4;
    private int number = 0;

    @BeforeEach
    void setUp(){
        player1 = new Player("EZ",100,0,1);
        player2 = new Player("EZ",100,0,1);
        player3 = new Player("EZ",100,0,1);
        player4 = new Player("EZ",100,0,1);
        logic = new Logic();
        logic.addPlayer(player1);
        logic.addPlayer(player2);
        logic.addPlayer(player3);
        logic.addPlayer(player4);
        ArrayList<Player> players = new ArrayList<>(logic.getPlayers());
    }

    @Test
    void addPlayerTest(){
        logic.addPlayer(player1);
        assertEquals(player1,logic.getPlayers().get(0));
    }


    @Test
    void nextPlayerTest(){

    }

    @Test
    void getCurrentPlayerTest(){
    }
}
