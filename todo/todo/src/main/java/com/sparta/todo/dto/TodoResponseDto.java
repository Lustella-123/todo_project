package com.sparta.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String todo;
    private String user;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    public TodoResponseDto(long id, Todo todo) {
        this.id = id;
        this.todo = todo.getTodo();
        this.user = todo.getUser();
        this.password = todo.getPassword();
        this.createdTime = todo.getCreatedTime();
        this.updatedTime = todo.getUpdatedTime();
    }

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.todo = todo.getTodo();
        this.user = todo.getUser();
        this.password = todo.getPassword();
        this.createdTime = todo.getCreatedTime();
        this.updatedTime = todo.getUpdatedTime();
    }
}
