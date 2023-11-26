package com.todo_management_system.contoller;

import com.todo_management_system.dto.TodoDto;
import com.todo_management_system.service.TodoService;
import lombok.AllArgsConstructor;
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

        return ResponseEntity.ok(todo);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        var todos = todoService.getAllTodos();

        return ResponseEntity.ok(todos);
    }
}
