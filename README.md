# CH3 일정 관리 앱 Develop
## API
|Methdo|Endpoint|Description|Parameters|Request Body| Response |Status Code|
|:-:|:-:|:-:|:-:|:-:|:--------:|:-:|
|POST|/users/signup|유저 추가|없음|{"username": "testname", "password": "1234", "email": "test@email.com"}|{"id": 1, "username": "testname", "email": "test@email.com"}|201 Created|
|GET|/users/{id}|id를 통한 유저 조회|Path: id|없음|{"username": "testname", "email": "test@email.com"}|200 OK|
|PATCH|/users/{id}|유저 비밀번호 수정|Path: id|{"oldPassword": "1234", "newPassword": "1111"}|{"id": 1, "oldPassword": "1234", "newPassword": "1111"}|200 OK|
|DELETE|/users/{id}|유저 삭제|Path: id|없음|없음|200 OK|
|POST|/schedules|일정 추가|없음|{"todoTitle": "testTitle", "todoContent": "testContent", "username": "testname"}|{"id": 1, "todoTitle": "testTitle", "todoContent": "testContent"}|200 OK|
|GET|/schedules|일정 조회|없음|없음|{"id": 1, "todoTitle": "testTitle", "todoContent": "testContent"}|200 OK|
|GET|/schedules/{id}|id를 통한 일정 조회|Path: id|없음|{"todoTitle": "testTitle", "todoContent": "testContent", "username": "testname"}|200 OK|
|PATCH|/schedules/{id}|일정 수정|Path: id|{"todoTitle": "patchTitle", "todoContent": "patchContent"}|없음|200 OK|
|DELETE|/schedules/{id}|일정 삭제|Path: id|없음|없음|200 OK|
|POST|/users/control/register|회원가입|없음|email:test@email.com, password:1234, username:testname (x-www-form-urlencoded)|"Success"|200 OK|
|POST|/users/control/login|로그인|없음|{"email": "test@email.com", "password": "1234"}|{"id": 1}|200 OK|
|POST|/users/logout|로그아웃|없음|없음|"Logout Success"|200 OK|

## ERD
![image](https://github.com/user-attachments/assets/d59eaa80-e600-4d3f-abf7-e9e34f41be96)

## SQL
1. schedule
```sql
create table schedule
   (
   created_date  datetime(6)  null,
   id            bigint auto_increment
   primary key,
   modified_date datetime(6)  null,
   user_id       bigint       not null,
   todo_content  longtext     not null,
   todo_title    varchar(255) not null,
   constraint FKa50n59y1j4a6qwa42p8jiguds
   foreign key (user_id) references user (id)
   );
```
2. user
```sql
create table user
   (
   created_date  datetime(6)  null,
   id            bigint auto_increment
   primary key,
   modified_date datetime(6)  null,
   email         varchar(255) not null,
   username      varchar(255) not null
   );
```
