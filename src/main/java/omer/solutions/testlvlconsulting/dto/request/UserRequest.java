package omer.solutions.testlvlconsulting.dto.request;

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

    public UserRequest(String username,
                       String email,
                       String password,
                       String firstName,
                       String lastName,
                       String company,
                       String phone,
                       Set<Role> authority) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.authority = authority;
    }

    private Set<omer.solutions.testlvlconsulting.entity.Role> authority;

}
