package com.example.client1.controller;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.CityEntity;
import com.example.client1.entity.GenreEntity;
import com.example.client1.service.GenreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AddGenreController {

        private final GenreService service = new GenreService();
        private boolean addFlag = true;
        @FXML
        private ListView<GenreEntity> dataList;

        @FXML
        private void initialize() {

                service.getAll();
                dataList.setItems(service.getData());
        }
        @FXML
        private TextField textTitle;

        @FXML
        void addAction(ActionEvent event) {
                GenreEntity genre = new GenreEntity();
                genre.setTitle(textTitle.getText());
                if (addFlag) {
                        service.add(genre);
                } else {
                        genre.setId(getSelectionElement().getId());
                        service.update(genre, getSelectionElement());
                }
                textTitle.clear();
        }

        private GenreEntity getSelectionElement() {
                GenreEntity temp = dataList.getSelectionModel().getSelectedItem();
                return temp;
        }

        @FXML
        void cancelAction(ActionEvent event) {
                addFlag = true;
        }
        @FXML
        void onMouseClickedToList(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                        if (event.getClickCount() == 2) {
                                addFlag = false;
                                GenreEntity temp = getSelectionElement();
                                textTitle.setText(temp.getTitle());
                        }
                }
        }

        @FXML
        void deleteAction(ActionEvent event) {
                GenreEntity selectedIndex = dataList.getSelectionModel().getSelectedItem();
                if (selectedIndex != null) {
                        service.delete(selectedIndex);
                } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Нет выбора");
                        alert.setHeaderText("Никто не выбран :(");
                        alert.setContentText("Пожалуйста, выберите жанр в таблице");
                        alert.showAndWait();
                }
        }

    }

