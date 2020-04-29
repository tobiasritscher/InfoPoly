import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 * View class for main game window. Prepares the FXMLLoader and initializes the main GUI.
 *
 * @author corrooli
 */
public class mainView extends Application {
    /**
     * Explicitly declared controller class.
     */
    mainWindowController controller = new mainWindowController();

    public static void main(String[] args){
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        String mainWindowPath = "src/main/resources/infoPolyMainWindow.fxml";
        loader.setController(controller);
        FileInputStream mainWindowStream = new FileInputStream(mainWindowPath);

        // Caution! VBox Cast appears redundant, but is actually needed.
        VBox root = (VBox) loader.load(mainWindowStream);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("InfoPoly");
        stage.setMinWidth(root.prefWidth(1200));
        stage.setMinHeight(root.prefHeight(800));
        stage.show();
    }
}
