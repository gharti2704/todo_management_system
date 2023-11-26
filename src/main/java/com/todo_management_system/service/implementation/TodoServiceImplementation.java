package com.todo_management_system.service.implementation;

import com.todo_management_system.dto.TodoDto;
import com.todo_management_system.entity.Todo;
import com.todo_management_system.repository.TodoRepository;
import com.todo_management_system.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImplementation implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
//        Todo todo = new Todo();
//        todo.setTitle(todo.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());
        Todo todo = modelMapper.map(todoDto, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDto.class);


    }
}
