package ru.netology.afisha;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class Movie {
    private int id;
    private String name;
    private String genre;
    private String coverUrl;
    private boolean isPremiere;
}