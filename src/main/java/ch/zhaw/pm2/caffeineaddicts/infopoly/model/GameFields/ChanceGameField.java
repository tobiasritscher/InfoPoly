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
        new InformationalWindow("Wuuups...Something has happened!!!", getMessage());
        currentPlayer.alterMoney(getMoneyDeviation());
        currentPlayer.alterCredits(getCreditsDeviation());
    }

    public void generateEvent() {
        int eventId = random.nextInt(ChanceEvent.values().length - 1);
        event = ChanceEvent.values()[eventId];
    }

    public String getMessage() {
        return event.getMessage();
    }

    public int getCreditsDeviation() {
        return event.creditsDeviation;
    }

    public int getMoneyDeviation() {
        return event.moneyDeviation;
    }

    private enum ChanceEvent {
        EVENT1("You got run over by a bus.\nThe medical fees cost CHF 50.-",
                0, -50),
        EVENT2(
                "You got caught with no ticket on a tram.\nYou lose CHF 80.-",
                0, -80),
        EVENT3(
                "You got caught surfing a nasty website on the ZHAW network.\nYou lose 5 credits.",
                -5, 0),
        EVENT4(
                "Your new game app went viral. You earn CHF 100.- from ad revenue",
                0, 100),
        EVENT5(
                "Your new fitness app went viral. You earn CHF 200.- from ad revenue",
                0, 200),
        EVENT6(
                "You win a programming contest.\nYou get the prize money of CHF 100.-",
                0, 100),
        EVENT7(
                "Your program code was stolen by hackers.\nYou lose CHF 100.- and your hard work.",
                0, -100),
        EVENT8(
                "Your new game app gained some traction, but you got sued because you used copyrighted assets.\nYou lose CHF 90.-",
                0, -90),
        EVENT9(
                "Even though you have exams tomorrow, you went to a party.\nYou lose 2 credits and spent CHF 40.-",
                -2, -40),
        EVENT10(
                "You overslept an exam. You lose 2 credits.",
                -2, 0),
        EVENT11(
                "Since you study Information Technology, your relatives \nconfuse you with a PC repairman.\nYou earn CHF 50.- by fixing your grandmother's Internet Explorer.",
                0, 50);

        private final String message;
        private final int creditsDeviation;
        private final int moneyDeviation;

        /**
         * Constructor of ChanceEvent class.
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
    }
}