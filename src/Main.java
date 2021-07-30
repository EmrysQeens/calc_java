import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        Parent ui = FXMLLoader.load(getClass().getResource("ui.fxml"));
        Scene scene = new Scene(ui);
        scene.getStylesheets().add(getClass().getResource("ui.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResource("app_icon.png").toExternalForm()));
        stage.setTitle("Calculator");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
