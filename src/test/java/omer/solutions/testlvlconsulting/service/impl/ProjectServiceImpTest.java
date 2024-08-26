package omer.solutions.testlvlconsulting.service.impl;

import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;
import omer.solutions.testlvlconsulting.entity.Project;
import omer.solutions.testlvlconsulting.entity.User;
import omer.solutions.testlvlconsulting.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ProjectServiceImpTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImp projectService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProjectById() {
        User user = new User();
        user.setId(1L);
        user.setPassword(passwordEncoder.encode("password"));
        user.setUsername("user1");

        Project project = new Project();
        project.setId(1L);
        project.setNombre("Project 1");
        project.setDescripcion("Description 1");
        project.setEstado("Active");
        project.setFechaInicio(new Date());
        project.setFechaFin(new Date());
        project.setUser(user);

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