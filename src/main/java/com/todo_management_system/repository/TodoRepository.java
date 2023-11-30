package com.todo_management_system.repository;

import com.todo_management_system.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.todo_management_system.dto.TodoDto;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Fetch all todos which include user's name and email form users table
    @Query("SELECT (t.id, t.title, t.description, t.completed, u.name) FROM Todo t JOIN t.user u")
    List<TodoDto> fetchAllTodos();

}
