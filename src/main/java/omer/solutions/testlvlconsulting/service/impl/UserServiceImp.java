package omer.solutions.testlvlconsulting.service.impl;

import lombok.RequiredArgsConstructor;
import omer.solutions.testlvlconsulting.dto.request.UpdatePasswordRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateUserRequest;
import omer.solutions.testlvlconsulting.dto.request.UserRequest;
import omer.solutions.testlvlconsulting.dto.response.UserResponse;
import omer.solutions.testlvlconsulting.entity.Role;
import omer.solutions.testlvlconsulting.entity.User;
import omer.solutions.testlvlconsulting.repository.RoleRepository;
import omer.solutions.testlvlconsulting.repository.UserRepository;
import omer.solutions.testlvlconsulting.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public UserResponse getUserById(Long id) throws UsernameNotFoundException {
        return UserResponse.toUserResponseFromUserWithDetails(
                userRepository.findById(id).orElseThrow(
                        () -> new UsernameNotFoundException("User with id " + id + " not found")
                )
        );
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws IOException {

        Set<Role> authorities = new HashSet<>();
        for (Role roleString : userRequest.getAuthority()) {
            Optional<Role> findRole = roleRepository.findByAuthority(roleString.getAuthority());
            findRole.ifPresent(authorities::add);
        }

        User newUser = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encoder.encode(userRequest.getPassword()))
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .company(userRequest.getCompany())
                .phone(userRequest.getPhone())
                .authorities(authorities)
                .image(userRequest.getImage().getBytes())
                .build();

        return UserResponse.toUserResponseFromUserWithDetails(userRepository.save(newUser));
    }

    @Override
    public UserResponse deleteUser(Long id) {
        UserResponse userResponse = this.getUserById(id);
        userRepository.deleteById(id);
        return userResponse;
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException {
        User user = userRepository.findById(updateUserRequest.getId()).orElseThrow(
                () -> new UsernameNotFoundException("User with id " + updateUserRequest.getId() + " not found")
        );
        user.setEmail(updateUserRequest.getEmail());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setCompany(updateUserRequest.getCompany());
        user.setPhone(updateUserRequest.getPhone());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthorities(user.getAuthorities());
        user.setUsername(updateUserRequest.getUsername());
        user.setImage(updateUserRequest.getImage().getBytes());
        userRepository.save(user);
        return UserResponse.toUserResponseFromUserWithDetails(user);
    }

    @Override
    public void updateUserPassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findByUsername(String.valueOf(updatePasswordRequest.getEmail())).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + updatePasswordRequest.getEmail() + " not found")
        );
        if (encoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(updatePasswordRequest.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Old password is incorrect");
        }
    }

    @Override
    public UserResponse getUserByEmail(String username) {
        return UserResponse.toUserResponseFromUserWithDetails(
                userRepository.findByEmail(username));
    }
}