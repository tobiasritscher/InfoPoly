package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents {@link Config.FieldType#START}.
 */
public class StartGameField extends GameField {
    public static final int PARENTS_HELP = 200;
    public static final int SCHOLARSHIP_WAITING_TIME = 3;
    public static final int PARENTS_HELP_ON_VISIT = 2 * PARENTS_HELP;

    public StartGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    @Override
    public void action(Player currentPlayer) {
        new InformationalWindow("Parents help!", String.format("You just visited you parents. They gave you %d.-CHF.", PARENTS_HELP_ON_VISIT));
        currentPlayer.alterMoney(PARENTS_HELP_ON_VISIT);
    }
}