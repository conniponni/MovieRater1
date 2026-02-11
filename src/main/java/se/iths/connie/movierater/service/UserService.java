package se.iths.connie.movierater.service;

import org.springframework.stereotype.Service;
import se.iths.connie.movierater.exception.DuplicateFoundException;
import se.iths.connie.movierater.exception.UserNotFoundException;
import se.iths.connie.movierater.model.User;
import se.iths.connie.movierater.repository.UserRepository;
import se.iths.connie.movierater.validator.UserValidator;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserValidator userValidator;
    private final UserRepository userRepository;

    public UserService(UserValidator userValidator, UserRepository userRepository) {
        this.userValidator = userValidator;
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        userValidator.validate(user);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateFoundException("Username: " + user.getUsername() + " already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateFoundException("Email: " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Username not found"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException("Email not found"));
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


}
