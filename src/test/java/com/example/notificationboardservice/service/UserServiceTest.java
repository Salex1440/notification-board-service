package com.example.notificationboardservice.service;

import com.example.notificationboardservice.entity.Role;
import com.example.notificationboardservice.entity.User;
import com.example.notificationboardservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepositoryMock;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoderMock;

    @InjectMocks
    UserService userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    AutoCloseable closeable;

    @Test
    void saveUser() {
        User user = new User();
        user.setEmail("mail@mail.ru");
        user.setPassword("password");
        user.setRole(new Role(1L, "USER"));

        doReturn(null).when(userRepositoryMock).findByEmail(user.getEmail());
        doReturn("encryptedPassword").when(bCryptPasswordEncoderMock).encode(user.getPassword());
        assertTrue(userService.saveUser(user));
        verify(userRepositoryMock).save(userCaptor.capture());
        assertEquals(userCaptor.getValue().getEmail(), user.getEmail());
        assertEquals(userCaptor.getValue().getPassword(), "encryptedPassword");
        assertEquals(userCaptor.getValue().getRole(), user.getRole());
    }

    @Test
    void userAlreadyExists() {
        User user = new User();
        user.setEmail("mail@mail.ru");
        user.setPassword("password");
        user.setRole(new Role(1L, "USER"));

        doReturn(user).when(userRepositoryMock).findByEmail(user.getEmail());
        assertFalse(userService.saveUser(user));
    }

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        closeable.close();
    }
}