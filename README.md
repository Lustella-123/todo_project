# API 명세서

| **HTTP Method** | **Endpoint**         | **Description**             | **Request Parameters / Body**                                                                                          | **Response**                                        | **Status Code**    |
|------------------|----------------------|-----------------------------|------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|--------------------|
| POST             | `/todo`             | 일정 생성                   | **Body**: `TodoRequestDto`                                         | `TodoResponseDto`                                  | 201 Created        |
| GET              | `/todo`             | 전체 일정 조회              | **Query Params (optional)**:<br>- `updatedDate` (yyyy-MM-dd)<br>- `user` (String)                                     | `List<TodoResponseDto>`                            | 200 OK             |
| GET              | `/todo/{id}`        | 선택 일정 조회              | **Path Variable**:<br>- `id` (Long)                                                                                     | `TodoResponseDto`                                  | 200 OK             |
| PATCH            | `/todo/{id}`        | 선택 일정 수정              | **Path Variable**:<br>- `id` (Long)<br>**Body**: `TodoRequestDto`                                                       | `TodoResponseDto`                                  | 200 OK             |
| DELETE           | `/todo/{id}`        | 선택 일정 삭제              | **Path Variable**:<br>- `id` (Long)<br>**Body**: `TodoRequestDto` (password 필수)                                       | 없음                                               | 200 OK             |

---

## Request Param & Body (예시)

`POST /todo` : 일정 생성
```json
{
    "todo": "누워서 숨쉬기",
    "user": "park",
    "password": "0000"
}
```

`GET /todo` : 전체 일정 조회
```param
/todo?updatedDate=2024-12-01&user=park
```

`GET /todo/{id}` : 선택 일정 조회
```
/todo/1
```

`PATCH /todo/{id}` : 선택 일정 수정
```json
{
    "todo": "책 읽기",
    "user": "park",
    "password": "0000"
}
```

`DELETE /todo/{id}` : 선택 일정 삭제
```json
{
    "password": "0000"
}
```
