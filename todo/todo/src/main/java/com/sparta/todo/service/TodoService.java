package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto requestDto);

    List<TodoResponseDto> getAllTodos(LocalDateTime updatedTime, String user);

    TodoResponseDto getTodoById(Long id);

    TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto);

    void deleteTodo(Long id, String password);
}
