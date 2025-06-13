package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import managers.CollectionManager;
import managers.DBManager;
import model.City;

import java.util.ArrayList;
import java.util.List;

public class AverageMetersController {
    @FXML
    private Label avgMetersAboveSeaLevelLabel;

    public void showAverageMeters() {
        DBManager dbManager = new DBManager();
        CollectionManager collectionManager = new CollectionManager(dbManager);
        double avg = collectionManager.getCities().stream()
                .mapToDouble(City::getMetersAboveSeaLevel)
                .average()
                .orElse(0.0);

        avgMetersAboveSeaLevelLabel.setText("Среднее значение абсолютной высоты: " + avg);
    }
}
