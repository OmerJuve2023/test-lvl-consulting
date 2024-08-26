package omer.solutions.testlvlconsulting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omer.solutions.testlvlconsulting.dto.request.ProjectRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateProjectRequest;
import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;
import omer.solutions.testlvlconsulting.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/project", produces = "application/json")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("create")
    public ProjectReponse createProject(@RequestBody ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteProject(@RequestParam Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @PutMapping("update")
    public ResponseEntity<?> updateProject(@RequestBody UpdateProjectRequest updateProjectRequest) {
        return ResponseEntity.ok(projectService.updateProject(updateProjectRequest));
    }

    @GetMapping("get")
    public ResponseEntity<?> getProjectById(@RequestParam Long id, @RequestParam Long idUser) {
        return ResponseEntity.ok().body(projectService.getProjectById(id, idUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getall")
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.listAll());
    }

    @GetMapping("getbyuser")
    public ResponseEntity<?> getProjectsByIdUser(@RequestParam Long id) {
        return ResponseEntity.ok(projectService.listByIdUser(id));
    }
}
