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
public class TodoServiceImpl implements TodoService {
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
        return todoRepository.getAllTodos(updatedTime, user);
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

    // 선택 일정 수정
    @Override
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {

        if (requestDto.getTodo() == null || requestDto.getPassword() == null || requestDto.getUser() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please give me good request.");
        }

        if (!requestDto.getPassword().equals(getTodoById(id).getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password.");
        }

        todoRepository.updateTodo(id, requestDto.getUser(), requestDto.getTodo());

        return new TodoResponseDto(todoRepository.getTodoById(id).get());
    }

    // 선택 일정 삭제
    @Override
    public void deleteTodo(Long id, String password) {
        // ID 존재 여부 확인
        Optional<Todo> optionalTodo = todoRepository.getTodoById(id);
        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }

        Todo todo = optionalTodo.get();

        // 비밀번호 검증
        if (!todo.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password");
        }

        // 삭제 수행
        boolean deletedRow = todoRepository.deleteTodo(id);
        if (!deletedRow) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete todo");
        }
    }

}
