package omer.solutions.testlvlconsulting.dto.request;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class UpdatePasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;

}
