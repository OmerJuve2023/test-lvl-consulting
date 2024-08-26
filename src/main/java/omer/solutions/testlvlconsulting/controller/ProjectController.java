package omer.solutions.testlvlconsulting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omer.solutions.testlvlconsulting.dto.request.ProjectRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateProjectRequest;
import omer.solutions.testlvlconsulting.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("create")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @PostMapping("delete")
    public ResponseEntity<?> deleteProject(@RequestBody Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @PostMapping("update")
    public ResponseEntity<?> updateProject(@RequestBody UpdateProjectRequest updateProjectRequest) {
        return ResponseEntity.ok(projectService.updateProject(updateProjectRequest));
    }

    @GetMapping("get")
    public ResponseEntity<?> getProjectById(@RequestParam Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.listAll());
    }
}
