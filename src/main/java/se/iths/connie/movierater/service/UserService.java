package se.iths.connie.movierater.service;

import org.springframework.stereotype.Service;
import se.iths.connie.movierater.exception.DuplicateFoundException;
import se.iths.connie.movierater.model.User;
import se.iths.connie.movierater.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateFoundException("Username: " + user.getUsername() + " already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateFoundException("Email: " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


}
