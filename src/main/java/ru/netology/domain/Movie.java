package ru.netology.domain;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Movie {
    private int id;
    private String name;
    private String genre;
    private String coverUrl;
    private boolean isPremiere;
}