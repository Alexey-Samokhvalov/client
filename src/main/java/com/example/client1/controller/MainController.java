package com.example.client1.controller;

import com.example.client1.entity.BookEntity;
import com.example.client1.service.BookService;
import com.example.client1.service.HttpService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

           BookService service = new BookService();

         @FXML
           private void initialize(){
                   //получаем все книги с сервера
                   service.getAll();
                   //связываем поля таблицы со столбцами
                   columnTitle.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
                   columnAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
                   columnPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
                   columnGenre.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("genre"));
                   columnNumber.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
                   bookTable.setItems(service.getData());
           }
        @FXML
        private TableView<BookEntity> bookTable;


        @FXML
        private TableColumn<BookEntity, String> columnAuthor;

        @FXML
        private TableColumn<BookEntity, String> columnGenre;

        @FXML
        private TableColumn<BookEntity, String> columnNumber;

        @FXML
        private TableColumn<BookEntity, String> columnPublisher;

        @FXML
        private TableColumn<BookEntity, String> columnTitle;

        @FXML

        void addBookAction(ActionEvent event) {

        }

        @FXML
        void addOrChangeAuthorAction(ActionEvent event) {

        }

        @FXML
        void addOrChangeCityAction(ActionEvent event) {

        }

        @FXML
        void addOrChangeGenreAction(ActionEvent event) {

        }

        @FXML
        void addOrChangePublisherAction(ActionEvent event) {

        }

        @FXML
        void changeBookAction(ActionEvent event) {

        }

        @FXML
        void closeAction(ActionEvent event) {

        }

        @FXML
        void deleteAuthorAction(ActionEvent event) {

        }

        @FXML
        void deleteBookAction(ActionEvent event) {
                HttpService service = new HttpService();
                System.out.println(service.get("http://localhost:28245/api/v1/book/all"));
        }


        @FXML
        void deleteCityAction(ActionEvent event) {

        }

        @FXML
        void deleteGenreAction(ActionEvent event) {

        }

        @FXML
        void deletePublisherAction(ActionEvent event) {

        }


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}
