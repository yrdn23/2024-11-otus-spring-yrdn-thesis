drop table if exists homeworks;
drop table if exists materials;
drop table if exists lessons;
drop table if exists group_students;
drop table if exists groups;
drop table if exists courses;
drop table if exists teachers;
drop table if exists students;
drop table if exists users;

create table users (
    id bigserial,
    first_name text,
    last_name text,
    email text,
    password text,
    phone text,
    register_date timestamp with time zone,
    role text,
    primary key (id)
);

create table students (
    user_id bigint references users (id) on delete cascade,
    enrollment_date timestamp with time zone,
    primary key (user_id)
);

create table teachers (
    user_id bigint references users (id) on delete cascade,
    hire_date timestamp with time zone,
    specialization text,
    primary key (user_id)
);

create table courses (
    id bigserial,
    title text,
    description text,
    teacher_id bigint references teachers (user_id) on delete cascade,
    primary key (id)
);

create table groups (
    id bigserial,
    code text,
    name text,
    course_id bigint references courses (id) on delete cascade,
    start_date timestamp with time zone,
    end_date timestamp with time zone,
    primary key (id)
);

create table group_students (
    group_id bigint references groups (id) on delete cascade,
    student_id bigint references students (user_id) on delete cascade,
    primary key (group_id, student_id)
);

create table lessons (
    id bigserial,
    group_id bigint references groups (id) on delete cascade,
    teacher_id bigint references teachers (user_id) on delete cascade,
    title text,
    description text,
    start_date timestamp with time zone,
    duration int,
    deadline_date timestamp with time zone,
    primary key (id)
);

create table materials (
    id bigserial,
    lesson_id bigint references lessons (id) on delete cascade,
    title text,
    description text,
    url text,
    primary key (id)
);

create table homeworks (
    id bigserial,
    lesson_id bigint references lessons (id) on delete cascade,
    student_id bigint references students (user_id) on delete cascade,
    answer text,
    submit_date timestamp with time zone,
    comment text,
    score int,
    status text,
    primary key (id)
);