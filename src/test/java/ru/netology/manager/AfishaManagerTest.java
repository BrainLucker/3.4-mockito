package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Movie;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(MockitoExtension.class)
public class AfishaManagerTest {
    //    @Mock
    private AfishaRepository repository = new AfishaRepository();
    //    @InjectMocks
    private AfishaManager manager;
    private final Movie first = new Movie(1, "Фильм_1", "боевик", "url1", false);
    private final Movie second = new Movie(2, "Фильм_2", "боевик", "url2", false);
    private final Movie third = new Movie(3, "Фильм_3", "боевик", "url3", false);
    private final Movie fourth = new Movie(4, "Фильм_4", "боевик", "url4", false);
    private final Movie fifth = new Movie(5, "Фильм_5", "боевик", "url5", false);

    public void setUp(AfishaManager manager) {
        manager.addMovie(first);
        manager.addMovie(second);
        manager.addMovie(third);
        manager.addMovie(fourth);
        manager.addMovie(fifth);
    }

    @Test
    public void shouldAddMovie() {
        AfishaManager manager = new AfishaManager(repository);
        repository.save(first);
        Movie[] actual = manager.showAllMovies();
        Movie[] expected = new Movie[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowAllMovies() {
        AfishaManager manager = new AfishaManager(repository);
        this.setUp(manager);
        Movie[] actual = manager.showAllMovies();
        Movie[] expected = new Movie[]{first, second, third, fourth, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowNoMovies() {
        AfishaManager emptyManager = new AfishaManager(repository);
        Movie[] actual = emptyManager.showAllMovies();
        Movie[] expected = new Movie[0];
        assertArrayEquals(expected, actual);
    }

    @Test // Стандартный менеджер с лимитом в 10 фильмов
    public void shouldShowLastMoviesUnderLimit() {
//        Movie[] returned = new Movie[0];
        AfishaManager manager = new AfishaManager(repository);
        this.setUp(manager);
        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test // Кастомный менеджер с лимитом в 5 фильмов
    public void shouldShowLastMoviesEqualsLimit() {
        AfishaManager manager = new AfishaManager(repository, 5);
        this.setUp(manager);
        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test // Кастомный менеджер с лимитом в 3 фильма
    public void shouldShowLastMoviesOverLimit() {
        AfishaManager manager = new AfishaManager(repository, 3);
        this.setUp(manager);
        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third};
        assertArrayEquals(expected, actual);
    }
}