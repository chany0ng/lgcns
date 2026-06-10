/*
SET FOREIGN_KEY_CHECKS = 0;
truncate table Post;
truncate table User;
SET FOREIGN_KEY_CHECKS = 1;

SET time_zone = 'Asia/Seoul';

insert into User(nickname, email, telno, bloodType)
values ('hongx', 'hongx@gmail.com', '010-1111-2222', 'A'),
       ('kimx', 'kimx@gmail.com', '010-2222-3333', 'B');


INSERT INTO Post(hit, id, createdAt, updatedAt, writer, title)
VALUES (0, 1, '2025-07-29 07:21:24', '2025-07-29 07:21:24', 1, 'Title01'),
    ...

 */
