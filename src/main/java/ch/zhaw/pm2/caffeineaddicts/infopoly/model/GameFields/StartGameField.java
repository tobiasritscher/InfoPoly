package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents {@link Config.FieldType#START}.
 */
public class StartGameField extends GameField {
    private final int BASE_SCHOLARSHIP = 10;
    private final int PARENTS_HELP = 200;
    private final int SCHOLARSHIP_WAIT_TIME = 3;
    private final int MONEY_AMOUNT = 200;

    public int getScholarshipWaitTime() {
        return SCHOLARSHIP_WAIT_TIME;
    }

    public StartGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    public int getParentsHelp() {
        return PARENTS_HELP;
    }

    public int getBaseScholarship() {
        return BASE_SCHOLARSHIP;
    }

    @Override
    public void action(Player currentPlayer) {
        currentPlayer.alterMoney(MONEY_AMOUNT);
    }
}