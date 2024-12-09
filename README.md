# API 명세서
| **HTTP Method** | **Endpoint**         | **Description**             | **Request Parameters / Body**                                                                                          | **Response**                                        | **Status Code**    |
|------------------|----------------------|-----------------------------|------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|--------------------|
| POST             | `/todo`             | 일정 생성                   | **Body**: `TodoRequestDto`                                         | `TodoResponseDto`                                  | 201 Created        |
| GET              | `/todo`             | 전체 일정 조회              | **Query Params (optional)**:<br>- `updatedTime` (LocalDateTime)<br>- `user` (String)                                    | `List<TodoResponseDto>`                            | 200 OK             |
| GET              | `/todo/{id}`        | 선택 일정 조회              | **Path Variable**:<br>- `id` (Long)                                                                                     | `TodoResponseDto`                                  | 200 OK             |

## Request Body (예시)

`POST /todo` : 일정 생성
```json
{
    "todo": "누워서 숨쉬기",
    "user": "park",
    "password": "0000"
}
```

`GET /todo` : 전체 일정 조회

`GET /todo/1` : 선택 일정 조회
