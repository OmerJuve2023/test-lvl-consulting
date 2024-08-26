package omer.solutions.testlvlconsulting.service;

import omer.solutions.testlvlconsulting.dto.request.ProjectRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateProjectRequest;
import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;

import java.util.List;

public interface ProjectService {
    ProjectReponse getProjectById(Long id);

    ProjectReponse createProject(ProjectRequest projectRequest);

    ProjectReponse deleteProject(Long id);

    ProjectReponse updateProject(UpdateProjectRequest updateProjectRequest);

    List<ProjectReponse> listAll();

    List<ProjectReponse> listByIdUser(Long id);
}
