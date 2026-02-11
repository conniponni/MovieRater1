package se.iths.connie.movierater.service;


import org.springframework.stereotype.Service;
import se.iths.connie.movierater.exception.MovieNotFoundException;
import se.iths.connie.movierater.model.Movie;
import se.iths.connie.movierater.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found with id: " + id));
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found with id: " + id));

        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
        existingMovie.setDurationMinutes(updatedMovie.getDurationMinutes());
        existingMovie.setRating(updatedMovie.getRating());

        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found with id: " + id));

        movieRepository.delete(movie);
    }
}
