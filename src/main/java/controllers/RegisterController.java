package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import managers.AuthorisationManager;
import managers.DBManager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;

import static managers.AuthorisationManager.hashPassword;



public class RegisterController  implements Initializable {

    @FXML
    private Hyperlink loginButton;

    @FXML
    private ImageView failureImage;

    @FXML
    private Label failureLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button signupButton;
    @FXML private MenuButton languageMenu;
    @FXML private MenuItem langRu;
    @FXML private MenuItem langEn;
    @FXML private MenuItem langIs;
    @FXML private MenuItem langBg;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        failureImage.setVisible(false);
        failureLabel.setVisible(false);

        signupButton.setText(resources.getString("register.button"));
        loginButton.setText(resources.getString("register.login"));
        failureLabel.setText(resources.getString("register.failure"));

        langRu.setOnAction(e -> switchLanguage("ru", "RU"));
        langEn.setOnAction(e -> switchLanguage("en", "CA"));
        langIs.setOnAction(e -> switchLanguage("is", "IS"));
        langBg.setOnAction(e -> switchLanguage("bg", "BG"));
    }




    @FXML
    public void signupButtonOnAction(ActionEvent event){
        String login = usernameTextField.getText().trim();
        String password = enterPasswordField.getText().trim();

        boolean success = AuthorisationManager.register(login, password);

        if (success) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.setTitle("Login");
                stage.show();

                Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            failureImage.setVisible(true);
            failureLabel.setVisible(true);
        }
    }


    @FXML
    private void switchToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchLanguage(String lang, String country) {
        try {
            Locale locale = new Locale(lang, country);
            ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Register.fxml"), bundle);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(bundle.getString("register.title"));
            stage.show();

            Stage current = (Stage) languageMenu.getScene().getWindow();
            current.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }





}

