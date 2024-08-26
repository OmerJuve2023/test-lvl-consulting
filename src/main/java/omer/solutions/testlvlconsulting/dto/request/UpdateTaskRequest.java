package omer.solutions.testlvlconsulting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UpdateTaskRequest {
    private Long idTask;
    private String nombre;
    private Long idProyecto;
}
