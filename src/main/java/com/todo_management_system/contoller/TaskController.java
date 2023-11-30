package com.todo_management_system.contoller;

import com.todo_management_system.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/task")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add")
    public ResponseEntity<String> addTask(Long userId, Long taskId) {
        taskService.addTask(userId, taskId);

        return ResponseEntity.ok("Task added successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete")
    public ResponseEntity<String> deleteTask(Long userId, Long taskId) {
        taskService.deleteTask(userId, taskId);

        return ResponseEntity.ok("Task deleted successfully");
    }
}
