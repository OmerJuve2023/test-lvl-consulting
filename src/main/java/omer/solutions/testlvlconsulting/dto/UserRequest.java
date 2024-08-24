package omer.solutions.testlvlconsulting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omer.solutions.testlvlconsulting.entity.Role;

import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String company;
    private String phone;


    private Set<omer.solutions.testlvlconsulting.entity.Role> authority;

}
