insert into users (id, first_name, last_name, email, password, phone, role, register_date) values
(1, 'TestFirstName1', 'TestLastName1', 'TestEmail1', 'TestPassword1', 'TestPhone1', 'ADMIN', current_timestamp),
(2, 'TestFirstName2', 'TestLastName2', 'TestEmail2', 'TestPassword2', 'TestPhone2', 'STUDENT', current_timestamp),
(3, 'TestFirstName3', 'TestLastName3', 'TestEmail3', 'TestPassword3', 'TestPhone3', 'STUDENT', current_timestamp),
(4, 'TestFirstName4', 'TestLastName4', 'TestEmail4', 'TestPassword4', 'TestPhone4', 'STUDENT', current_timestamp),
(5, 'TestFirstName5', 'TestLastName5', 'TestEmail5', 'TestPassword5', 'TestPhone5', 'TEACHER', current_timestamp),
(6, 'TestFirstName6', 'TestLastName6', 'TestEmail6', 'TestPassword6', 'TestPhone6', 'TEACHER', current_timestamp),
(7, 'TestFirstName7', 'TestLastName7', 'TestEmail7', 'TestPassword7', 'TestPhone7', 'TEACHER', current_timestamp);

insert into students (user_id, enrollment_date) values
(2, current_timestamp - interval '5 days'),
(3, current_timestamp - interval '7 days'),
(4, current_timestamp - interval '3 days');

insert into courses (id, title, description, teacher_id) values
(1, 'Test Course 1', 'Test Course Description 1', 5),
(2, 'Test Course 2', 'Test Course Description 2', 6),
(3, 'Test Course 3', 'Test Course Description 3', 7);

insert into groups (id, code, name, course_id, start_date, end_date) values
(1, 'G1', 'Test Group 1', 1, current_timestamp, current_timestamp + interval '90 days'),
(2, 'G2', 'Test Group 2', 1, current_timestamp, current_timestamp + interval '95 days'),
(3, 'G3', 'Test Group 3', 2, current_timestamp, current_timestamp + interval '50 days');

insert into group_students (group_id, student_id) values
(1, 2),
(1, 3),
(1, 4),
(2, 2),
(2, 3),
(3, 2);