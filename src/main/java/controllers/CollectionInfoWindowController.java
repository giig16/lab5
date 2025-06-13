package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import managers.CollectionManager;
import managers.DBManager;

import java.time.format.DateTimeFormatter;

public class CollectionInfoWindowController {
    @FXML
    private TextArea collectionInfoTextArea;

    @FXML
    public void initialize() {
        DBManager dbManager = new DBManager();
        CollectionManager collectionManager = new CollectionManager(dbManager);

        String info = String.format("""
                Информация о коллекции:
                
                • Тип коллекции: %s
                • Количество элементов: %d
                • Дата инициализации: %s
                """,
        "LinkedHashSet",
        collectionManager.getCities().size(),
                collectionManager.getInitTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        collectionInfoTextArea.setText(info);

    }
}
