package omer.solutions.testlvlconsulting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import omer.solutions.testlvlconsulting.entity.Role;
import omer.solutions.testlvlconsulting.entity.User;

import java.util.Set;

@AllArgsConstructor
@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Set<Role> authorities;
    private String firstName;
    private String lastName;
    private String company;
    private String phone;

    public static UserResponse toUserResponseFromUserWithDetails(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .authorities(user.getAuthorities())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .company(user.getCompany())
                .phone(user.getPhone())
                .build();
    }
}
