package omer.solutions.testlvlconsulting.controller;

import omer.solutions.testlvlconsulting.utils.ApiRoutes;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiRoutes.ENDPOINT_TEST)
public class TestController {
    @GetMapping(ApiRoutes.ALL_ACCESS)
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping(ApiRoutes.USER_ACCESS)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping(ApiRoutes.ADMIN_ACCESS)
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
