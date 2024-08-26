package omer.solutions.testlvlconsulting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omer.solutions.testlvlconsulting.entity.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private MultipartFile image;

    public UserRequest(String username,
                       String email,
                       String password,
                       String firstName,
                       String lastName,
                       String company,
                       String phone,
                       Set<Role> authority,
                       MultipartFile image) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.authority = authority;
        this.image = image;
    }

    private Set<omer.solutions.testlvlconsulting.entity.Role> authority;

}
