package omer.solutions.testlvlconsulting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omer.solutions.testlvlconsulting.dto.request.TaskRequest;
import omer.solutions.testlvlconsulting.dto.request.UpdateTaskRequest;
import omer.solutions.testlvlconsulting.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("create")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

    @PostMapping("delete")
    public ResponseEntity<?> deleteTask(@RequestBody Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("get")
    public ResponseEntity<?> getTaskById(@RequestParam Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskService.listAll());
    }

    @PostMapping("update")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest));
    }
}
