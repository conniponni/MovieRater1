package se.iths.connie.movierater.service;

import se.iths.connie.movierater.exception.DirectorNotFoundException;
import se.iths.connie.movierater.model.Director;
import se.iths.connie.movierater.repository.DirectorRepository;

import java.util.List;

public class DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Director createDirector(Director director) {
        return directorRepository.save(director);
    }

    public Director getDirector(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("There is no director with id: " + id));
    }

    public Director updateDirector(Long id, Director director) {
        director.setId(id);
        return directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }


}
