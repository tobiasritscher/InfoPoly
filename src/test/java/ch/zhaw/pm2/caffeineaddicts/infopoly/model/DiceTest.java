package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {


    @Test
    public void rollDiceTest(){
        Logic dummyLogic = new Logic();
        dummyLogic.addPlayer(new Player());
        Config.Dice.rollDice(dummyLogic);
        int diceRoll = Config.Dice.getFinalRollProperty().get();

        for (int i = 0; i < 100; i++){
            assertTrue(diceRoll >= 1 && diceRoll <= 6);
        }
    }
}
