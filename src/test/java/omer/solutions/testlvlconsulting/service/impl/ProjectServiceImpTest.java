package omer.solutions.testlvlconsulting.service.impl;

import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;
import omer.solutions.testlvlconsulting.entity.Project;
import omer.solutions.testlvlconsulting.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectServiceImpTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImp projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProjectById() {

        Project project = new Project();
        project.setId(1L);
        project.setNombre("Project 1");
        project.setDescripcion("Description 1");
        project.setEstado("Active");
        project.setFechaInicio(new Date());
        project.setFechaFin(new Date());
        project.setUser(null);

        when(projectRepository.findByIdAndUser(1L, 1L)).thenReturn(project);

        ProjectReponse projectReponse = projectService.getProjectById(1L, 1L);

        assertNotNull(projectReponse);
        assertEquals(projectReponse.getId(), project.getId().toString());
        assertEquals(projectReponse.getName(), project.getNombre());
        assertEquals(projectReponse.getDescripcion(), project.getDescripcion());
        assertEquals(projectReponse.getEstado(), project.getEstado());
        assertEquals(projectReponse.getFechaInicio(), project.getFechaInicio());
        assertEquals(projectReponse.getFechaFin(), project.getFechaFin());
        assertEquals(projectReponse.getIdUser(), project.getUser());
    }
}