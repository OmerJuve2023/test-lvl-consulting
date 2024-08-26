package omer.solutions.testlvlconsulting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import omer.solutions.testlvlconsulting.entity.Project;

@Getter
@Builder
@AllArgsConstructor
public class TaskResponse {

    private String id;
    private String codigo;
    private String nombre;
    private String categoria;
    private Project proyecto;

}
