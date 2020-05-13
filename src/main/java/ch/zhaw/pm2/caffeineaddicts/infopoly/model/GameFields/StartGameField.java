package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#START}.
 */
public class StartGameField extends GameField {
    private static final int SCHOLARSHIP_MONEY = 100;
    private static final int PARENTS_HELP = 100;
    private static final int SCHOLARSHIP_WAITING_TIME = 3;
    private static final int PARENTS_HELP_ON_VISIT = 2 * PARENTS_HELP;

    public StartGameField(int fieldId, String fieldName) {
        super(fieldId, fieldName);
    }

    public static int getScholarshipMoney() {
        return SCHOLARSHIP_MONEY;
    }

    public static int getScholarshipWaitingTime() {
        return SCHOLARSHIP_WAITING_TIME;
    }

    public static int getParentsHelp() {
        return PARENTS_HELP;
    }

    /**
     * the action is called when a player enters the StartGameField
     * the the currentPlayer gets some money
     *
     * @param currentPlayer != null, the player who is standing on the field
     */
    @Override
    public void action(Player currentPlayer) {
        new InformationalWindow("Financial support from parents",
                String.format("You just visited your parents. They gave you %d.- CHF.",
                        PARENTS_HELP_ON_VISIT));
        currentPlayer.alterMoney(PARENTS_HELP_ON_VISIT);
    }
}