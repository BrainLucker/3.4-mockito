package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Movie;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AfishaRepositoryTest {
    private final Movie first = new Movie(1, "Фильм_1", "боевик", "url1", false);
    private final Movie second = new Movie(2, "Фильм_2", "боевик", "url2", false);
    private final Movie third = new Movie(3, "Фильм_3", "боевик", "url3", false);
    private final Movie[] movies = new Movie[]{first, second, third};
    private AfishaRepository repository = new AfishaRepository(movies);

    @Test
    public void shouldFindAll() {
        Movie[] actual = repository.findAll();
        Movie[] expected = movies;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSave() {
        AfishaRepository repository = new AfishaRepository();
        repository.save(first);
        Movie[] actual = repository.findAll();
        Movie[] expected = new Movie[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {
        Movie actual = repository.findById(3);
        Movie expected = third;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnNullIfCantFindById() {
        Movie actual = repository.findById(4);
        Movie expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(3);
        Movie[] actual = repository.findAll();
        Movie[] expected = new Movie[]{first, second};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveIfCantFindById() {
        repository.removeById(4);
        Movie[] actual = repository.findAll();
        Movie[] expected = movies;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAll() {
        repository.removeAll();
        Movie[] actual = repository.findAll();
        Movie[] expected = new Movie[0];
        assertArrayEquals(expected, actual);
    }
}