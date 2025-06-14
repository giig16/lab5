package main1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Locale locale = new Locale("ru", "RU");
        ResourceBundle bundle = ResourceBundle.getBundle(
                "MessagesBundle",
                new Locale("ru", "RU"),
                ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_DEFAULT)
        );
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Register.fxml"), bundle);
        Parent root = fxmlLoader.load();
        stage.setTitle(bundle.getString("register.title"));
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
