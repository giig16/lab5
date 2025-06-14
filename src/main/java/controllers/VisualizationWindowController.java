package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.City;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VisualizationWindowController {
    @FXML
    private Canvas visualizationCanvas;

    private Collection<City> cityCollection;

    private final Map<City, Circle> cityShapes = new HashMap<>();


    public void initData(Collection<City> cities) {
        this.cityCollection = cities;
        drawCities();
        setupMouseHandler();
    }

    private void drawCities() {
        GraphicsContext gc = visualizationCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, visualizationCanvas.getWidth(), visualizationCanvas.getHeight());

        Map<String, Color> userColors = new HashMap<>();
        cityShapes.clear();

        for (City city : cityCollection) {
            double canvasWidth = visualizationCanvas.getWidth();
            double canvasHeight = visualizationCanvas.getHeight();


            double x = (city.getCoordinates().getX() % canvasWidth + canvasWidth) % canvasWidth;
            double y = (city.getCoordinates().getY() % canvasHeight + canvasHeight) % canvasHeight;

            String owner = city.getOwner();
            Color color = userColors.computeIfAbsent(owner, k -> Color.color(Math.random(), Math.random(), Math.random()));

            gc.setFill(color);
            gc.fillOval(x, y, 15, 15);


            cityShapes.put(city, new Circle(x + 7.5, y + 7.5, 7.5));
        }
    }

    private void setupMouseHandler() {
        visualizationCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            double x = event.getX();
            double y = event.getY();

            for (Map.Entry<City, Circle> entry : cityShapes.entrySet()) {
                Circle circle = entry.getValue();
                if (circle.contains(x, y)) {
                    City city = entry.getKey();
                    showCityTooltip(city, x, y);
                    break;
                }
            }
        });
    }


    private void showCityTooltip(City city, double x, double y) {
        Tooltip tooltip = new Tooltip("Город: " + city.getName() +
                "\\nВладелец: " + city.getOwner() +
                "\\nКоординаты: " + city.getCoordinates().getX() + ", " + city.getCoordinates().getY());
        tooltip.setAutoHide(true);
        tooltip.show(visualizationCanvas.getScene().getWindow(),
                x + visualizationCanvas.getLayoutX(),
                y + visualizationCanvas.getLayoutY());
    }
    }

