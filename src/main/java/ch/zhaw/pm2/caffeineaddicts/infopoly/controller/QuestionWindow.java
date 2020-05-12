package ch.zhaw.pm2.caffeineaddicts.infopoly.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Question window to ask players yes/no concerning events happening in the game.
 * (e.g. asking to close app, start a new game, acquire a field, etc.)
 * <p>
 * To create a question window, you will need to instance it permanently. You can simply create one by using:
 * <p>
 * QuestionWindow thisQuestion = new QuestionWindow("The title","The text you want to tell the user);
 * <p>
 * And you can retrieve a boolean value of the user's input by:
 * <p>
 * thisQuestion.getAnswer;
 *
 * @author corrooli
 */
@SuppressWarnings("ALL")
public class QuestionWindow {
    /**
     * Spawns a new question window.
     */
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    /**
     * Stores the result retrieved from the GUI.
     */
    private final Optional<ButtonType> answer;
    private final ButtonType yesButton;

    /**
     * Constructor for the question window. Needs a header text (e.g. The name of the class/job) and a message text.
     *
     * @param headerText  String for the header text.
     * @param messageText String for the message text.
     */
    public QuestionWindow(String headerText, String messageText) {
        alert.setResizable(true);
        alert.setTitle("Question");
        alert.setHeaderText(headerText);
        alert.setContentText(messageText);

        yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);

        answer = alert.showAndWait();
    }

    /**
     * Returns a boolean answer.
     *
     * @return True if the user clicked yes, false if the user clicked no.
     */
    public boolean getAnswer() {
        return answer.get() == yesButton;
    }
}
