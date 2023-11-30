package com.todo_management_system.service;

public interface TaskService {
    void addTask(Long userId, Long taskId);
    void deleteTask(Long userId, Long taskId);
}
