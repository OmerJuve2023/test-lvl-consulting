package omer.solutions.testlvlconsulting.service;

import omer.solutions.testlvlconsulting.dto.request.UpdatePasswordRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateUserRequest;
import omer.solutions.testlvlconsulting.dto.request.UserRequest;
import omer.solutions.testlvlconsulting.dto.response.UserResponse;

import java.io.IOException;

public interface UserService {
    UserResponse getUserById(Long id);

    UserResponse createUser(UserRequest userRequest) throws IOException;

    UserResponse deleteUser(Long id);

    UserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException;

    void updateUserPassword(UpdatePasswordRequest updatePasswordRequest);
    UserResponse getUserByEmail(String username);
}
