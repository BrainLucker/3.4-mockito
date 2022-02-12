package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Movie;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// Тесты на методы без логики
public class AfishaManagerTest {
    private Movie first = new Movie(1, "Фильм_1", "боевик", "url1", false);
    private Movie second = new Movie(2, "Фильм_2", "боевик", "url2", false);
    private Movie third = new Movie(3, "Фильм_3", "боевик", "url3", false);
    private Movie[] movies = new Movie[]{first, second, third};

    private AfishaRepository repository = new AfishaRepository();
    private AfishaManager manager = new AfishaManager(repository);

    @Test
    public void shouldAddMovie() {
        manager.addMovie(first);

        Movie[] actual = manager.showAllMovies();
        Movie[] expected = new Movie[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowNoMoviesIfEmpty() {
        Movie[] actual = manager.showAllMovies();
        Movie[] expected = new Movie[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowAllMovies() {
        repository = new AfishaRepository(movies);
        manager = new AfishaManager(repository);

        Movie[] actual = manager.showAllMovies();
        Movie[] expected = new Movie[]{first, second, third};
        assertArrayEquals(expected, actual);
    }
}