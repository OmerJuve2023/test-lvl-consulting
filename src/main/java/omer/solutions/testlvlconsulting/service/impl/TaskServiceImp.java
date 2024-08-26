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
    public TaskResponse getTaskById(Long id, Long idUser) {
        Task task = taskRepository.findByIdAndUser(id, idUser);
        return getTaskResponse(task);
    }

    private static TaskResponse getTaskResponse(Task task) {
        return new TaskResponse(
                task.getId().toString(),
                task.getCodigo(),
                task.getNombre(),
                task.getCategoria(),
                task.getProyecto()
        );
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Project project = projectRepository.findById(taskRequest.getIdProject()).orElseThrow(
                () -> new UsernameNotFoundException("Project with id " + taskRequest.getIdProject() + " not found")
        );
        Task newTask = Task.builder()
                .nombre(taskRequest.getNombre())
                .codigo(taskRequest.getCodigo())
                .categoria(taskRequest.getCategoria())
                .proyecto(project)
                .build();
        taskRepository.save(newTask);
        return getTaskResponse(newTask);
    }

    @Override
    public TaskResponse deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Task with id " + id + " not found")
        );
        return getTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(UpdateTaskRequest updateTaskRequest) {
        Project project = projectRepository.findById(updateTaskRequest.getIdProyecto()).orElseThrow(
                () -> new UsernameNotFoundException("Project with id " + updateTaskRequest.getIdProyecto() + " not found")
        );
        Task task = taskRepository.findById(updateTaskRequest.getId()).orElseThrow(
                () -> new UsernameNotFoundException("Task with id " + updateTaskRequest.getId() + " not found")
        );
        task.setNombre(updateTaskRequest.getNombre());
        task.setProyecto(project);
        task.setCategoria(updateTaskRequest.getCategoria());
        task.setCodigo(updateTaskRequest.getCodigo());
        taskRepository.save(task);
        return getTaskResponse(task);
    }

    @Override
    public List<TaskResponse> listAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(
                task -> new TaskResponse(
                        task.getId().toString(),
                        task.getCodigo(),
                        task.getNombre(),
                        task.getCategoria(),
                        task.getProyecto()
                )
        ).toList();
    }

    @Override
    public List<TaskResponse> listByIdUser(Long projectId) {
        List<Task> tasks = taskRepository.findAllByUser(projectId);
        return tasks.stream().map(
                task -> new TaskResponse(
                        task.getId().toString(),
                        task.getCodigo(),
                        task.getNombre(),
                        task.getCategoria(),
                        task.getProyecto()
                )
        ).toList();
    }
}
