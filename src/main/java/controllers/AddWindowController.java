package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import managers.CollectionManager;
import model.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AddWindowController {

    @FXML private TextField nameField;
    @FXML private TextField coordXField;
    @FXML private TextField coordYField;
    @FXML private TextField areaField;
    @FXML private TextField populationField;
    @FXML private TextField seaLevelField;
    @FXML private TextField governorAgeTextField;

    @FXML private DatePicker establishmentDatePicker;

    @FXML private MenuButton governmentMenuButton;
    @FXML private MenuItem aristocracyMenuItem;
    @FXML private MenuItem statotocracyMenuItem;
    @FXML private MenuItem tellurocracyMenuItem;

    @FXML private MenuButton standardOfLivingMenuButton;
    @FXML private MenuItem very_highMenuItem;
    @FXML private MenuItem highMenuItem;
    @FXML private MenuItem nightmareMenuItem;

    @FXML private Button addButton;
    @FXML private Button cancelAddButton;

    @FXML private Label errorLabel;

    private CollectionManager collectionManager;
    private MainWindowController mainWindowController;

    private StandardOfLiving selectedStandard;
    private Government selectedGovernment;
    private City createdCity;

    public void setCollectionManager(CollectionManager cm) {
        this.collectionManager = cm;
    }

    public void setMainWindowController(MainWindowController controller) {
        this.mainWindowController = controller;
    }

    public City getCreatedCity() {
        return createdCity;
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

            if (nameField.getText().isEmpty() || coordXField.getText().isEmpty() || coordYField.getText().isEmpty() ||
                    areaField.getText().isEmpty() || populationField.getText().isEmpty() ||
                    selectedStandard == null || selectedGovernment == null || governorAgeTextField.getText().isEmpty()) {
                throw new IllegalArgumentException("Заполните все обязательные поля.");
            }

            String name = nameField.getText();
            Long coordX = Long.parseLong(coordXField.getText());
            long coordY = Long.parseLong(coordYField.getText());
            double area = Double.parseDouble(areaField.getText());
            long population = Long.parseLong(populationField.getText());

            Long seaLevel = seaLevelField.getText().isEmpty() ? null : Long.parseLong(seaLevelField.getText());

            ZonedDateTime establishmentDate = null;
            if (establishmentDatePicker.getValue() != null) {
                establishmentDate = ZonedDateTime.of(establishmentDatePicker.getValue().atStartOfDay(), ZoneId.systemDefault());
            }

            Long governorAge = Long.parseLong(governorAgeTextField.getText());
            Coordinates coords = new Coordinates(coordX, coordY);
            Human governor = new Human(governorAge);

            City city = new City();
            city.setName(name);
            city.setCoordinates(coords);
            city.setArea(area);
            city.setPopulation(population);
            city.setMetersAboveSeaLevel(seaLevel);
            city.setEstablishmentDate(establishmentDate);
            city.setGovernment(selectedGovernment);
            city.setStandardOfLiving(selectedStandard);
            city.setGovernor(governor);
            city.setOwner(mainWindowController.getCurrentUser());

            createdCity = city;

            boolean success = collectionManager.addToSet(city);
            if (!success) {
                throw new RuntimeException("Ошибка при добавлении в коллекцию или базу данных.");
            }



            ((Stage) nameField.getScene().getWindow()).close();

        } catch (Exception e) {
            errorLabel.setText("Ошибка: проверьте корректность всех полей");
        }
    }

    @FXML
    private void onCancel() {
        ((Stage) nameField.getScene().getWindow()).close();
    }
}
