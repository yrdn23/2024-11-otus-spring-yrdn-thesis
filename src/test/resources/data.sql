insert into users (id, first_name, last_name, email, password, phone, role, register_date) values
(1, 'FirstName1', 'LastName1', 'Email1', 'Password1', 'Phone1', 'ADMIN', current_timestamp),
(2, 'FirstName2', 'LastName2', 'Email2', 'Password2', 'Phone2', 'STUDENT', current_timestamp),
(3, 'FirstName3', 'LastName3', 'Email3', 'Password3', 'Phone3', 'STUDENT', current_timestamp),
(4, 'FirstName4', 'LastName4', 'Email4', 'Password4', 'Phone4', 'STUDENT', current_timestamp),
(5, 'FirstName5', 'LastName5', 'Email5', 'Password5', 'Phone5', 'TEACHER', current_timestamp),
(6, 'FirstName6', 'LastName6', 'Email6', 'Password6', 'Phone6', 'TEACHER', current_timestamp),
(7, 'FirstName7', 'LastName7', 'Email7', 'Password7', 'Phone7', 'TEACHER', current_timestamp);

insert into students (user_id, enrollment_date) values
(2, current_timestamp - interval '5 days'),
(3, current_timestamp - interval '7 days'),
(4, current_timestamp - interval '3 days');

insert into teachers (user_id, hire_date, specialization) values
(5, current_timestamp - interval '5 days', 'Specialization 5'),
(6, current_timestamp - interval '7 days', 'Specialization 6'),
(7, current_timestamp - interval '3 days', 'Specialization 7');

insert into courses (id, title, description, teacher_id) values
(1, 'Course 1', 'Course Description 1', 7),
(2, 'Course 2', 'Course Description 2', 6),
(3, 'Course 3', 'Course Description 3', 5);

insert into groups (id, code, name, course_id, start_date, end_date) values
(1, 'G1', 'Group 1', 1, current_timestamp, current_timestamp + interval '90 days'),
(2, 'G2', 'Group 2', 1, current_timestamp, current_timestamp + interval '95 days'),
(3, 'G3', 'Group 3', 2, current_timestamp, current_timestamp + interval '50 days');

insert into group_students (group_id, student_id) values
(1, 2),
(1, 3),
(1, 4),
(2, 2),
(2, 3),
(3, 2);

insert into lessons (id, group_id, teacher_id, title, description, start_date, duration, deadline_date) values
( 1, 1, 7, 'Lesson 1-1', 'Description 1', current_timestamp, 90, current_timestamp + interval '10 days'),
( 2, 1, 6, 'Lesson 1-2', 'Description 2', current_timestamp, 90, current_timestamp + interval '10 days'),
( 3, 1, 5, 'Lesson 1-3', 'Description 3', current_timestamp, 90, current_timestamp + interval '10 days'),
( 4, 1, 7, 'Lesson 1-4', 'Description 4', current_timestamp, 90, current_timestamp + interval '10 days'),
( 5, 1, 6, 'Lesson 1-5', 'Description 5', current_timestamp, 90, current_timestamp + interval '10 days'),
( 6, 1, 5, 'Lesson 1-6', 'Description 6', current_timestamp, 90, current_timestamp + interval '10 days'),
( 7, 2, 7, 'Lesson 2-1', 'Description 7', current_timestamp, 90, current_timestamp + interval '10 days'),
( 8, 2, 6, 'Lesson 2-2', 'Description 8', current_timestamp, 90, current_timestamp + interval '10 days'),
( 9, 2, 5, 'Lesson 2-3', 'Description 9', current_timestamp, 90, current_timestamp + interval '10 days'),
(10, 3, 7, 'Lesson 3-1', 'Description A', current_timestamp, 90, current_timestamp + interval '10 days'),
(11, 3, 6, 'Lesson 3-2', 'Description B', current_timestamp, 90, current_timestamp + interval '10 days'),
(12, 3, 5, 'Lesson 3-3', 'Description C', current_timestamp, 90, current_timestamp + interval '10 days');

