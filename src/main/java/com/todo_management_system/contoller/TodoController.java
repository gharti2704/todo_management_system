package com.todo_management_system.contoller;

import com.todo_management_system.dto.TodoDto;
import com.todo_management_system.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
@CrossOrigin("*")
public class TodoController {

    private TodoService todoService;

    @PostMapping("create")
    // Method level authorization
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> addTodo (@RequestBody TodoDto todoDto) {
        var todo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @GetMapping
    // Method level authorization
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        var todos = todoService.getAllTodos();

        return ResponseEntity.ok(todos);
    }

    @GetMapping("{id}")
    // Method level authorization
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDto> getTodo (@PathVariable Long id) {
        var todo = todoService.getTodo (id);

        return ResponseEntity.ok(todo);
    }

    @PutMapping("update/{id}")
    // Method level authorization
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        TodoDto todo = todoService.updateTodo(id, todoDto);

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("delete/{id}")
    // Method level authorization
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTodo (@PathVariable Long id) {
        todoService.deleteTodo (id);

        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PatchMapping("complete/{id}")
    // Method level authorization
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDto> completeTodo (@PathVariable Long id) {
        TodoDto todo = todoService.completeTodo (id);

        return ResponseEntity.ok(todo);
    }

    @PatchMapping("uncomplete/{id}")
    // Method level authorization
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDto> uncompleteTodo(@PathVariable Long id) {
        TodoDto todo = todoService.uncompleteTodo(id);

        return ResponseEntity.ok(todo);
    }
}
