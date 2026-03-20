drop table if exists messages cascade;
drop table if exists homeworks cascade;
drop table if exists materials cascade;
drop table if exists lessons cascade;
drop table if exists group_students cascade;
drop table if exists groups cascade;
drop table if exists courses cascade;
drop table if exists teachers cascade;
drop table if exists students cascade;
drop table if exists users cascade;

create table users (
    id bigserial,
    name text,
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

create table messages (
    id bigserial,
    student_id bigint references students (user_id) on delete cascade,
    teacher_id bigint references teachers (user_id) on delete cascade,
    text text,
    message_direction text,
    message_time timestamp with time zone default current_timestamp,
    primary key (id)
);