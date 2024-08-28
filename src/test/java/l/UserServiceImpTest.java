package l;

import omer.solutions.testlvlconsulting.dto.request.UpdatePasswordRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateUserRequest;
import omer.solutions.testlvlconsulting.dto.request.UserRequest;
import omer.solutions.testlvlconsulting.dto.response.UserResponse;
import omer.solutions.testlvlconsulting.entity.Role;
import omer.solutions.testlvlconsulting.entity.User;
import omer.solutions.testlvlconsulting.repository.RoleRepository;
import omer.solutions.testlvlconsulting.repository.UserRepository;
import omer.solutions.testlvlconsulting.service.impl.UserServiceImp;
import omer.solutions.testlvlconsulting.utils.Images;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static omer.solutions.testlvlconsulting.utils.Images.uploadImageDefault;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImp userServiceImp;

    private User createSampleUser(Long id, String username, String email) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword("encodedPassword");
        user.setFirstName("First");
        user.setLastName("Last");
        user.setCompany("Company");
        user.setPhone("1234567890");
        user.setAuthorities(new HashSet<>());
        user.setImage(new byte[]{0});
        return user;
    }

    private Role createSampleRole(String authority) {
        Role role = new Role();
        role.setAuthority(authority);
        return role;
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userServiceImp.getUserById(userId);
        });

        assertEquals("User with id 1 not found", exception.getMessage());

        verify(userRepository).findById(userId);
    }

    @Test
    void getUserById() {
        Long userId = 1L;
        User user = createSampleUser(userId, "testuser", "testuser@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        UserResponse response = userServiceImp.getUserById(userId);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("testuser@example.com", response.getEmail());

        verify(userRepository).findById(userId);
    }

    @Test
    void createUser() throws IOException {

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("newuser");
        userRequest.setEmail("newuser@example.com");
        userRequest.setPassword("password");
        userRequest.setFirstName("First");
        userRequest.setLastName("Last");
        userRequest.setCompany("Company");
        userRequest.setPhone("1234567890");

        Role roleUser = createSampleRole("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        userRequest.setAuthority(roles);

        when(roleRepository.findByAuthority("ROLE_USER")).thenReturn(Optional.of(roleUser));
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Mockear método estático uploadImageDefault
        try (MockedStatic<Images> mockedImages = mockStatic(Images.class)) {
            byte[] defaultImage = new byte[]{0, 1, 2};
            mockedImages.when(() -> uploadImageDefault("static/default.png")).thenReturn(defaultImage);

            User savedUser = createSampleUser(1L, "newuser", "newuser@example.com");
            savedUser.setAuthorities(roles);
            savedUser.setImage(defaultImage);

            when(userRepository.save(any(User.class))).thenReturn(savedUser);

            // Act
            UserResponse response = userServiceImp.createUser(userRequest);

            // Assert
            assertNotNull(response);
            assertEquals(1L, response.getId());
            assertEquals("newuser", response.getUsername());
            assertEquals("newuser@example.com", response.getEmail());
            assertEquals("First", response.getFirstName());
            assertEquals("Last", response.getLastName());
            assertEquals("Company", response.getCompany());
            assertEquals("1234567890", response.getPhone());
            assertArrayEquals(defaultImage, response.getImage());

            verify(roleRepository).findByAuthority("ROLE_USER");
            verify(passwordEncoder).encode("password");
            verify(userRepository).save(any(User.class));
            mockedImages.verify(() -> uploadImageDefault("static/default.png"));
        }

    }

    @Test
    void deleteUser() {
        Long userId = 1L;
        User user = createSampleUser(userId, "testuser", "testuser@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        doNothing().when(userRepository).deleteUserTask(userId);
        doNothing().when(userRepository).deleteUserProject(userId);
        doNothing().when(userRepository).deleteUserRole(userId);
        doNothing().when(userRepository).deleteById(userId);

        // Act
        UserResponse response = userServiceImp.deleteUser(userId);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.getId());
        assertEquals("testuser", response.getUsername());

        verify(userRepository).findById(userId);
        verify(userRepository).deleteUserTask(userId);
        verify(userRepository).deleteUserProject(userId);
        verify(userRepository).deleteUserRole(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void testCreateUser_RoleNotFound() throws IOException {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("newuser");
        userRequest.setEmail("newuser@example.com");
        userRequest.setPassword("password");
        userRequest.setFirstName("First");
        userRequest.setLastName("Last");
        userRequest.setCompany("Company");
        userRequest.setPhone("1234567890");

        Role roleUser = createSampleRole("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        userRequest.setAuthority(roles);

        when(roleRepository.findByAuthority("ROLE_USER")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        try (MockedStatic<Images> mockedImages = mockStatic(Images.class)) {
            byte[] defaultImage = new byte[]{0, 1, 2};
            mockedImages.when(() -> uploadImageDefault("static/default.png")).thenReturn(defaultImage);

            User savedUser = createSampleUser(1L, "newuser", "newuser@example.com");
            savedUser.setAuthorities(Collections.emptySet());
            savedUser.setImage(defaultImage);

            when(userRepository.save(any(User.class))).thenReturn(savedUser);

            // Act
            UserResponse response = userServiceImp.createUser(userRequest);

            // Assert
            assertNotNull(response);
            assertEquals(1L, response.getId());
            assertEquals("newuser", response.getUsername());
            assertEquals("newuser@example.com", response.getEmail());
            assertEquals("First", response.getFirstName());
            assertEquals("Last", response.getLastName());
            assertEquals("Company", response.getCompany());
            assertEquals("1234567890", response.getPhone());
            assertArrayEquals(defaultImage, response.getImage());

            verify(roleRepository).findByAuthority("ROLE_USER");
            verify(passwordEncoder).encode("password");
            verify(userRepository).save(any(User.class));
            mockedImages.verify(() -> uploadImageDefault("static/default.png"));
        }
    }

    @Test
    void updateUser() throws IOException {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(1L);
        updateUserRequest.setUsername("updateduser");
        updateUserRequest.setEmail("updated@example.com");
        updateUserRequest.setFirstName("UpdatedFirst");
        updateUserRequest.setLastName("UpdatedLast");
        updateUserRequest.setCompany("UpdatedCompany");
        updateUserRequest.setPhone("0987654321");

        User existingUser = createSampleUser(1L, "testuser", "testuser@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(existingUser.getPassword())).thenReturn("encodedNewPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserResponse response = userServiceImp.updateUser(updateUserRequest);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("updateduser", response.getUsername());
        assertEquals("updated@example.com", response.getEmail());
        assertEquals("UpdatedFirst", response.getFirstName());
        assertEquals("UpdatedLast", response.getLastName());
        assertEquals("UpdatedCompany", response.getCompany());
        assertEquals("0987654321", response.getPhone());
        assertEquals("encodedNewPassword", existingUser.getPassword());

        verify(userRepository).findById(1L);
        verify(passwordEncoder).encode(existingUser.getPassword());
        verify(userRepository).save(existingUser);
    }

    @Test
    void testUpdateUser_UserNotFound() throws IOException {
        // Arrange
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(1L);
        updateUserRequest.setUsername("updateduser");
        updateUserRequest.setEmail("updated@example.com");
        updateUserRequest.setFirstName("UpdatedFirst");
        updateUserRequest.setLastName("UpdatedLast");
        updateUserRequest.setCompany("UpdatedCompany");
        updateUserRequest.setPhone("0987654321");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userServiceImp.updateUser(updateUserRequest);
        });

        assertEquals("User with id 1 not found", exception.getMessage());

        verify(userRepository).findById(1L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUserPassword() {
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmail("testuser@example.com");
        updatePasswordRequest.setOldPassword("oldPassword");
        updatePasswordRequest.setNewPassword("newPassword");

        User user = createSampleUser(1L, "testuser", "testuser@example.com");
        when(userRepository.findByUsername("testuser@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("oldPassword", "encodedPassword")).thenReturn(true);
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userServiceImp.updateUserPassword(updatePasswordRequest);

        // Assert
        assertEquals("encodedNewPassword", user.getPassword());

        verify(userRepository).findByUsername("testuser@example.com");
        verify(passwordEncoder).matches("oldPassword", "encodedPassword");
        verify(passwordEncoder).encode("newPassword");
        verify(userRepository).save(user);
    }

    @Test
    void testUpdateUserPassword_UserNotFound() {
        // Arrange
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmail("testuser@example.com");
        updatePasswordRequest.setOldPassword("oldPassword");
        updatePasswordRequest.setNewPassword("newPassword");

        when(userRepository.findByUsername("testuser@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userServiceImp.updateUserPassword(updatePasswordRequest);
        });

        assertEquals("User with email testuser@example.com not found", exception.getMessage());

        verify(userRepository).findByUsername("testuser@example.com");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testUpdateUserPassword_IncorrectOldPassword() {
        // Arrange
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmail("testuser@example.com");
        updatePasswordRequest.setOldPassword("wrongOldPassword");
        updatePasswordRequest.setNewPassword("newPassword");

        User user = createSampleUser(1L, "testuser", "testuser@example.com");
        when(userRepository.findByUsername("testuser@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongOldPassword", "encodedPassword")).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceImp.updateUserPassword(updatePasswordRequest);
        });

        assertEquals("Old password is incorrect", exception.getMessage());

        verify(userRepository).findByUsername("testuser@example.com");
        verify(passwordEncoder).matches("wrongOldPassword", "encodedPassword");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getUserByEmail() {
        String email = "testuser@example.com";
        User user = createSampleUser(1L, "testuser", email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        // Act
        UserResponse response = userServiceImp.getUserByEmail(email);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals(email, response.getEmail());

        verify(userRepository).findByEmail(email);
    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        // Arrange
        String email = "testuser@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            userServiceImp.getUserByEmail(email);
        });


        verify(userRepository).findByEmail(email);
    }

    @Test
    void uploadImage() throws IOException {
        Long userId = 1L;
        MultipartFile file = mock(MultipartFile.class);
        byte[] imageBytes = new byte[]{1, 2, 3};

        User user = createSampleUser(userId, "testuser", "testuser@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(file.getBytes()).thenReturn(imageBytes);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        userServiceImp.uploadImage(userId, file);

        // Assert
        assertArrayEquals(imageBytes, user.getImage());

        verify(userRepository).findById(userId);
        verify(file).getBytes();
        verify(userRepository).save(user);
    }

    @Test
    void testUploadImage_UserNotFound() throws IOException {
        // Arrange
        Long userId = 1L;
        MultipartFile file = mock(MultipartFile.class);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userServiceImp.uploadImage(userId, file);
        });

        assertEquals("User with id 1 not found", exception.getMessage());

        verify(userRepository).findById(userId);
        verify(file, never()).getBytes();
        verify(userRepository, never()).save(any(User.class));
    }
}