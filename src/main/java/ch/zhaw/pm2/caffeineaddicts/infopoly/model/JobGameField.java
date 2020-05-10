package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.InformationalWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.controller.QuestionWindow;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameField;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;

public class JobGameField extends GameField {
    private int baseWage;
    private GameField gameField;

    public JobGameField(int fieldId, Config.FieldType fieldType, String fieldName, int baseWage) {
        super(fieldId, fieldType, fieldName);
        this.baseWage = baseWage;
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
            if (currentPlayer.equals(gameField.getOwner())) {
                new InformationalWindow("You are already working here. You made an extra shift: +" + baseWage + "CHF");
                currentPlayer.alterMoney(baseWage);
            } else {
                new InformationalWindow("Thank you for shopping with us!");
                currentPlayer.alterMoney(baseWage * -1);
                gameField.getOwner().alterMoney(baseWage);
            }
        } else {
            if (currentPlayer.isWorking()) {
                QuestionWindow questionWindow = new QuestionWindow("Job Application " + gameField.getFieldName() + " (" + baseWage + "CHF)",
                        "You already have a job!\nWould you like to quit it?");
                if (questionWindow.getAnswer()) {
                    quitWork(currentPlayer);
                }
            } else {
                QuestionWindow questionWindow = new QuestionWindow("Job Application " + gameField.getFieldName() + " (" + baseWage + "CHF)",
                        "Would you like to start working here?");
                if (questionWindow.getAnswer()) {
                     gameField.setOwner(currentPlayer);
                    currentPlayer.setWorking(true);
                }
            }
        }
    }
}