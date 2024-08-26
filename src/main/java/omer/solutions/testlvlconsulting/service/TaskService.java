package omer.solutions.testlvlconsulting.service;

import omer.solutions.testlvlconsulting.dto.request.TaskRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateTaskRequest;
import omer.solutions.testlvlconsulting.dto.response.TaskResponse;

import java.io.IOException;
import java.util.List;

public interface TaskService {
    TaskResponse getTaskById(Long id, Long idUser);

    TaskResponse createTask(TaskRequest taskRequest) throws IOException;

    TaskResponse deleteTask(Long id);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest) throws IOException;

    List<TaskResponse> listAll();

    List<TaskResponse> listByIdUser(Long projectId);
}