insert into homeworks (id, lesson_id, student_id, answer, submit_date, comment, score, status) values
( 1,  1, 2, 'Answer 2-1', current_timestamp, 'Comment 2-1', 1, 'NOT_SUBMITTED'),
( 2,  2, 2, 'Answer 2-2', current_timestamp, 'Comment 2-2', 1, 'SUBMITTED'),
( 3,  3, 2, 'Answer 2-3', current_timestamp, 'Comment 2-3', 1, 'NEED_REVISION'),
( 4,  4, 2, 'Answer 2-4', current_timestamp, 'Comment 2-4', 1, 'ACCEPTED'),
( 5,  5, 2, 'Answer 2-5', current_timestamp, 'Comment 2-5', 1, 'NOT_SUBMITTED'),
( 6,  6, 2, 'Answer 2-6', current_timestamp, 'Comment 2-6', 1, 'SUBMITTED'),
( 7,  7, 2, 'Answer 2-7', current_timestamp, 'Comment 2-7', 1, 'NEED_REVISION'),
( 8,  8, 2, 'Answer 2-8', current_timestamp, 'Comment 2-8', 1, 'ACCEPTED'),
( 9,  9, 2, 'Answer 2-9', current_timestamp, 'Comment 2-9', 1, 'NOT_SUBMITTED'),
(10, 10, 2, 'Answer 2-10', current_timestamp, 'Comment 2-10', 1, 'SUBMITTED'),
(11, 11, 2, 'Answer 2-11', current_timestamp, 'Comment 2-11', 1, 'NEED_REVISION'),
(12, 12, 2, 'Answer 2-12', current_timestamp, 'Comment 2-12', 1, 'ACCEPTED'),
(13,  1, 3, 'Answer 3-1', current_timestamp, 'Comment 3-1', 1, 'NOT_SUBMITTED'),
(14,  2, 3, 'Answer 3-2', current_timestamp, 'Comment 3-2', 1, 'SUBMITTED'),
(15,  3, 3, 'Answer 3-3', current_timestamp, 'Comment 3-3', 1, 'NEED_REVISION'),
(16,  4, 3, 'Answer 3-4', current_timestamp, 'Comment 3-4', 1, 'ACCEPTED'),
(17,  5, 3, 'Answer 3-5', current_timestamp, 'Comment 3-5', 1, 'NOT_SUBMITTED'),
(18,  6, 3, 'Answer 3-6', current_timestamp, 'Comment 3-6', 1, 'SUBMITTED'),
(19,  7, 3, 'Answer 3-7', current_timestamp, 'Comment 3-7', 1, 'NEED_REVISION'),
(20,  8, 3, 'Answer 3-8', current_timestamp, 'Comment 3-8', 1, 'ACCEPTED'),
(21,  9, 3, 'Answer 3-9', current_timestamp, 'Comment 3-9', 1, 'NOT_SUBMITTED'),
(22, 10, 3, 'Answer 3-10', current_timestamp, 'Comment 3-10', 1, 'SUBMITTED'),
(23, 11, 3, 'Answer 3-11', current_timestamp, 'Comment 3-11', 1, 'NEED_REVISION'),
(24, 12, 3, 'Answer 3-12', current_timestamp, 'Comment 3-12', 1, 'ACCEPTED'),
(25,  1, 4, 'Answer 4-1', current_timestamp, 'Comment 4-1', 1, 'NOT_SUBMITTED'),
(26,  2, 4, 'Answer 4-2', current_timestamp, 'Comment 4-2', 1, 'SUBMITTED'),
(27,  3, 4, 'Answer 4-3', current_timestamp, 'Comment 4-3', 1, 'NEED_REVISION'),
(28,  4, 4, 'Answer 4-4', current_timestamp, 'Comment 4-4', 1, 'ACCEPTED'),
(29,  5, 4, 'Answer 4-5', current_timestamp, 'Comment 4-5', 1, 'NOT_SUBMITTED'),
(30,  6, 4, 'Answer 4-6', current_timestamp, 'Comment 4-6', 1, 'SUBMITTED'),
(31,  7, 4, 'Answer 4-7', current_timestamp, 'Comment 4-7', 1, 'NEED_REVISION'),
(32,  8, 4, 'Answer 4-8', current_timestamp, 'Comment 4-8', 1, 'ACCEPTED'),
(33,  9, 4, 'Answer 4-9', current_timestamp, 'Comment 4-9', 1, 'NOT_SUBMITTED'),
(34, 10, 4, 'Answer 4-10', current_timestamp, 'Comment 4-10', 1, 'SUBMITTED'),
(35, 11, 4, 'Answer 4-11', current_timestamp, 'Comment 4-11', 1, 'NEED_REVISION'),
(36, 12, 4, 'Answer 4-12', current_timestamp, 'Comment 4-12', 1, 'ACCEPTED');

insert into messages (id, student_id, teacher_id, text, message_time) values
(1001, 2, 5, 'Message 1', current_timestamp),
(1002, 2, 6, 'Message 2', current_timestamp + interval '1 minute'),
(1003, 2, 5, 'Message 3', current_timestamp + interval '2 minute'),
(1004, 2, 6, 'Message 4', current_timestamp + interval '3 minute'),
(1005, 2, 5, 'Message 5', current_timestamp + interval '4 minute'),
(1006, 2, 7, 'Message 6', current_timestamp + interval '5 minute'),
(1007, 2, 5, 'Message 7', current_timestamp + interval '6 minute');