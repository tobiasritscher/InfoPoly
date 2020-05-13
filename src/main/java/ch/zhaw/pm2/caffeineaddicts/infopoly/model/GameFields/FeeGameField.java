package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

public class FeeGameField extends GameField {
    public  final int baseFee = 30;
    private final FeeType feeType;
    private final Random random = new Random();
    private final int rate = 3;

    public FeeGameField(int fieldId, String fieldName, FeeType feeType) {
        super(fieldId, fieldName);
        this.feeType = feeType;
    }


    private int calculateFee() {
        int value = baseFee;
        if (feeType.equals(FeeType.RANDOM)) {
            value += random.nextInt(rate) * baseFee;
        }
        return value;
    }

    @Override
    public void action(Player currentPlayer) {
        int fee = calculateFee();
        new InformationalWindow("Nothing is free of charge...", String.format("You have to pay %d.-CHF!", fee));
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