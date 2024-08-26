package omer.solutions.testlvlconsulting.service;

import omer.solutions.testlvlconsulting.dto.request.ProjectRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateProjectRequest;
import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;

import java.io.IOException;
import java.util.List;

public interface ProjectService {
    ProjectReponse getProjectById(Long id, Long idUser);

    ProjectReponse createProject(ProjectRequest projectRequest) throws IOException;

    ProjectReponse deleteProject(Long id);

    ProjectReponse updateProject(UpdateProjectRequest updateProjectRequest) throws IOException;

    List<ProjectReponse> listAll();

    List<ProjectReponse> listByIdUser(Long id);

}
