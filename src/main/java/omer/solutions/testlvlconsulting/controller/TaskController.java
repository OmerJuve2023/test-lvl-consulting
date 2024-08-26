package omer.solutions.testlvlconsulting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omer.solutions.testlvlconsulting.dto.request.TaskRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateTaskRequest;
import omer.solutions.testlvlconsulting.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("create")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) throws IOException {
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteTask(@RequestParam Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("get")
    public ResponseEntity<?> getTaskById(@RequestParam Long id, @RequestParam Long idUser) {
        return ResponseEntity.ok(taskService.getTaskById(id, idUser));
    }

    @GetMapping("getbyuser")
    public ResponseEntity<?> getTasksByIdUser(@RequestParam Long id) {
        return ResponseEntity.ok(taskService.listByIdUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getall")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskService.listAll());
    }

    @PutMapping("update")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) throws IOException {
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest));
    }
}
