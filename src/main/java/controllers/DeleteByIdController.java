package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import managers.CollectionManager;
import model.City;

import java.util.Optional;

public class DeleteByIdController {
    @FXML
    private TextField deleteIDTextField;
    @FXML private Label errorLabel;

    private CollectionManager collectionManager;
    private String currentUser;

    public void setCollectionManager(CollectionManager manager) {
        this.collectionManager = manager;
    }

    public void setCurrentUser(String user) {
        this.currentUser = user;
    }

    @FXML
    private void onDeleteClicked() {
        try {
            errorLabel.setText("");
            String input = deleteIDTextField.getText().trim();

            if (input.isEmpty()) {
                errorLabel.setText("Введите ID.");
                return;
            }

            int id = Integer.parseInt(input);

            Optional<City> cityOpt = collectionManager.getCities().stream()
                    .filter(c -> c.getId() == id)
                    .findFirst();

            if (cityOpt.isEmpty()) {
                errorLabel.setText("Город с таким ID не найден.");
                return;
            }

            City city = cityOpt.get();
            if (!city.getOwner().equals(currentUser)) {
                errorLabel.setText("Вы можете удалять только свои элементы.");
                return;
            }

            boolean removed = collectionManager.removeById(id);
            if (removed) {
                closeWindow();
            } else {
                errorLabel.setText("Не удалось удалить.");
            }

        } catch (NumberFormatException e) {
            errorLabel.setText("ID должен быть числом.");
        }
    }

    @FXML
    private void onCancelClicked() {
        closeWindow();
    }

    private void closeWindow() {
        ((Stage) deleteIDTextField.getScene().getWindow()).close();
    }
}
