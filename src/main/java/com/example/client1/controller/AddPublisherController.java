package com.example.client1.controller;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.CityEntity;
import com.example.client1.entity.PublisherEntity;
import com.example.client1.service.CityService;
import com.example.client1.service.PublisherService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AddPublisherController {

        private final CityService cityService = new CityService();
        private final PublisherService service = new PublisherService();
        private boolean addFlag = true;

        @FXML
        private void initialize() {
                service.getAll();
                cityService.getAll();
                dataList.setItems(service.getData());
                comboBox.setItems(cityService.getData());
        }
        @FXML
        private ComboBox<CityEntity> comboBox;

        @FXML
        private ListView<PublisherEntity> dataList;

        @FXML
        private TextField textTitle;

        @FXML
        void addAction(ActionEvent event) {
PublisherEntity publisher = new PublisherEntity();
publisher.setTitle(textTitle.getText());
publisher.setCity(comboBox.getSelectionModel().getSelectedItem());
if (addFlag) {
        service.add(publisher);
}else {
        publisher.setId(getSelectionElement().getId());
        service.update(publisher, getSelectionElement());
}
textTitle.clear();
        }

        private PublisherEntity getSelectionElement() {
                PublisherEntity temp = dataList.getSelectionModel().getSelectedItem();
                return temp;
        }

        @FXML
        void cancelAction(ActionEvent event) {
addFlag = true;
        }

        @FXML
        void deleteAction(ActionEvent event) {
                PublisherEntity selectedIndex = dataList.getSelectionModel().getSelectedItem();
                if (selectedIndex != null) {
                        service.delete(selectedIndex);
                } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Нет выбора");
                        alert.setHeaderText("Никто не выбран :(");
                        alert.setContentText("Пожалуйста, выберите издательство в таблице");
                        alert.showAndWait();
                }
        }

        @FXML
        void clickedToList(MouseEvent event) {
if (event.getButton().equals(MouseButton.PRIMARY)) {
        if(event.getClickCount() == 2) {
                addFlag = false;
                PublisherEntity temp = getSelectionElement();
                textTitle.setText(temp.getTitle());
                comboBox.getSelectionModel().select(temp.getCity());
        }
}
        }
       @FXML
        void onMouseClickedToList(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                        if (event.getClickCount() == 2) {
                                addFlag = false;
                                PublisherEntity temp = getSelectionElement();
                                textTitle.setText(temp.getTitle());
                                comboBox.getSelectionModel().select((temp.getCity()));
                        }
                }
        }
    }

