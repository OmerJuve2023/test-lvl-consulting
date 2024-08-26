package omer.solutions.testlvlconsulting.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ProjectRequest {
    private String name;
    private String descripcion;
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;
    private Long idUser;
}