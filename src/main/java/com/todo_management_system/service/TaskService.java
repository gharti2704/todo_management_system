package com.todo_management_system.service;

public interface TaskService {
    void addTask(Long userId, Long taskId);
    void removeTask(Long userId, Long taskId);
}
