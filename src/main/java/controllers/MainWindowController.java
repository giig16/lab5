package controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import managers.CollectionManager;
import managers.DBManager;
import model.City;
import controllers.AddWindowController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class MainWindowController {
    @FXML
    private Label usernameLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<City> citiesTable;
    @FXML
    private TableColumn<City, Integer> idColumn;
    @FXML
    private TableColumn<City, String> nameColumn;
    @FXML
    private TableColumn<City, Long> xCoordColumn;
    @FXML
    private TableColumn<City, Double> yCoordColumn;
    @FXML
    private TableColumn<City, String> creationDateColumn;
    @FXML
    private TableColumn<City, Double> areaColumn;
    @FXML
    private TableColumn<City, Long> populationColumn;
    @FXML
    private TableColumn<City, Double> metersAboveSeaLevelColumn;
    @FXML
    private TableColumn<City, String> establishmentDateColumn;
    @FXML
    private TableColumn<City, String> standardOfLivingColumn;
    @FXML
    private TableColumn<City, String> governmentColumn;
    @FXML
    private TableColumn<City, Long> governorColumn;
    @FXML
    private TableColumn<City, String> ownerColumn;
    @FXML
    private MenuItem helpMenuItem;
    @FXML



    DBManager dbManager = new DBManager();
    CollectionManager collectionManager = new CollectionManager(dbManager);
    public void setUsername(String username) {
        usernameLabel.setText(username);
    }




    public void setCollectionManager(CollectionManager collectionManager) {this.collectionManager = collectionManager;}
    private String currentUser;

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }



    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xCoordColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCoordinates().getX())
        );
        yCoordColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCoordinates().getY())
        );
        creationDateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getCreationDate() != null
                                ? dateFormatter.format(cellData.getValue().getCreationDate().toLocalDate())
                                : "N/A"
                )
        );
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));
        metersAboveSeaLevelColumn.setCellValueFactory(new PropertyValueFactory<>("metersAboveSeaLevel"));
        establishmentDateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getEstablishmentDate() != null
                                ? dateFormatter.format(cellData.getValue().getEstablishmentDate().toLocalDate())
                                : "N/A"
                )
        );
        standardOfLivingColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStandardOfLiving().toString())
        );
        governmentColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getGovernment().toString())
        );
        governorColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getGovernor().getAge())
        );
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));

        DBManager dbManager = new DBManager();
        CollectionManager collectionManager = new CollectionManager(dbManager);

        ObservableList<City> observableCities = FXCollections.observableArrayList();
        observableCities.addAll(collectionManager.getCities());

        citiesTable.setItems(observableCities);
    }
    @FXML
    private void onHelpClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HelpWindow.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Help");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void onCollectionInfoClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CollectionInfoWindow.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Info");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAverageMetersClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AverageMetersWindow.fxml"));
        Parent root = loader.load();

        AverageMetersController controller = loader.getController();
        controller.showAverageMeters();

        Stage stage = new Stage();
        stage.setTitle("Average meters above sea level");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void onUniqueMetersClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UniqueMeters.fxml"));
        Parent root = loader.load();

        UniqueMetersController controller = loader.getController();
        controller.showUniqueMeters(collectionManager.getCities());

        Stage stage = new Stage();
        stage.setTitle("Unique meters above sea level");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onGroupByAreaClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GroupByArea.fxml"));
        Parent root = loader.load();

        GroupByAreaController controller = loader.getController();
        controller.showGroupByArea(collectionManager.getCities());

        Stage stage = new Stage();
        stage.setTitle("Группировка по площади");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onSortAreaAscClick() {

        FXCollections.sort(citiesTable.getItems());
    }

    @FXML
    private void onSortAreaDescClick() {

        FXCollections.sort(citiesTable.getItems(),Comparator.reverseOrder());
    }

    @FXML
    private void onAddCityClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCommandWindow.fxml"));
            Parent root = loader.load();

            AddWindowController controller = loader.getController();
            controller.setCollectionManager(collectionManager);
            controller.setMainWindowController(this);

            Stage stage = new Stage();
            stage.setTitle("Add City");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            City createdCity = controller.getCreatedCity();
            if (createdCity != null) {
                citiesTable.getItems().add(createdCity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void refreshTable() {
        citiesTable.getItems().clear();
        citiesTable.getItems().addAll(collectionManager.getCities());
    }




}
