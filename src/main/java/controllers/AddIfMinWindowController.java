package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import managers.CollectionManager;
import model.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AddIfMinWindowController {

    @FXML private TextField nameField, coordXField, coordYField, areaField, populationField, seaLevelField, governorAgeTextField;
    @FXML private DatePicker establishmentDatePicker;
    @FXML private MenuButton governmentMenuButton, standardOfLivingMenuButton;
    @FXML private MenuItem aristocracyMenuItem, statotocracyMenuItem, tellurocracyMenuItem;
    @FXML private MenuItem very_highMenuItem, highMenuItem, nightmareMenuItem;
    @FXML private Label errorLabel;
    @FXML private Label addIfMinErrorLabel;

    private CollectionManager collectionManager;
    private String currentUser;

    private StandardOfLiving selectedStandard;
    private Government selectedGovernment;

    public void setCollectionManager(CollectionManager manager) {
        this.collectionManager = manager;
    }

    public void setCurrentUser(String user) {
        this.currentUser = user;
    }

    @FXML
    public void initialize() {
        very_highMenuItem.setOnAction(e -> {
            selectedStandard = StandardOfLiving.VERY_HIGH;
            standardOfLivingMenuButton.setText("VERY_HIGH");
        });
        highMenuItem.setOnAction(e -> {
            selectedStandard = StandardOfLiving.HIGH;
            standardOfLivingMenuButton.setText("HIGH");
        });
        nightmareMenuItem.setOnAction(e -> {
            selectedStandard = StandardOfLiving.NIGHTMARE;
            standardOfLivingMenuButton.setText("NIGHTMARE");
        });

        aristocracyMenuItem.setOnAction(e -> {
            selectedGovernment = Government.ARISTOCRACY;
            governmentMenuButton.setText("ARISTOCRACY");
        });
        statotocracyMenuItem.setOnAction(e -> {
            selectedGovernment = Government.STRATOCRACY;
            governmentMenuButton.setText("STATOTOCRACY");
        });
        tellurocracyMenuItem.setOnAction(e -> {
            selectedGovernment = Government.TELLUROCRACY;
            governmentMenuButton.setText("TELLUROCRACY");
        });
    }

    @FXML
    private void onSubmit() {
        try {
            errorLabel.setText("");
            addIfMinErrorLabel.setText("");

            if (nameField.getText().isEmpty() || coordXField.getText().isEmpty() || coordYField.getText().isEmpty() ||
                    areaField.getText().isEmpty() || populationField.getText().isEmpty() ||
                    selectedStandard == null || selectedGovernment == null || governorAgeTextField.getText().isEmpty()) {
                throw new IllegalArgumentException("Заполните все обязательные поля.");
            }

            City city = new City();
            city.setName(nameField.getText());
            city.setCoordinates(new Coordinates(Long.parseLong(coordXField.getText()), Long.parseLong(coordYField.getText())));
            city.setArea(Double.parseDouble(areaField.getText()));
            city.setPopulation(Long.parseLong(populationField.getText()));
            city.setMetersAboveSeaLevel(seaLevelField.getText().isEmpty() ? null : Long.parseLong(seaLevelField.getText()));
            if (establishmentDatePicker.getValue() != null) {
                city.setEstablishmentDate(ZonedDateTime.of(establishmentDatePicker.getValue().atStartOfDay(), ZoneId.systemDefault()));
            }
            city.setGovernor(new Human(Long.parseLong(governorAgeTextField.getText())));
            city.setStandardOfLiving(selectedStandard);
            city.setGovernment(selectedGovernment);
            city.setOwner(currentUser);

            boolean isMin = collectionManager.getCities().stream()
                    .allMatch(existing -> city.compareTo(existing) < 0);

            if (isMin) {
                collectionManager.addToSet(city);
                ((Stage) nameField.getScene().getWindow()).close();
            } else {
                addIfMinErrorLabel.setText("Город не добавлен: не является минимальным.");
            }

        } catch (Exception e) {
            errorLabel.setText("Ошибка: проверьте корректность всех полей.");
        }
    }

    @FXML
    private void onCancel() {
        ((Stage) nameField.getScene().getWindow()).close();
    }
}
