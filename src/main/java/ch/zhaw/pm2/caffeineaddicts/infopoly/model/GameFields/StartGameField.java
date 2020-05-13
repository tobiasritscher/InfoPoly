package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#START}.
 */
public class StartGameField extends GameField {
    public static final int SCHOLARSHIP_MONEY = 100;
    public static final int PARENTS_HELP = 100;
    public static final int SCHOLARSHIP_WAITING_TIME = 3;
    public static final int PARENTS_HELP_ON_VISIT = 2 * PARENTS_HELP;

    public StartGameField(int fieldId, String fieldName) {
        super(fieldId, fieldName);
    }

    @Override
    public void action(Player currentPlayer) {
        new InformationalWindow("Financial support from parents",
                String.format("You just visited your parents. They gave you %d.- CHF.",
                        PARENTS_HELP_ON_VISIT));
        currentPlayer.alterMoney(PARENTS_HELP_ON_VISIT);
    }
}