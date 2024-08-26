package omer.solutions.testlvlconsulting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UpdateTaskRequest {
    private Long id;
    private String codigo;
    private String nombre;
    private String categoria;
    private Long idProyecto;
}
