package omer.solutions.testlvlconsulting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import omer.solutions.testlvlconsulting.entity.Project;

import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class ProjectReponse {

    private Long id;
    private String name;
    private String descripcion;
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;
    private Long idUser;
}
