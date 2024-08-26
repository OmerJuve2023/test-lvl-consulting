package omer.solutions.testlvlconsulting.service.impl;

import omer.solutions.testlvlconsulting.dto.request.TaskRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateTaskRequest;
import omer.solutions.testlvlconsulting.dto.response.TaskResponse;
import omer.solutions.testlvlconsulting.entity.Project;
import omer.solutions.testlvlconsulting.entity.Task;
import omer.solutions.testlvlconsulting.repository.ProjectRepository;
import omer.solutions.testlvlconsulting.repository.TaskRepository;
import omer.solutions.testlvlconsulting.service.TaskService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImp(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return TaskResponse.toTaskResponseFromTaskWithDetails(
                taskRepository.findById(id).orElseThrow(
                        () -> new UsernameNotFoundException("Task with id " + id + " not found")
                )
        );

    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Project project = projectRepository.findById(taskRequest.getIdProject()).orElseThrow(
                () -> new UsernameNotFoundException("Project with id " + taskRequest.getIdProject() + " not found")
        );
        Task newTask = Task.builder()
                .nombre(taskRequest.getNombre())
                .proyecto(project)
                .build();
        return TaskResponse.toTaskResponseFromTaskWithDetails(taskRepository.save(newTask));
    }

    @Override
    public TaskResponse deleteTask(Long id) {
        TaskResponse taskResponse = getTaskById(id);
        taskRepository.deleteById(id);
        return taskResponse;
    }

    @Override
    public TaskResponse updateTask(UpdateTaskRequest updateTaskRequest) {
        Task task = taskRepository.findById(updateTaskRequest.getIdTask()).orElseThrow(
                () -> new UsernameNotFoundException("Task with id " + updateTaskRequest.getIdProyecto() + " not found")
        );
        Project project = projectRepository.findById(updateTaskRequest.getIdProyecto()).orElseThrow(
                () -> new UsernameNotFoundException("Project with id " + updateTaskRequest.getIdProyecto() + " not found")
        );
        task.setNombre(updateTaskRequest.getNombre());
        task.setProyecto(project);
        return TaskResponse.toTaskResponseFromTaskWithDetails(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> listAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskResponse::toTaskResponseFromTaskWithDetails).toList();
    }
}
