package ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameFields;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

/**
 * Represents {@link Config.FieldType#JOB}.
 */
public class JobGameField extends GameField {
    private static final int BASE_WAGE = 10;

    public JobGameField(int fieldId, String fieldName) {
        super(fieldId, fieldName);
    }

    public static int getBaseWage() {
        return BASE_WAGE;
    }

    private void askQuitWork(Player player) {
        QuestionWindow questionWindow = new QuestionWindow("Quit job", "Do you really want to quit this job?");
        if (questionWindow.getAnswer()) {
            quitWork(player);
        }
    }

    void quitWork(Player player) {
        resetOwner();
        player.removeJob();
    }

    /**
     * the action is called when a player enters the jobGameField
     * the the currentPlayer will have the possibility to get this job
     *
     * @param currentPlayer != null, the player who is standing on the field
     */
    @Override
    public void action(Player currentPlayer) {

        if (hasOwner()) {
            if (currentPlayer.equals(getOwner())) {
                currentPlayerIsOwner(currentPlayer);
            } else {
                otherPlayerIsOwner(currentPlayer);
            }

        } else {
            if (currentPlayer.isWorking()) {
                new InformationalWindow(String.format("Job application %S (%d.-CHF)", getFieldName(), BASE_WAGE),
                        String.format("You already have a job!%nCome back when you quit it!"));
            } else {
                currentPlayerHasNoJob(currentPlayer);
            }
        }
    }

    private void currentPlayerIsOwner(Player currentPlayer) {
        new InformationalWindow("Payday!", "You made an extra shift: +" + BASE_WAGE + "CHF");
        currentPlayer.alterMoney(BASE_WAGE);

        QuestionWindow questionWindow = new QuestionWindow(String.format("Job manager %s (%d.-CHF)",
                getFieldName(), BASE_WAGE), "Would you like to quite this job?");

        if (questionWindow.getAnswer()) {
            askQuitWork(currentPlayer);
        }
    }

    private void otherPlayerIsOwner(Player currentPlayer) {
        new InformationalWindow("You visited " + getFieldName(),
                "Thank you for shopping with us!");
        currentPlayer.alterMoney(BASE_WAGE * -1);
        getOwner().alterMoney(BASE_WAGE);
    }

    private void currentPlayerHasNoJob(Player currentPlayer) {
        QuestionWindow questionWindow = new QuestionWindow(String.format("Job application %s (%d.-CHF)",
                getFieldName(), BASE_WAGE), "Would you like to start working here?");

        if (questionWindow.getAnswer()) {
            setOwner(currentPlayer);
            currentPlayer.addJob(this);
        }
    }
}