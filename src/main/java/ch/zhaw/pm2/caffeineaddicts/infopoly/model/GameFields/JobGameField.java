package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#JOB}.
 */
public class JobGameField extends GameField {
    final int BASE_WAGE = 10;

    public JobGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    private void quitWork(Player player) {
        QuestionWindow questionWindow = new QuestionWindow("Quit job", "Do you really want to quit your job?");
        if (questionWindow.getAnswer()) {
            player.setWorking(false);
            player.removeWork();
        }
    }

    @Override
    public void action(Player currentPlayer) {

        if (hasOwner()) {
            if (currentPlayer.equals(getOwner())) {
                new InformationalWindow("Forgot?", "You are already working here. You made an extra shift: +" + BASE_WAGE + "CHF");
                currentPlayer.alterMoney(BASE_WAGE);
            } else {
                new InformationalWindow("Shopping?", "Thank you for shopping with us!");
                currentPlayer.alterMoney(BASE_WAGE * -1);
                getOwner().alterMoney(BASE_WAGE);
            }
        } else {
            if (currentPlayer.isWorking()) {
                QuestionWindow questionWindow = new QuestionWindow("Job Application " + getFieldName() + " (" + BASE_WAGE + "CHF)",
                        "You already have a job!\nWould you like to quit it?");
                if (questionWindow.getAnswer()) {
                    quitWork(currentPlayer);
                }
            } else {
                QuestionWindow questionWindow = new QuestionWindow("Job Application " + getFieldName() + " (" + BASE_WAGE + "CHF)",
                        "Would you like to start working here?");
                if (questionWindow.getAnswer()) {
                    setOwner(currentPlayer);
                    currentPlayer.setWorking(true);
                }
            }
        }
    }
}