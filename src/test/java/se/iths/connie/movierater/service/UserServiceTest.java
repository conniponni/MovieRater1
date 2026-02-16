package se.iths.connie.movierater.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.connie.movierater.exception.DuplicateFoundException;
import se.iths.connie.movierater.exception.UserNotFoundException;
import se.iths.connie.movierater.model.User;
import se.iths.connie.movierater.repository.UserRepository;
import se.iths.connie.movierater.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private User user;
    private User duplicateUser;
    private User updateUser;

    @Mock
    private UserRepository userRepository;

    @Mock
    UserValidator userValidator;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.setUserId(1L);

        duplicateUser = new User();
        duplicateUser.setUserId(2L);
        duplicateUser.setUsername("username2");
        duplicateUser.setPassword("password");
        duplicateUser.setEmail("email2@email.com");

        updateUser = new User();
        updateUser.setUsername("NewUsername2");
        updateUser.setPassword("password");
        updateUser.setEmail("NewEmail2@email.com");


    }


    @Test
    public void testFindAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userList, userService.findAllUsers());

        verify(userRepository).findAll();
    }

    @Test
    public void testFindUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertEquals(user, userService.findUserById(1L));

        verify(userRepository).findById(1L);
    }

    @Test
    public void testDeleteUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUserById(1L);
        verify(userRepository).findById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    public void testUpdateUserWithSameInfo() {
        updateUser.setUsername("username");
        updateUser.setPassword("password");
        updateUser.setEmail("email@email.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(updateUser)).thenReturn(updateUser);

        assertDoesNotThrow(() -> {
            userService.updateUser(1L, updateUser);
        });

        verify(userValidator).validate(updateUser);
        verify(userRepository).save(updateUser);
        verify(userRepository).findById(1L);
    }

    @Test
    public void testUpdateUserWithDifferentInfo() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(updateUser)).thenReturn(updateUser);

        assertDoesNotThrow(() -> {
            userService.updateUser(1L, updateUser);
        });
        verify(userValidator).validate(updateUser);
        verify(userRepository).save(updateUser);
        verify(userRepository).findById(1L);
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);

        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());

        assertEquals(user, userService.createUser(user));

        verify(userValidator).validate(user);
        verify(userRepository).findByUsername("username");
        verify(userRepository).findByEmail("email@email.com");
        verify(userRepository).save(user);
    }

    @Test
    public void testCreateDuplicatesUsernameThrowsException() {
        duplicateUser.setUsername("username");

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        assertThrows(DuplicateFoundException.class, () -> userService.createUser(duplicateUser));
        verify(userValidator).validate(duplicateUser);
        verify(userRepository).findByUsername("username");
        verify(userRepository, never()).save(duplicateUser);

    }

    @Test
    public void testCreateDuplicateEmailThrowsException() {
        duplicateUser.setEmail("email@email.com");

        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));


        assertThrows(DuplicateFoundException.class, () -> userService.createUser(duplicateUser));

        verify(userValidator).validate(duplicateUser);
        verify(userRepository).findByEmail("email@email.com");
        verify(userRepository, never()).save(duplicateUser);

    }

    @Test
    public void testUpdateDuplicateUsernameThrowsException() {
        updateUser.setUsername("username");

        when(userRepository.findById(2L)).thenReturn(Optional.of(duplicateUser));
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));


        assertThrows(DuplicateFoundException.class, () -> userService.updateUser(2L, updateUser));

        verify(userValidator).validate(updateUser);
        verify(userRepository).findByUsername("username");
        verify(userRepository, never()).save(updateUser);
    }

    @Test
    public void testUpdateDuplicateEmailThrowsException() {
        updateUser.setEmail("email@email.com");

        when(userRepository.findById(2L)).thenReturn(Optional.of(duplicateUser));
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));


        assertThrows(DuplicateFoundException.class, () -> userService.updateUser(2L, updateUser));
        verify(userValidator).validate(updateUser);
        verify(userRepository).findByEmail("email@email.com");
        verify(userRepository, never()).save(updateUser);

    }

    @Test
    public void testUpdateUserNotFound() {
        when(userRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(3L, duplicateUser));
        verify(userRepository).findById(3L);
        verify(userValidator, never()).validate(duplicateUser);
        verify(userRepository, never()).save(duplicateUser);
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUserById(3L));
        verify(userRepository).findById(3L);
        verify(userRepository, never()).deleteById(3L);
    }

    @Test
    public void testFindByIdNotFound() {
        when(userRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUserById(3L));
        verify(userRepository).findById(3L);
    }


}
