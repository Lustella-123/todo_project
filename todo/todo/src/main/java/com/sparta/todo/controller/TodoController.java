package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    //속
    private final TodoService todoService;

    //생
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //기

    // 일정 생성
    @PostMapping
    public TodoResponseDto saveTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.saveTodo(requestDto);
    }

    // 전체 일정 조회
    @GetMapping
    public List<TodoResponseDto> getAllTodos(
            @RequestParam(required = false) LocalDateTime updatedTime,
            @RequestParam(required = false) String user
            ) {
        return todoService.getAllTodos(updatedTime, user);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public TodoResponseDto getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }
}
