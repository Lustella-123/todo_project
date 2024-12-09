package com.sparta.todo.repository;

import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);

    List<TodoResponseDto> getAllTodos(LocalDateTime updatedTime, String user);

    Optional<Todo> getTodoById(Long id);
}
