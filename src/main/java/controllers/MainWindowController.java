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
import java.util.*;
import java.util.stream.Collectors;

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
    private MenuItem addIfMinMenuItem;
    @FXML
    private MenuItem removeSelectedMenuItem;
    @FXML
    private MenuItem deleteByIdMenuItem;
    @FXML
    private MenuItem removeIfMinMenuItem;
    @FXML
    private MenuItem removeIfMaxMenuItem;
    @FXML
    private MenuItem clearCollectionMenuItem;



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
        double avg = collectionManager.getCities().stream()
                .filter(c -> c.getMetersAboveSeaLevel() != null)
                .mapToDouble(City::getMetersAboveSeaLevel)
                .average()
                .orElse(Double.NaN);

        showInfo("Среднее значение абсолютной высоты: " + (Double.isNaN(avg) ? "нет данных" : avg));
    }

    @FXML
    private void onUniqueMetersClick() throws IOException {
        Set<Long> unique = collectionManager.getCities().stream()
                .map(City::getMetersAboveSeaLevel)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (unique.isEmpty()) {
            showInfo("Уникальные значения отсутствуют.");
        } else {
            String text = unique.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            showInfo("Уникальные значения абсолютной высоты:\n" + text);
        }
    }

    @FXML
    private void onGroupByAreaClick() throws IOException {
        Map<Double, Long> grouped = collectionManager.getCities().stream()
                .collect(Collectors.groupingBy(City::getArea, Collectors.counting()));

        if (grouped.isEmpty()) {
            showInfo("Группировка по площади: нет данных.");
        } else {
            String text = grouped.entrySet().stream()
                    .map(e -> "Площадь " + e.getKey() + ": " + e.getValue() + " городов")
                    .collect(Collectors.joining("\n"));
            showInfo("Группировка по площади:\n" + text);
        }
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

    @FXML
    private void onAddIfMinClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddIfMinWindow.fxml"));
            Parent root = loader.load();

            AddIfMinWindowController controller = loader.getController();
            controller.setCollectionManager(collectionManager);
            controller.setCurrentUser(currentUser);


            Stage stage = new Stage();
            stage.setTitle("Добавить, если меньше");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();


            refreshTable();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onRemoveSelected() {

        City selected = citiesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showInfo("Сначала выберите элемент в таблице.");
            return;
        }

        if (!selected.getOwner().equals(currentUser)) {
            showInfo("Вы можете удалять только свои элементы.");
            return;
        }

        boolean removed = collectionManager.removeById(selected.getId());

        if (removed) {
            refreshTable();
            showInfo("Элемент успешно удалён.");
        } else {
            showInfo("Не удалось удалить элемент.");
        }
    }
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void updateTable() {
        refreshTable();
    }
    @FXML
    private void onDeleteByIdMenuClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeletByIdWindow.fxml"));
            Parent root = loader.load();

            DeleteByIdController controller = loader.getController();
            controller.setCollectionManager(collectionManager);
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("Delete City By Id");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            refreshTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onRemoveIfMinClicked() {
        City selected = citiesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showInfo("Сначала выберите элемент в таблице.");
            return;
        }

        if (!selected.getOwner().equals(currentUser)) {
            showInfo("Вы можете удалять только свои элементы.");
            return;
        }

        boolean isMin = collectionManager.getCities().stream()
                .allMatch(c -> selected.compareTo(c) < 0 || c.getId() == selected.getId());

        if (isMin) {
            boolean removed = collectionManager.removeById(selected.getId());
            if (removed) {
                refreshTable();
                showInfo("Элемент удалён, так как был минимальным.");
            } else {
                showInfo("Не удалось удалить элемент.");
            }
        } else {
            showInfo("Элемент не является минимальным — удаление отменено.");
        }
    }
    @FXML
    private void onRemoveIfMaxClicked() {
        City selected = citiesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showInfo("Сначала выберите элемент в таблице.");
            return;
        }

        if (!selected.getOwner().equals(currentUser)) {
            showInfo("Вы можете удалять только свои элементы.");
            return;
        }

        boolean isMax = collectionManager.getCities().stream()
                .allMatch(c -> selected.compareTo(c) > 0 || c.getId() == selected.getId());

        if (isMax) {
            boolean removed = collectionManager.removeById(selected.getId());
            if (removed) {
                refreshTable();
                showInfo("Элемент удалён, так как был максимальным.");
            } else {
                showInfo("Не удалось удалить элемент.");
            }
        } else {
            showInfo("Элемент не является максимальным — удаление отменено.");
        }
    }
    @FXML
    private void onClearCollectionClicked() {

        List<City> toRemove = collectionManager.getCities().stream()
                .filter(c -> currentUser.equals(c.getOwner()))
                .collect(Collectors.toList());

        if (toRemove.isEmpty()) {
            showInfo("У вас нет элементов для удаления.");
            return;
        }



        for (City city : toRemove) {
            collectionManager.removeById(city.getId());
        }

        refreshTable();
        showInfo("Все ваши элементы успешно удалены.");
    }






}
