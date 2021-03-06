package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

public class FeeGameField extends GameField {
    private final int BASE_FEE = 30;
    private final FeeType feeType;
    private final Random random = new Random();
    private final int RATE = 3;
    public FeeGameField(int fieldId, String fieldName, FeeType feeType) {
        super(fieldId, fieldName);
        this.feeType = feeType;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    private int calculateFee() {
        int value = BASE_FEE;
        if (feeType.equals(FeeType.RANDOM)) {
            value += random.nextInt(RATE) * BASE_FEE;
        }
        return value;
    }

    /**
     * the action is called when a player enters the FeeGameField
     * the the currentPlayer will have to pay some money
     *
     * @param currentPlayer != null, the player who is standing on the field
     */
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