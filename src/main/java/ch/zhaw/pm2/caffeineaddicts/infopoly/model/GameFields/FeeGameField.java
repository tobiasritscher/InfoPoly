package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

public class FeeGameField extends GameField {
    public final int BASE_FEE = 30;
    private final FeeType feeType;
    private final Random random = new Random();
    private final int RATE = 3;

    public FeeGameField(int fieldId, String fieldName, FeeType feeType) {
        super(fieldId, fieldName);
        this.feeType = feeType;
    }


    private int calculateFee() {
        int value = BASE_FEE;
        if (feeType.equals(FeeType.RANDOM)) {
            value += random.nextInt(RATE) * BASE_FEE;
        }
        return value;
    }

    @Override
    public void action(Player currentPlayer) {
        int fee = calculateFee();
        new InformationalWindow("New fee", String.format("You have to pay %d.-CHF!", fee));
        currentPlayer.alterMoney(-fee);
    }

    /**
     * Enum for the different Feetypes
     */
    public enum FeeType {
        /**
         * Money will be charged at fixed rate.
         */
        FIXED,
        /**
         * Random amount of money will be charged.
         */
        RANDOM
    }
}