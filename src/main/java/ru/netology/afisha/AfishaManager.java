package ru.netology.afisha;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@Data
public class AfishaManager {
    private int limit = 10;
    private Movie[] movies = new Movie[0];

    public AfishaManager(int limit) {
        this.limit = limit;
    }

    public void addMovie(Movie movie) {
        int length = movies.length + 1;
        Movie[] tmp = Arrays.copyOf(movies, length);
        tmp[length - 1] = movie;
        movies = tmp;
    }

    public Movie[] showAllMovies() {
        Movie[] result = new Movie[movies.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = movies[i];
        }
        return result;
    }

    public Movie[] showLastLimitedNumberOfMovies() {
        int resultLength = (this.limit > movies.length) ? movies.length : this.limit;
        Movie[] result = new Movie[resultLength];
        for (int i = 0; i < result.length; i++) {
            int index = movies.length - i - 1;
            result[i] = movies[index];
        }
        return result;
    }
}