package com.example.client1.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    private Long id;
    private String title;
    private AuthorEntity author;
    private PublisherEntity publisher;
    private GenreEntity genre;
    private String year;
}
