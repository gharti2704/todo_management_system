package com.todo_management_system.contoller;

import com.todo_management_system.dto.TodoDto;
import com.todo_management_system.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @PostMapping("create")
    public ResponseEntity<TodoDto> addTodo (@RequestBody TodoDto todoDto) {
        var todo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        var todos = todoService.getAllTodos();

        return ResponseEntity.ok(todos);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo (@PathVariable Long id) {
        var todo = todoService.getTodo (id);

        return ResponseEntity.ok(todo);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        TodoDto todo = todoService.updateTodo(id, todoDto);

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTodo (@PathVariable Long id) {
        todoService.deleteTodo (id);

        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PutMapping("complete/{id}")
    public ResponseEntity<TodoDto> completeTodo (@PathVariable Long id) {
        TodoDto todo = todoService.completeTodo (id);

        return ResponseEntity.ok(todo);
    }
}
