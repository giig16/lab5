package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.City;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueMetersController {
    @FXML
    private TextArea uniqueTextArea;

    public void showUniqueMeters(LinkedHashSet<City> cities) {

        Set<Long> uniqueValues = cities.stream()
                .map(City::getMetersAboveSeaLevel)
                .collect(Collectors.toSet());

        String result = uniqueValues.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));

        uniqueTextArea.setText("Уникальные значения абсолютной высоты: "+"\n"+result);
    }
}
