package se.iths.connie.movierater.service;

import org.springframework.stereotype.Service;
import se.iths.connie.movierater.exception.DuplicateFoundException;
import se.iths.connie.movierater.exception.UserNotFoundException;
import se.iths.connie.movierater.model.User;
import se.iths.connie.movierater.repository.UserRepository;
import se.iths.connie.movierater.validator.UserValidator;

import java.util.List;

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

    public User updateUser(Long id, User user) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUserId(id);
        userValidator.validate(user);

        userRepository.findByUsername(user.getUsername()).ifPresent(found -> {
            if (!found.getUserId().equals(id)) {
                throw new DuplicateFoundException("Username: " + user.getUsername() + " already exists");
            }
        });
        userRepository.findByEmail(user.getEmail()).ifPresent(found -> {
            if (!found.getUserId().equals(id)) {
                throw new DuplicateFoundException("Email: " + user.getEmail() + " already exists");
            }
        });

        return userRepository.save(user);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public User findUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User not found"));

    }


}
