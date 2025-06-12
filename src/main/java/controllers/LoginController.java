package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import managers.DBManager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

import static managers.AuthorisationManager.hashPassword;

public class LoginController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File brandingFile =  new File("/Users/marknorkin/Desktop/java/test_SB/imgs/IMG_0361.JPG");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile =  new File("/Users/marknorkin/Desktop/java/test_SB/imgs/IMG_0362.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }


    public void loginButtonOnAction(ActionEvent event){
        loginMessageLabel.setText(" You try to login!");
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
        }else{
            loginMessageLabel.setText(" Enter your username and password!");
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
            loginMessageLabel.setText("Ошибка подключения к БД");
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
                    loginMessageLabel.setText(" You are logged in!");
                } else {
                    loginMessageLabel.setText(" Login is invalid! Try again!");
                }
            //}

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
