package ru.netology.manager;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.domain.Movie;
import ru.netology.repository.AfishaRepository;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AfishaManager {
    private AfishaRepository repository;
    private int limit = 10;

    // Конструктор с лимитом по-умолчанию
    public AfishaManager(AfishaRepository repository) {
        this.repository = repository;
    }

    public void addMovie(Movie movie) {
        repository.save(movie);
    }

    public Movie[] showAllMovies() {
        return repository.findAll();
    }

    public Movie[] showLastLimitedNumberOfMovies() {
        Movie[] movies = showAllMovies();
        int resultLength = Math.min(limit, movies.length);
        Movie[] result = new Movie[resultLength];
        for (int i = 0; i < result.length; i++) {
            int index = movies.length - i - 1;
            result[i] = movies[index];
        }
        return result;
    }
}