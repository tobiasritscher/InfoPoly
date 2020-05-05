import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 * View class for main game window. Prepares the FXMLLoader and initializes the main GUI.
 *
 * @author corrooli
 */
public class MainWindowView extends Application {

    /**
     * Explicitly declared controller class.
     */
    MainWindowController controller = new MainWindowController();
    /**
     * Parent root node.
     */
    Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        String mainWindowPath = "src/main/resources/mainWindow.fxml";
        loader.setController(controller);
        FileInputStream mainWindowStream = new FileInputStream(mainWindowPath);

        root = (VBox) loader.load(mainWindowStream);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("InfoPoly");
        stage.setMinWidth(root.prefWidth(1200));
        stage.setMinHeight(root.prefHeight(800));
        stage.show();
    }

    public MainWindowController getController() {
        return controller;
    }

    public void startUI() {
        Application.launch();
    }
}
