import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class UIView extends Application {

    public static void main(String[] args){
        Application.launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        String mainWindowPath = "src/main/resources/infoPolyMainWindow.fxml";
        FileInputStream mainWindowStream = new FileInputStream(mainWindowPath);

        VBox root = (VBox) loader.load(mainWindowStream);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("InfoPoly");
        stage.show();
    }
}
