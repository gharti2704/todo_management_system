package com.todo_management_system.service.implementation;

import com.todo_management_system.entity.Todo;
import com.todo_management_system.entity.User;
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
       User user =  userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
       Todo todo = todoRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Todo not found"));
       todo.setUser(user);
       user.getTodos().add(todo);
       userRepository.save(user);

     user.getTodos().stream().filter(todo1 -> todo1.getId().equals(taskId)).findFirst().orElseThrow(() -> new RuntimeException("Todo not found"));

    }

    @Override
    public void removeTask(Long userId, Long taskId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Todo todo = todoRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Todo not found"));

        user.getTodos().remove(todo);
        userRepository.save(user);
        todo.setUser(null);
        todoRepository.save(todo);
    }
}
