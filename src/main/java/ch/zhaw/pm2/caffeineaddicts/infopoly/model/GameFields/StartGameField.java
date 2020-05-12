package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents {@link Config.FieldType#START}.
 */
public class StartGameField extends GameField {
    public static final int PARENTS_HELP = 200;
    public static final int MONEY_AMOUNT = 200;
    public static final int SCHOLARSHIP_WAITING_TIME = 3;

    public StartGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    public int getParentsHelp() {
        return PARENTS_HELP;
    }

    @Override
    public void action(Player currentPlayer) {
        currentPlayer.alterMoney(MONEY_AMOUNT);
    }
}