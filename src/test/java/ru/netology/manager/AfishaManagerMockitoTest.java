package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.netology.domain.Movie;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AfishaManagerMockitoTest {
    @Mock
    private AfishaRepository repository;
    @InjectMocks
    private AfishaManager manager;
    private Movie first = new Movie(1, "Фильм_1", "боевик", "url1", false);
    private Movie second = new Movie(2, "Фильм_2", "боевик", "url2", false);
    private Movie third = new Movie(3, "Фильм_3", "боевик", "url3", false);
    private Movie fourth = new Movie(4, "Фильм_4", "боевик", "url4", false);
    private Movie fifth = new Movie(5, "Фильм_5", "боевик", "url5", false);
    private Movie[] returned = new Movie[]{first, second, third, fourth, fifth};


    @Test // Стандартный менеджер с лимитом в 10 фильмов
    public void shouldShowLastMoviesUnderLimit() {
        doReturn(returned).when(repository).findAll();

        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test // Кастомный менеджер с лимитом в 5 фильмов
    public void shouldShowLastMoviesEqualsLimit() {
        manager.setLimit(5);
        doReturn(returned).when(repository).findAll();

        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test // Кастомный менеджер с лимитом в 3 фильма
    public void shouldShowLastMoviesOverLimit() {
        manager.setLimit(3);
        doReturn(returned).when(repository).findAll();

        Movie[] actual = manager.showLastLimitedNumberOfMovies();
        Movie[] expected = new Movie[]{fifth, fourth, third};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }
}