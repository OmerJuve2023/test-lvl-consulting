package omer.solutions.testlvlconsulting.service;

import omer.solutions.testlvlconsulting.dto.request.UpdatePasswordRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateUserRequest;
import omer.solutions.testlvlconsulting.dto.request.UserRequest;
import omer.solutions.testlvlconsulting.dto.response.UserResponse;

public interface UserService {
    UserResponse getUserById(Long id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse deleteUser(Long id);

    UserResponse updateUser(UpdateUserRequest updateUserRequest);

    void updateUserPassword(UpdatePasswordRequest updatePasswordRequest);
    UserResponse getUserByEmail(String username);
}
