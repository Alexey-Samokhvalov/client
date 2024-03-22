package com.example.client1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CityEntity {
    private Long id;
    private String title;
    private List<PublisherEntity> publisher;
}

