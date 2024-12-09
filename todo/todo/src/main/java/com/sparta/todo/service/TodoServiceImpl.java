package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    //속
    private final TodoRepository todoRepository;

    //생
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //기

    // 일정 생성
    @Override
    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getTodo(), requestDto.getUser(), requestDto.getPassword());
        return todoRepository.saveTodo(todo);
    }

    // 전체 일정 조회
    @Override
    public List<TodoResponseDto> getAllTodos(LocalDateTime updatedTime, String user) {
        return todoRepository.getAllTodos(updatedTime,user);
    }

    // 선택 일정 조회
    @Override
    public TodoResponseDto getTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.getTodoById(id);

        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist");
        }

        return new TodoResponseDto(optionalTodo.get());
    }
}
