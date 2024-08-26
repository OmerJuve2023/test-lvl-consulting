package omer.solutions.testlvlconsulting.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omer.solutions.testlvlconsulting.config.JwtUtils;
import omer.solutions.testlvlconsulting.dto.request.LoginRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdatePasswordRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateUserRequest;
import omer.solutions.testlvlconsulting.dto.request.UserRequest;
import omer.solutions.testlvlconsulting.dto.response.UserResponse;
import omer.solutions.testlvlconsulting.entity.User;
import omer.solutions.testlvlconsulting.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/auth")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = userService.getUserByEmail(loginRequest.getUsername());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userResponse.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserResponse(
                                user.getId(),
                                user.getUsername(),
                                user.getEmail(),
                                user.getAuthorities(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getCompany(),
                                user.getPhone(),
                                user.getImage()
                        )
                );
    }

    @PostMapping("signup")
    public UserResponse signupUser(@RequestBody @Valid UserRequest request) throws IOException {
        log.debug("Register: " + request.toString());
        return userService.createUser(request);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @PutMapping("update")
    public UserResponse updateUser(@RequestBody @Valid UpdateUserRequest request) throws IOException {
        log.debug("Update: " + request.toString());
        return userService.updateUser(request);
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        log.debug("Change password: {}", request.toString());
        userService.updateUserPassword(request);
        return "Password changed successfully!";
    }
}
