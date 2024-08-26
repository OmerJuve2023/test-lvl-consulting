package omer.solutions.testlvlconsulting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private String name;
    private String descripcion;
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;
    private Long idUser;

}