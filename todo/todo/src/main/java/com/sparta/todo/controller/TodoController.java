package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<TodoResponseDto> saveTodo(@RequestBody TodoRequestDto requestDto) {
        return new ResponseEntity<>(todoService.saveTodo(requestDto), HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public List<TodoResponseDto> getAllTodos(
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate updatedDate,
            @RequestParam(required = false) String user
    ) {
        LocalDateTime updatedTime = updatedDate != null ? updatedDate.atStartOfDay() : null;
        return todoService.getAllTodos(updatedTime, user);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public TodoResponseDto getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    // 선택 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto requestDto
    ) {
        return new ResponseEntity<>(todoService.updateTodo(id, requestDto), HttpStatus.OK);
    }

    // 선택 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto requestDto
    ) {
        todoService.deleteTodo(id, requestDto.getPassword());
        return ResponseEntity.ok().build();
    }
}
