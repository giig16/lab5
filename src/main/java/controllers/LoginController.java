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
import managers.*;

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

public class LoginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML private MenuButton languageMenu;
    @FXML private MenuItem langRu;
    @FXML private MenuItem langEn;
    @FXML private MenuItem langIs;
    @FXML private MenuItem langBg;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File brandingFile =  new File("/Users/marknorkin/Desktop/java/test_SB/imgs/IMG_0361.JPG");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile =  new File("/Users/marknorkin/Desktop/java/test_SB/imgs/IMG_0362.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());

        usernameLabel.setText(bundle.getString("login.username"));
        passwordLabel.setText(bundle.getString("login.password"));
        loginButton.setText(bundle.getString("login.button"));
        cancelButton.setText(bundle.getString("login.cancel"));
        //loginMessageLabel.setText(bundle.getString("login.fail"));


        langRu.setOnAction(e -> switchLanguage("ru", "RU"));
        langEn.setOnAction(e -> switchLanguage("en", "CA"));
        langIs.setOnAction(e -> switchLanguage("is", "IS"));
        langBg.setOnAction(e -> switchLanguage("bg", "BG"));
    }

    private void switchLanguage(String lang, String country) {
        try {
            Locale locale = new Locale(lang, country);
            Locale.setDefault(locale);
            ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", locale);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"), bundle);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(bundle.getString("login.title")); // <-- изменено на login.title
            stage.show();

            Stage current = (Stage) languageMenu.getScene().getWindow();
            current.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void loginButtonOnAction(ActionEvent event){
        loginMessageLabel.setText(" You try to login!");
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
        }else{
            ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());
            loginMessageLabel.setText(bundle.getString("login.prompt"));
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DBManager dbManager = new DBManager();
        Connection connectDB = dbManager.connect_to_db("lab5", "postgres", "12345");
        if (connectDB == null) {
            ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());
            loginMessageLabel.setText(bundle.getString("login.db_error"));
            return;
        }

        String verifyLogin = "SELECT * FROM users WHERE username = ? AND password = ?";
        try{
            PreparedStatement stmt = connectDB.prepareStatement(verifyLogin);
            stmt.setString(1, usernameTextField.getText());
            stmt.setString(2, hashPassword(enterPasswordField.getText()));
            ResultSet queryResult = stmt.executeQuery();
            //while(queryResult.next()){
                if (queryResult.next()) {
                    ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());
                    loginMessageLabel.setText(bundle.getString("login.start"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"), bundle);
                    Parent root = loader.load();

                    MainWindowController controller = loader.getController();
                    controller.setUsername(usernameTextField.getText());
                    controller.setCurrentUser(usernameTextField.getText());


                    CollectionManager collectionManager = new CollectionManager(dbManager);
                    CSVManager fileManager = new CSVManager("");
                    Invoker invoker = new Invoker(collectionManager,fileManager,dbManager,loginMessageLabel.getText());
                    controller.setInvoker(invoker);

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Main Window");
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.show();
                } else {
                    ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());
                    loginMessageLabel.setText(bundle.getString("login.fail"));
                }
            //}

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
