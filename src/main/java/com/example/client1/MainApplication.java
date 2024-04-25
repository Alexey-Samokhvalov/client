package com.example.client1;

import com.example.client1.controller.AddBookController;
import com.example.client1.controller.MainController;
import com.example.client1.entity.BookEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainApplication extends Application {
private FXMLLoader fxmlLoader;
private static MainController mainController;


    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Библиотека");
        stage.setScene(scene);
        mainController = fxmlLoader.getController();
        stage.show();
    }

    public static void showBookDialog(Optional<BookEntity> book){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("book-add-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа с книгами");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            AddBookController controller = loader.getController();
            controller.setBook(book);
            controller.start();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            book = controller.getBook();
            System.out.println(book);
            mainController.setBook(book);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void showAuthorDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("add-author-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить/изменить автора");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void showCityDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("city-add-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить/изменить город");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void showGenreDialog() {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApplication.class.getResource("genre-add-view.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Добавить/изменить жанр");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            } catch (IOException e){
                e.printStackTrace();
            }
    }

    public static void showPublisherDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("publisher-add-view.fxml"));
AnchorPane page = (AnchorPane) loader.load();
Stage dialogStage = new Stage();
dialogStage.setTitle("Добавить/изменить издательство");
dialogStage.initModality(Modality.WINDOW_MODAL);
Scene scene = new Scene(page);
dialogStage.setScene(scene);
dialogStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void showDialog (String nameView, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource(nameView));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}