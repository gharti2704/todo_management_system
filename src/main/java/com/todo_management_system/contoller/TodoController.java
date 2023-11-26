package com.todo_management_system.contoller;

import com.todo_management_system.dto.TodoDto;
import com.todo_management_system.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
