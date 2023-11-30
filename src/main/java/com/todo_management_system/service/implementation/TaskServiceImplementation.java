package com.todo_management_system.service.implementation;

import com.todo_management_system.repository.TodoRepository;
import com.todo_management_system.repository.UserRepository;
import com.todo_management_system.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImplementation implements TaskService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Override
    public void addTask(Long userId, Long taskId) {
       var user =  userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
       var todo = todoRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Todo not found"));

       user.getTodos().add(todo);
       userRepository.save(user);
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {

    }
}
