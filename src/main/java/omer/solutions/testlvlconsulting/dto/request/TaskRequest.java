package omer.solutions.testlvlconsulting.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskRequest {
    private String nombre;
    private String codigo;
    private String categoria;
    private Long idProject;
}
