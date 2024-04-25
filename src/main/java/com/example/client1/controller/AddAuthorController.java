package com.example.client1.controller;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.BookEntity;
import com.example.client1.service.AuthorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class AddAuthorController {

        private final AuthorService service = new AuthorService();
private boolean addFlag = true;

        @FXML
        private ListView<AuthorEntity> dataList;
        @FXML
        private void initialize() {

            service.getAll();
            dataList.setItems(service.getData());
        }
        @FXML
        private TextField textLastname;

        @FXML
        private TextField textName;

        @FXML
        private TextField textSurname;

        @FXML
        void addAction(ActionEvent event) {
AuthorEntity author = new AuthorEntity();
author.setLastname(textLastname.getText());
author.setName(textName.getText());
author.setSurname(textSurname.getText());
if (addFlag) {

    service.add(author);
} else {

    author.setId(getSelectionElement().getId());
    service.update(author, getSelectionElement());
}
textLastname.clear();
textName.clear();
textSurname.clear();

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
                AuthorEntity temp = getSelectionElement();
                textLastname.setText(temp.getLastname());
                textName.setText(temp.getName());
                textSurname.setText(temp.getSurname());
            }
        }
    }

    private AuthorEntity getSelectionElement() {
            AuthorEntity temp = dataList.getSelectionModel().getSelectedItem();
            return temp;
    }
        @FXML
        void deleteAction(ActionEvent event) {
            AuthorEntity selectedIndex = dataList.getSelectionModel().getSelectedItem();
            if (selectedIndex != null) {
                service.delete(selectedIndex);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Нет выбора");
                alert.setHeaderText("Никто не выбран :(");
                alert.setContentText("Пожалуйста, выберите автора в таблице");
                alert.showAndWait();
            }
        }


    }

