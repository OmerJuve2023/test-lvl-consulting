package omer.solutions.testlvlconsulting.service.impl;

import jakarta.transaction.Transactional;
import omer.solutions.testlvlconsulting.dto.request.ProjectRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateProjectRequest;
import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;
import omer.solutions.testlvlconsulting.entity.Project;
import omer.solutions.testlvlconsulting.entity.User;
import omer.solutions.testlvlconsulting.repository.ProjectRepository;
import omer.solutions.testlvlconsulting.repository.UserRepository;
import omer.solutions.testlvlconsulting.service.ProjectService;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Function;

import static omer.solutions.testlvlconsulting.utils.Images.uploadImageDefault;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImp(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ProjectReponse getProjectById(Long id, Long idUser) {
        Project project = projectRepository.findByIdAndUser(id, idUser);
        return getProjectReponse(project);
    }

    @Override
    public ProjectReponse createProject(ProjectRequest projectRequest) throws IOException {
        User user = userRepository.findById(projectRequest.getIdUser()).orElseThrow();

        Project project = Project.builder()
                .nombre(projectRequest.getName())
                .descripcion(projectRequest.getDescripcion())
                .estado(projectRequest.getEstado())
                .fechaInicio(projectRequest.getFechaInicio())
                .fechaFin(projectRequest.getFechaFin())
                .user(user)
                .image(uploadImageDefault("static/default.png"))
                .build();

        projectRepository.save(project);
        return getProjectReponse(project);
    }

    private static ProjectReponse getProjectReponse(Project project) {
        return new ProjectReponse(
                project.getId(),
                project.getNombre(),
                project.getDescripcion(),
                project.getEstado(),
                project.getFechaInicio(),
                project.getFechaFin(),
                project.getUser().getId(),
                project.getImage()
        );
    }

    @Override
    public ProjectReponse deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        projectRepository.deleteById(id);
        return getProjectReponse(project);
    }

    @Override
    public ProjectReponse updateProject(UpdateProjectRequest updateProjectRequest) throws IOException {
        Project project = projectRepository.findById(updateProjectRequest.getId()).orElseThrow();
        project.setNombre(updateProjectRequest.getNombre());
        project.setDescripcion(updateProjectRequest.getDescripcion());
        project.setEstado(updateProjectRequest.getEstado());
        project.setFechaInicio(updateProjectRequest.getFechaInicio());
        project.setFechaFin(updateProjectRequest.getFechaFIn());
        projectRepository.save(project);
        return getProjectReponse(project);
    }

    @Override
    public List<ProjectReponse> listAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(
                getProjectProjectReponseFunction()
        ).toList();
    }

    private static Function<Project, ProjectReponse> getProjectProjectReponseFunction() {
        return project -> new ProjectReponse(
                project.getId(),
                project.getNombre(),
                project.getDescripcion(),
                project.getEstado(),
                project.getFechaInicio(),
                project.getFechaFin(),
                project.getUser().getId(),
                project.getImage()
        );
    }

    @Override
    public List<ProjectReponse> listByIdUser(Long userId) {
        List<Project> projects = projectRepository.findAllByUser(userId);
        return projects.stream().map(
                getProjectProjectReponseFunction()
        ).toList();
    }

    @Override
    public void uploadImage(Long id, MultipartFile file) throws IOException {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Project with id " + id + " not found")
        );
        project.setImage(file.getBytes());
        projectRepository.save(project);
    }
}
