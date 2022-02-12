package ru.netology.repository;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import ru.netology.domain.Movie;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
public class AfishaRepository {
    private Movie[] movies = new Movie[0];

    public Movie[] findAll() {
        return this.movies;
    }

    public void save(Movie movie) {
        int length = movies.length + 1;
        Movie[] tmp = Arrays.copyOf(movies, length);
        tmp[length - 1] = movie;
        movies = tmp;
    }

    public Movie findById(int id) {
        Movie result = null;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].getId() == id) {
                result = movies[i];
            }
        }
        return result;
    }

    public void removeById(int id) {
        int length = movies.length - 1;
        Movie[] tmp = new Movie[length];
        int index = 0;
        for (Movie movie : movies) {
            if (movie.getId() != id) {
                if (index == length) return;
                tmp[index] = movie;
                index++;
            }
        }
        movies = tmp;
    }

    public void removeAll() {
        movies = new Movie[0];
    }
}