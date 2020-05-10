package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

public  class FeeGameField extends GameField {
    private final int baseFee;
    private final FeeType feeType;
    private final Random random = new Random();
    private final int RATE = 3;

    public FeeGameField(int fieldId, Config.FieldType fieldType, String fieldName, FeeType feeType, int baseFee) {
        super(fieldId, fieldType, fieldName);
        this.feeType = feeType;
        this.baseFee = baseFee;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public int getFee() {
        int value = baseFee;
        if (feeType.equals(FeeType.RANDOM)) {
            value += random.nextInt(RATE) * baseFee;
        }
        return value;
    }

    @Override
    public void action(Player currentPlayer) {
        //TODO
    }

    public enum FeeType {
        /**
         * Money will be charged at fixed rate.
         */
        FIXED,
        /**
         * Random amount of money will be charged.
         */
        RANDOM,
        /**
         * Fixed amount of money will be charged in return of some credits; Money go to the bank;
         */
        BANK_CREDIT
    }
}