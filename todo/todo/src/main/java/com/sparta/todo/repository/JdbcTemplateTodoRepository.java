package com.sparta.todo.repository;

import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateTodoRepository implements TodoRepository{
    //속
    private final JdbcTemplate jdbcTemplate;

    //생
    public JdbcTemplateTodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //기

    // 일정 생성
    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", todo.getTodo());
        parameters.put("user", todo.getUser());
        parameters.put("password", todo.getPassword());
        parameters.put("createdTime", todo.getCreatedTime());
        parameters.put("updatedTime", todo.getUpdatedTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new TodoResponseDto(key.longValue(), todo);
    }

    // 전체 일정 조회
    @Override
    public List<TodoResponseDto> getAllTodos(LocalDateTime updatedTime, String user) {
        return jdbcTemplate.query("SELECT * FROM todo", todoRowMapper());
    }

    private RowMapper<TodoResponseDto> todoRowMapper() {
        return new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getTimestamp("createdTime").toLocalDateTime(),
                        rs.getTimestamp("updatedTime").toLocalDateTime()
                );
            }
        };
    }

    // 선택 일정 조회
    @Override
    public Optional<Todo> getTodoById(Long id) {
        List<Todo> result = jdbcTemplate.query("SELECT * FROM todo WHERE id = ?", todoRowMapperV2(), id);
        return result.stream().findAny();
    }

    private RowMapper<Todo> todoRowMapperV2() {
        return new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Todo(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getTimestamp("createdTime").toLocalDateTime(),
                        rs.getTimestamp("updatedTime").toLocalDateTime()
                );
            }
        };
    }
}