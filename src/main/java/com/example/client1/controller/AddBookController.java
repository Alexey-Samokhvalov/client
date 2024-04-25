package com.example.client1.controller;

import com.example.client1.MainApplication;
import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.BookEntity;
import com.example.client1.entity.GenreEntity;
import com.example.client1.entity.PublisherEntity;
import com.example.client1.service.AuthorService;
import com.example.client1.service.BookService;
import com.example.client1.service.GenreService;
import com.example.client1.service.PublisherService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;
import java.util.Optional;

public class AddBookController {

    private final BookService service = new BookService();
    private boolean addFlag = true;
    private final AuthorService authorService = new AuthorService();
    private final GenreService genreService = new GenreService();
    private final PublisherService publisherService = new PublisherService();


    @FXML
    private ComboBox<AuthorEntity> ComboBoxAuthor;

    @FXML
    private ComboBox<PublisherEntity> ComboBoxPublisher;

    @FXML
    private ComboBox<GenreEntity> ComboBoxGenre;

    @FXML
    private TextField textTitle;

    @FXML
    private TextField textYear;

    @FXML
    private void initialize() {
        authorService.getAll();
        publisherService.getAll();
        genreService.getAll();
        ComboBoxAuthor.setItems(authorService.getData());
        ComboBoxGenre.setItems(genreService.getData());
        ComboBoxPublisher.setItems(publisherService.getData());
    }

    @Setter
    @Getter
    private Optional<BookEntity> book;

    public void start() {
        if (book.isPresent()) {
            textTitle.setText(book.get().getTitle());
            textYear.setText(book.get().getYear());
            ComboBoxAuthor.setValue(book.get().getAuthor());
            ComboBoxGenre.setValue(book.get().getGenre());
            ComboBoxPublisher.setValue(book.get().getPublisher());
        }
    }

    @FXML
    void addAction(ActionEvent event) {
        BookEntity temp = BookEntity.builder()
                .title(textTitle.getText())
                .year(textYear.getText())
                .genre(ComboBoxGenre.getSelectionModel().getSelectedItem())
                .publisher(ComboBoxPublisher.getSelectionModel().getSelectedItem())
                .author(ComboBoxAuthor.getSelectionModel().getSelectedItem())
                .build();
        if (book.isEmpty()) {
            book = Optional.of(temp);
        }else {
            temp.setId(book.get().getId());
            book = Optional.of(temp);
        }
    }

    @FXML
    void cancelAction(ActionEvent event) {
        addFlag = true;
    }
}


