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
public class mainWindowView extends Application {
    /**
     * Explicitly declared controller class.
     */
    mainWindowController controller = new mainWindowController();
    Parent root;

    public static void main(String[] args){
        Application.launch();
    }

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
}
