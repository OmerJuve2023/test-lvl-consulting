package omer.solutions.testlvlconsulting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
