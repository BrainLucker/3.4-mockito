package ru.netology.afisha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AfishaManagerTest {
    private Movie first = new Movie(1, "Фильм_1", "боевик", "url1", false);
    private Movie second = new Movie(2, "Фильм_2", "боевик", "url2", false);
    private Movie third = new Movie(3, "Фильм_3", "боевик", "url3", false);
    private Movie fourth = new Movie(4, "Фильм_4", "боевик", "url4", false);
    private Movie fifth = new Movie(5, "Фильм_5", "боевик", "url5", false);

    public void setUp(AfishaManager manager) {
        manager.addMovie(first);
        manager.addMovie(second);
        manager.addMovie(third);
        manager.addMovie(fourth);
        manager.addMovie(fifth);
    }

    @Test
    void shouldAddMovie() {
        AfishaManager emptyManager = new AfishaManager();
        emptyManager.addMovie(first);
        Movie[] actual = emptyManager.getMovies();
        Movie[] expected = new Movie[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowAllMovies() {
        AfishaManager manager = new AfishaManager();
        this.setUp(manager);
        Movie[] actual = manager.showAllMovies();
        Movie[] expected = new Movie[]{first, second, third, fourth, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowNoMovies() {
        AfishaManager emptyManager = new AfishaManager();
        Movie[] actual = emptyManager.showAllMovies();
        Movie[] expected = new Movie[0];
        assertArrayEquals(expected, actual);
    }

    @Test // Стандартный менеджер с лимитом в 10 фильмов
    void shouldShowLastMoviesUnderLimit() {
        AfishaManager manager = new AfishaManager();
        this.setUp(manager);
        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test // Кастомный менеджер с лимитом в 5 фильмов
    void shouldShowLastMoviesEqualsLimit() {
        AfishaManager manager = new AfishaManager(5);
        this.setUp(manager);
        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test // Кастомный менеджер с лимитом в 3 фильма
    void shouldShowLastMoviesOverLimit() {
        AfishaManager manager = new AfishaManager(3);
        this.setUp(manager);
        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third};
        assertArrayEquals(expected, actual);
    }
}