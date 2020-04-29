import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Informational window to inform players of events happening in the game.
 * (e.g. chance field, party field, zvv fee field)
 * To create an informational window, you don't need to instance it permanently. You can simply create one by using:
 * new InformationalWindow("The text you want to tell the user);
 */
public class InformationalWindow {

    /**
     * Spawns a new alert window.
     */
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    /**
     * Header and title text
     */
    private final String header = "Information";

    /**
     * Constructor of the informational window.
     *
     * @param message The message for the player.
     */
    public InformationalWindow(String message) {
        alert.setTitle(header);
        alert.setHeaderText(header);
        alert.setContentText(message);
        Optional<ButtonType> buttonType = alert.showAndWait();
    }
}
