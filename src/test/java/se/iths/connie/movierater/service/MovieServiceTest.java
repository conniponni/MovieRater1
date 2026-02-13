package se.iths.connie.movierater.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.iths.connie.movierater.exception.MovieNotFoundException;
import se.iths.connie.movierater.model.Movie;
import se.iths.connie.movierater.repository.MovieRepository;
import se.iths.connie.movierater.validator.MovieValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    private MovieRepository movieRepository;
    private MovieValidator movieValidator;
    private MovieService movieService;
    private Movie movie;

    @BeforeEach
    void setup() {
        movieRepository = Mockito.mock(MovieRepository.class);
        movieValidator = Mockito.mock(MovieValidator.class);
        movieService = new MovieService(movieRepository, movieValidator);
        movie = new Movie("Test Movie", "Action", 2020, 120, 8.5);
    }

    @Test
    void getAllMoviesReturnsList() {
        when(movieRepository.findAll()).thenReturn(List.of(movie));
        List<Movie> result = movieService.getAllMovies();
        assertEquals(1, result.size());
        verify(movieRepository).findAll();
    }

    @Test
    void getMovieByIdReturnsMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        Movie result = movieService.getMovieById(1L);
        assertEquals("Test Movie", result.getTitle());
    }

    @Test
    void getMovieByIdThrowsExceptionIfNotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(MovieNotFoundException.class,
                () -> movieService.getMovieById(1L));
    }

    @Test
    void createMovieSavesMovie() {
        when(movieRepository.save(movie)).thenReturn(movie);
        Movie result = movieService.createMovie(movie);
        verify(movieValidator).validateMovie(movie);
        verify(movieRepository).save(movie);
        assertEquals("Test Movie", result.getTitle());
    }

    @Test
    void updateMovieUpdatesMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        Movie updated = new Movie("Updated", "Drama", 2021, 110, 9);
        Movie result = movieService.updateMovie(1L, updated);
        verify(movieValidator).validateMovie(updated);
        verify(movieRepository).save(any(Movie.class));
        assertEquals("Updated", result.getTitle());
    }

    @Test
    void deleteMovieDeletesMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        movieService.deleteMovie(1L);
        verify(movieRepository).delete(movie);
    }

    @Test
    void deleteMovieThrowsExceptionIfNotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(MovieNotFoundException.class,
                () -> movieService.deleteMovie(1L));
    }
}