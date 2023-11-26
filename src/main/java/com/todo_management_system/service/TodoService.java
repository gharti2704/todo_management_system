package com.todo_management_system.service;

import com.todo_management_system.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    List<TodoDto> getAllTodos();
    TodoDto getTodo(Long id);
    TodoDto updateTodo(Long id, TodoDto todoDto);
    void deleteTodo(Long id);
    TodoDto completeTodo (Long id);
    TodoDto uncompleteTodo (Long id);
}
