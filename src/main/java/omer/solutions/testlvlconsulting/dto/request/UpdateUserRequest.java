package omer.solutions.testlvlconsulting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile image;

}
