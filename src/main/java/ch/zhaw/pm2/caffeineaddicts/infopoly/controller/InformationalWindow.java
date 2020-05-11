package ch.zhaw.pm2.caffeineaddicts.infopoly.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

import java.util.Optional;

/**
 * Informational window to inform players of events happening in the game.
 * (e.g. chance field, party field, zvv fee field)
 * To create an informational window, you don't need to instance it permanently. You can simply create one by using:
 * new InformationalWindow("The text you want to tell the user);
 *
 * @author corrooli
 */
public class InformationalWindow {

    /**
     * Constructor of the informational window. Spawns a new informational window.
     *
     * @param message The message for the player.
     */
    public InformationalWindow(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(true);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        String header = "Information";
        alert.setTitle(header);
        alert.setHeaderText(header);
        alert.setContentText(message);
        Optional<ButtonType> buttonType = alert.showAndWait();
    }
}
