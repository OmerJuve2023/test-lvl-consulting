package omer.solutions.testlvlconsulting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import omer.solutions.testlvlconsulting.entity.Project;
import omer.solutions.testlvlconsulting.entity.Task;

import java.util.Optional;

@Getter
@Builder
public class TaskResponse {

    private String nombre;
    private Long proyectoId;

    public TaskResponse(String nombre, Long proyectoId) {
        this.nombre = nombre;
        this.proyectoId = proyectoId;
    }

    public static TaskResponse toTaskResponseFromTaskWithDetails(Task task) {
        return new TaskResponse(
                task.getNombre(),
                Optional.ofNullable(task.getProyecto())
                        .map(Project::getId)
                        .orElse(null)
        );
    }

}
