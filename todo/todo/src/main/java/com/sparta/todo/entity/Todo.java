package com.sparta.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Todo {

    private Long id;
    private String todo;
    private String user;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    public Todo(String todo, String user, String password) {
        this.todo = todo;
        this.user = user;
        this.password = password;
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }
}
