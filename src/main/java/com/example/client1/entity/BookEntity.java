package com.example.client1.entity;

import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Section;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {

    private Long id;
    private String title;
    private AuthorEntity author;
    private PublisherEntity publisher;
    private GenreEntity genre;
    private String year;

    @Override
    public String toString() {
        return title;
    }
}
