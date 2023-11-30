package com.todo_management_system.contoller;

import com.todo_management_system.dto.TaskDto;
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
    public ResponseEntity<String> addTask(@RequestBody TaskDto taskDto) {
        taskService.addTask(taskDto.getUserId(), taskDto.getTaskId());

        return ResponseEntity.ok("Task added successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete")
    public ResponseEntity<String> deleteTask(Long userId, Long taskId) {
        taskService.removeTask(userId, taskId);

        return ResponseEntity.ok("Task deleted successfully");
    }
}
