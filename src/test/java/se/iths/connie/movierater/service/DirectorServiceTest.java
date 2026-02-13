package se.iths.connie.movierater.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.connie.movierater.model.Director;
import se.iths.connie.movierater.repository.DirectorRepository;
import se.iths.connie.movierater.validator.DirectorValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {

    @InjectMocks
    DirectorService directorService;

    @Mock
    DirectorRepository directorRepository;

    @Mock
    DirectorValidator directorValidator;

    Director director;
    List<Director> directors = new ArrayList<>();

    @BeforeEach
    void SetUp() {
        director = new Director();
        director.setId(1L);
        director.setName("Sam Raimi");
        director.setBirthYear(1959);
        director.setNationality("American");
        director.setNumberOfMoviesDirected(12);
        director.setActive(true);

    }

    @Test
    void saveNewDirectorTest() {
        directorService.createDirector(director);
        verify(directorRepository).save(director);
    }

    @Test
    void getDirectorTest() {
        Mockito.when(directorRepository.findById(1L)).thenReturn(Optional.of(director));
        Director result = directorService.getDirector(director.getId());
        assertEquals(director, result);
    }

    @Test
    void getAllDirectorsTest() {
        Mockito.when(directorService.getAllDirectors()).thenReturn(directors);
        List<Director> resault = directorService.getAllDirectors();
        assertEquals(directors, resault);
    }

    @Test
    void updateDirectorTest() {
        Mockito.when(directorRepository.findById(1L))
                .thenReturn(Optional.of(director));
        Mockito.when(directorRepository.save(any(Director.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Director updatedDirector = new Director();
        updatedDirector.setName("Sam Raimi");
        updatedDirector.setBirthYear(1959);
        updatedDirector.setNationality("American");
        updatedDirector.setNumberOfMoviesDirected(64);
        updatedDirector.setActive(true);

        Director result = directorService.updateDirector(1L, updatedDirector);

        assertEquals(64, result.getNumberOfMoviesDirected());
        verify(directorRepository).save(any(Director.class));

    }

    @Test
    void updateDirectorThrowsExceptionIfNotFound() {
        Mockito.when(directorRepository.findById(2L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                directorService.updateDirector(2L, new Director()));
    }

    @Test
    void deleteDirectorTest() {
        Mockito.when(directorRepository.findById(1L))
                .thenReturn(Optional.of(director));

        directorService.deleteDirector(1L);

        verify(directorRepository).deleteById(1L);
    }

    @Test
    void deleteDirectorThrowsExceptionIfNotFound() {
        Mockito.when(directorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                directorService.deleteDirector(1L));
    }
}
