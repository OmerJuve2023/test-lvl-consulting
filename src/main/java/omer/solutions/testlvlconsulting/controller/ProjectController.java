package omer.solutions.testlvlconsulting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omer.solutions.testlvlconsulting.dto.request.ProjectRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateProjectRequest;
import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;
import omer.solutions.testlvlconsulting.service.ProjectService;
import omer.solutions.testlvlconsulting.utils.ApiRoutes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = ApiRoutes.ENDPOINT_PROJECT, produces = "application/json")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping(ApiRoutes.CREATE_PROJECT)
    public ProjectReponse createProject(@RequestBody ProjectRequest projectRequest) throws IOException {
        return projectService.createProject(projectRequest);
    }

    @DeleteMapping(ApiRoutes.DELETE_PROJECT)
    public ResponseEntity<?> deleteProject(@RequestParam Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @PutMapping(ApiRoutes.UPDATE_PROJECT)
    public ResponseEntity<?> updateProject(@RequestBody UpdateProjectRequest updateProjectRequest) throws IOException {
        return ResponseEntity.ok(projectService.updateProject(updateProjectRequest));
    }

    @GetMapping(ApiRoutes.GET_PROJECT)
    public ResponseEntity<?> getProjectById(@RequestParam Long id, @RequestParam Long idUser) {
        return ResponseEntity.ok().body(projectService.getProjectById(id, idUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ApiRoutes.GET_ALL_PROJECTS)
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.listAll());
    }

    @GetMapping(ApiRoutes.GET_PROJECTS_BY_USER)
    public ResponseEntity<?> getProjectsByIdUser(@RequestParam Long id) {
        return ResponseEntity.ok(projectService.listByIdUser(id));
    }

    @PostMapping(ApiRoutes.UPLOAD_IMAGE)
    public ResponseEntity<?> uploadImage(@RequestParam Long id,
                                         @RequestParam("file") MultipartFile file) throws IOException {
        projectService.uploadImage(id, file);
        return ResponseEntity.ok().body("Image uploaded successfully");
    }

    @GetMapping("donwloadImage")
    public ResponseEntity<byte[]> downloadImage(@RequestParam Long id, @RequestParam Long idUser) {
        ProjectReponse projectReponse = projectService.getProjectById(id, idUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(projectReponse.getImage().length);
        return new ResponseEntity<>(projectReponse.getImage(), headers, HttpStatus.OK);
    }
}
