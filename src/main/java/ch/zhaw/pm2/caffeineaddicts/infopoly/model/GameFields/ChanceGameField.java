package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.Random;

/**
 * Represents @{@link Config.FieldType#CHANCE}.
 */
public class ChanceGameField extends GameField {
    private final Random random = new Random();
    private ChanceEvent event;

    public ChanceGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
        generateEvent();
    }

    /**
     * Show message
     * Note: player may be broke or may win after the method was processed. Make sure in the caller function to consider the cases;
     *
     * @param currentPlayer the player who is standing on the field
     */
    @Override
    public void action(Player currentPlayer) {
        generateEvent();
        new InformationalWindow(getMessage());
        currentPlayer.alterMoney(getMoneyDeviation());
        currentPlayer.alterCredits(getCreditsDeviation());
    }

    public void generateEvent() {
        int eventId = random.nextInt(ChanceEvent.values().length - 1);
        event = ChanceEvent.values()[eventId];
    }

    public String getMessage() {
        if (event == null) {
            throw new RuntimeException("invalid operation: generate event first.");
        }
        return event.getMessage();
    }

    public int getCreditsDeviation() {
        if (event == null) {
            throw new RuntimeException("invalid operation: generate event first.");
        }
        return event.creditsDeviation;
    }

    public int getMoneyDeviation() {
        if (event == null) {
            throw new RuntimeException("invalid operation: generate event first.");
        }
        return event.moneyDeviation;
    }

    private enum ChanceEvent {
        EVENT1("You got run over by a bus. The medical fees cost CHF 150.-",
                0, -150),
        EVENT2(
                "You got caught with no ticket on a tram. You lose CHF 80.-",
                0, -80),
        EVENT3(
                "You got caught surfing a nasty website on the ZHAW network. You lose 5 credits.",
                -5, 0),
        EVENT4(
                "Your new game app went viral. You earn CHF 500.- from ad revenue",
                0, 500),
        EVENT5(
                "Your new fitness app went viral. You earn CHF 700.- from ad revenue",
                0, 700),
        EVENT6(
                "You win a programming contest. You get the prize money of CHF 1000.-",
                0, 1000),
        EVENT7(
                "Your program code was stolen by hackers. You lose CHF 200.- and your hard work.",
                0, -200),
        EVENT8(
                "Your new game app gained some traction, but you got sued because you used copyrighted assets. You lose CHF 900.-",
                0, -900),
        EVENT9(
                "Even though you have exams tomorrow, you went to a party. You lose 2 credits and spent CHF 100.-",
                -2, -100),
        EVENT10(
                "You overslept an exam. You lose 2 credits.",
                -2, 0),
        EVENT11(
                "Since you study Information Technology, your relatives confuse you with a PC repairman. You earn CHF 50.- by fixing your grandmother's Internet Explorer.",
                0, 50);

        private final String message;
        private final int creditsDeviation;
        private final int moneyDeviation;

        /**
         * Constructor of ChanceEvent inner class.
         *
         * @param message          The message of the chance field.
         * @param creditsDeviation Addition/Reduction of credits
         * @param moneyDeviation   Addition/Reduction of money
         */
        ChanceEvent(String message, int creditsDeviation, int moneyDeviation) {
            this.message = message;
            this.creditsDeviation = creditsDeviation;
            this.moneyDeviation = moneyDeviation;
        }

        public String getMessage() {
            return message;
        }

        public int getCreditsDeviation() {
            return creditsDeviation;
        }

        public int getMoneyDeviation() {
            return moneyDeviation;
        }
    }
}