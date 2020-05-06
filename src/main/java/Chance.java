import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class for Chance fields. Consists of Chance main class which sports an ArrayList with hard-coded ChanceEvents objects
 * and a ChanceEvents inner class to make it work. This way you just need to instantiate a Chance object and draw as
 * many ChanceEvents as you like using Chance.getChanceEvent().
 * Get chance message:              chance.getChanceEvent().getMessage();
 * Get chance money deviation:      chance.getChanceEvent().getMoneyDeviation();
 * Get chance credits deviation:    chance.getChanceEvent().getCreditsDeviation();
 */
public class Chance {
    private final Random random = new Random();

    /**
     * Main ChanceEvent ArrayList. Add new events here by using the format below.
     */
    private final ArrayList<ChanceEvent> chanceEventArrayList = new ArrayList<>(Arrays.asList(
            new ChanceEvent(
                    "You got run over by a bus. The medical fees cost CHF 150.-",
                    0, -150),
            new ChanceEvent(
                    "You got caught with no ticket on a tram. You lose CHF 80.-",
                    0, -80),
            new ChanceEvent(
                    "You got caught surfing a nasty website on the ZHAW network. You lose 5 credits.",
                    -5, 0),
            new ChanceEvent(
                    "Your new game app went viral. You earn CHF 500.- from ad revenue",
                    0, 500),
            new ChanceEvent(
                    "Your new fitness app went viral. You earn CHF 700.- from ad revenue",
                    0, 700),
            new ChanceEvent(
                    "You win a programming contest. You get the prize money of CHF 1000.-",
                    0, 1000),
            new ChanceEvent(
                    "Your program code was stolen by hackers. You lose CHF 200.- and your hard work.",
                    0, -200),
            new ChanceEvent(
                    "Your new game app gained some traction, but you got sued because you used copyrighted assets. You lose CHF 900.-",
                    0, -900),
            new ChanceEvent(
                    "Even though you have exams tomorrow, you went to a party. You lose 2 credits and spent CHF 100.-",
                    -2, -100),
            new ChanceEvent(
                    "You overslept an exam. You lose 2 credits.",
                    -10, -100),
            new ChanceEvent(
                    "Since you study Information Technology, your relatives confuse you with a PC repairman. You earn CHF 50.- by fixing your grandmother's Internet Explorer.",
                    0, 50)
    ));

    /**
     * Returns a random chance event. This is where the magic happens.
     *
     * @return A ChanceEvent object
     */
    public ChanceEvent getChanceEvent() {
        return chanceEventArrayList.get(random.nextInt(chanceEventArrayList.size()));
    }


    /**
     * ChanceEvent data container inner class of Chance.
     */
    public static class ChanceEvent {
        private final String message;
        private final int creditsDeviation;
        private final int moneyDeviation;

        /**
         * Constructor of ChanceEvent inner class.
         *
         * @param message           The message of the chance field.
         * @param creditsDeviation  Addition/Reduction of credits
         * @param moneyDeviation    Addition/Reduction of money
         */
        public ChanceEvent(String message, int creditsDeviation, int moneyDeviation) {
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
