package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.City;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByAreaController {
    @FXML
    private TextArea groupByTextArea;

    public void showGroupByArea(LinkedHashSet<City> cities) {
        Map<Double, Long> grouped = cities.stream()
                .collect(Collectors.groupingBy(
                        City::getArea, Collectors.counting()
                ));

        StringBuilder sb = new StringBuilder();
        grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    sb.append("Площадь: ").append(entry.getKey())
                            .append(" — Количество городов: ").append(entry.getValue())
                            .append("\n");
                });

        groupByTextArea.setText(sb.toString());
    }
}
