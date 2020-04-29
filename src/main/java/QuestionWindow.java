import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Question window to ask players yes/no concerning events happening in the game.
 * (e.g. asking to close app, start a new game, acquire a field, etc. It's multi-purpose!)
 * To create an informational window, you will need to instance it permanently. You can simply create one by using:
 * InformationalWindow thisQuestion = new InformationalWindow("The title","The text you want to tell the user);
 * And you can retrieve a boolean value of the user's input by thisQuestion.getAnswer; - It's simple!
 */
public class QuestionWindow {

    private final Optional<ButtonType> result;
    private final ButtonType yesButton;
    private ButtonType noButton;

    /**
     * Constructor for the question window. Needs a header text (e.g. The name of the class/job) and a message text.
     *
     * @param headerText  String for the header text.
     * @param messageText String for the message text.
     */
    public QuestionWindow(String headerText, String messageText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Question");
        alert.setHeaderText(headerText);
        alert.setContentText(messageText);

        yesButton = new ButtonType("Yes");
        noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        result = alert.showAndWait();
    }

    /**
     * Returns a boolean answer.
     *
     * @return True if the user clicked yes, false if the user clicked no or closed the window.
     */
    public boolean getAnswer() {
        return result.get() == yesButton;
    }
}
