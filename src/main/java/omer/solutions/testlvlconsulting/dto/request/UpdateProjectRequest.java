package omer.solutions.testlvlconsulting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
public class UpdateProjectRequest {
    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private Date fechaInicio;
    private Date fechaFIn;
}
