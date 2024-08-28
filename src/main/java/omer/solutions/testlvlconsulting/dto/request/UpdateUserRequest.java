package omer.solutions.testlvlconsulting.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private String username;

}
