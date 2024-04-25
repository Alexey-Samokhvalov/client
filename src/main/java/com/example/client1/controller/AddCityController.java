package com.example.client1.controller;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.CityEntity;
import com.example.client1.service.CityService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AddCityController {

        private final CityService service = new CityService();
        private boolean addFlag = true;
        @FXML
        private ListView<CityEntity> dataList;

        @FXML
        private void initialize() {

                service.getAll();
                dataList.setItems(service.getData());
        }

        @FXML
        private TextField textTitle;

        @FXML
        void addAction(ActionEvent event) {
CityEntity city = new CityEntity();
city.setTitle(textTitle.getText());
                if (addFlag) {
service.add(city);
                } else {
                        city.setId(getSelectionElement().getId());
                        service.update(city, getSelectionElement());
                }
                textTitle.clear();
        }

        private CityEntity getSelectionElement() {
                CityEntity temp = dataList.getSelectionModel().getSelectedItem();
                return temp;
        }

        @FXML
        void cancelAction(ActionEvent event) {
                addFlag = true;
        }
        @FXML
        void onMouseClickDataList(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                        if (event.getClickCount() == 2) {
                                addFlag = false;
                                CityEntity temp = getSelectionElement();
                                textTitle.setText(temp.getTitle());
                        }
                }
        }

        @FXML
        void deleteAction(ActionEvent event) {
                CityEntity selectedIndex = dataList.getSelectionModel().getSelectedItem();
                if (selectedIndex != null) {
                        service.delete(selectedIndex);
                } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Нет выбора");
                        alert.setHeaderText("Никто не выбран :(");
                        alert.setContentText("Пожалуйста, выберите город в таблице");
                        alert.showAndWait();
                }
        }

    }
