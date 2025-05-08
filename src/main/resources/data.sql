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