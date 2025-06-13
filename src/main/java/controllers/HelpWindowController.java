package controllers;

import commands.Help;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HelpWindowController {
    @FXML
    private TextArea helpTextArea;

    @FXML

        public void initialize() {
            helpTextArea.setText("""
            Доступные команды:

            • Информация о коллекции — показывает информацию о текущей коллекции.
            • Обновить выбранный — редактирует выбранный объект в таблице.
            • Выполнить скрипт — запускает командный скрипт с локального файла.
            • Добавить — добавляет новый объект или по условию.
            • Удалить — удаляет выбранный объект или по условиям.
            • Среднее значение высоты — показывает среднее.
            • Уникальные значения — показывает уникальные значения.
            • Группировка по площади — группирует по полю area.
            """);
        }
    }

