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
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUserId(id);
        userValidator.validate(user);


        if (!user.getUsername().equals(existingUser.getUsername())) {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new DuplicateFoundException("Username: " + user.getUsername() + " already exists");
            }
        }
        if (!user.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new DuplicateFoundException("Email: " + user.getEmail() + " already exists");
            }
        }
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
