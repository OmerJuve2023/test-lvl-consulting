package omer.solutions.testlvlconsulting.service.impl;

import omer.solutions.testlvlconsulting.dto.request.ProjectRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateProjectRequest;
import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;
import omer.solutions.testlvlconsulting.entity.Project;
import omer.solutions.testlvlconsulting.entity.User;
import omer.solutions.testlvlconsulting.repository.ProjectRepository;
import omer.solutions.testlvlconsulting.repository.UserRepository;
import omer.solutions.testlvlconsulting.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImp(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProjectReponse getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        return new ProjectReponse(project);
    }

    @Override
    public ProjectReponse createProject(ProjectRequest projectRequest) {
        User user = userRepository.findById(projectRequest.getIdUser()).orElseThrow();

        Project project = Project.builder()
                .nombre(projectRequest.getName())
                .descripcion(projectRequest.getDescripcion())
                .estado(projectRequest.getEstado())
                .fechaInicio(projectRequest.getFechaInicio())
                .fechaFin(projectRequest.getFechaFin())
                .user(user)
                .build();
        projectRepository.save(project);
        return new ProjectReponse(project);
    }

    @Override
    public ProjectReponse deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        projectRepository.deleteById(id);
        return new ProjectReponse(project);
    }

    @Override
    public ProjectReponse updateProject(UpdateProjectRequest updateProjectRequest) {
        Project project = projectRepository.findById(updateProjectRequest.getId()).orElseThrow();
        project.setNombre(updateProjectRequest.getNombre());
        project.setDescripcion(updateProjectRequest.getDescripcion());
        project.setEstado(updateProjectRequest.getEstado());
        project.setFechaInicio(updateProjectRequest.getFechaInicio());
        project.setFechaFin(updateProjectRequest.getFechaFIn());
        projectRepository.save(project);
        return new ProjectReponse(project);
    }

    @Override
    public List<ProjectReponse> listAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(ProjectReponse::new).toList();
    }

    @Override
    public List<ProjectReponse> listByIdUser(Long id) {
        User user = projectRepository.findById(id).orElseThrow().getUser();
        return projectRepository.findAllByUser(user.getId());
    }
}
