package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#JOB}.
 */
public class JobGameField extends GameField {
    public static final int BASE_WAGE = 10;

    public JobGameField(int fieldId, Config.FieldType fieldType, String fieldName) {
        super(fieldId, fieldType, fieldName);
    }

    private void quitWork(Player player) {
        QuestionWindow questionWindow = new QuestionWindow("Quit job", "Do you really want to quit this job?");
        if (questionWindow.getAnswer()) {
            resetOwner();
            player.removeJob();
        }
    }

    @Override
    public void action(Player currentPlayer) {

        if (hasOwner()) {
            if (currentPlayer.equals(getOwner())) {
                new InformationalWindow("Payday!", "You made an extra shift: +" + BASE_WAGE + "CHF");
                currentPlayer.alterMoney(BASE_WAGE);
                QuestionWindow questionWindow = new QuestionWindow(String.format("Job manager %s (%d.-CHF)", getFieldName(), BASE_WAGE), "Would you like to quite this job?");
                if (questionWindow.getAnswer()) {
                    quitWork(currentPlayer);
                }
            } else {
                new InformationalWindow("Shopping?", "Thank you for shopping with us!");
                currentPlayer.alterMoney(BASE_WAGE * -1);
                getOwner().alterMoney(BASE_WAGE);
            }
        } else {
            if (currentPlayer.isWorking()) {
                new InformationalWindow(String.format("Job Application %S (%d.-CHF)", getFieldName(), BASE_WAGE),
                        String.format("You already have a job!%nCome back when you quit it!"));
            } else {
                QuestionWindow questionWindow = new QuestionWindow(String.format("Job manager %s (%d.-CHF)", getFieldName(), BASE_WAGE),
                        "Would you like to start working here?");
                if (questionWindow.getAnswer()) {
                    setOwner(currentPlayer);
                    currentPlayer.addJob(this);
                }
            }
        }
    }
}