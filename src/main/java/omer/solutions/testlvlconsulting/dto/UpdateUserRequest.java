package omer.solutions.testlvlconsulting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private String username;

}
