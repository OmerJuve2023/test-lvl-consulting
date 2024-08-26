package omer.solutions.testlvlconsulting.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class TaskRequest {
    private String nombre;
    private String codigo;
    private String categoria;
    private Long idProject;
    private MultipartFile image;
}
