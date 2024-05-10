drop database if exists EduVerse_db;

create database if not exists EduVerse_db;

use EduVerse_db;

create table if not exists admin_role(
	id int primary key auto_increment,
	code char(4) not null check(char_length(code) = 4),
	role_name varchar(20)
);

create table if not exists admin(
	id int primary key auto_increment,
	user_name varchar(20) not null,
	password varchar(68) not null,
	email varchar(50) not null unique,
	phone_number varchar(12),
	status boolean,
	role_id int,
	foreign key (role_id) references admin_role (id)
);

create table if not exists user(
	id int primary key auto_increment,
	name varchar(20) not null,
	email varchar(50) not null,
	password varchar(68) not null,
	phone_number varchar(12),
	age int check(age > 0),
	dob date ,
	gender enum('Male','Female'),
	address varchar(80)
);

create table if not exists pathway(
	id int primary key auto_increment,
	name varchar(20) not null,
	description varchar(200) 
);

create table if not exists category(
	id int primary key auto_increment,
	name varchar(20),
	pathway_id int,
	foreign key (pathway_id) references pathway(id)
);

create table if not exists course(
	id int primary key auto_increment,
	name varchar(20) not null,
	level enum('Basic','Intermediate','Advance') not null,
	duration varchar(10),
	short_description varchar(100),
	long_description varchar(500),
	created_at date,
	status boolean,
	admin_id int,
	foreign key (admin_id) references admin(id)
);

create table if not exists course_category(
	id int primary key auto_increment,
	category_id int,
	course_id int,
	foreign key (category_id) references category(id),
	foreign key (course_id) references course(id)
);


create table if not exists module(
	id int primary key auto_increment,
	name varchar(25) not null,
	content varchar(500),
	duration varchar(20),
	course_id int,
	foreign key (course_id) references course(id)
);

create table if not exists user_course(
	id int primary key auto_increment,
	created_at date,
	user_id int,
	course_id int,
	foreign key (user_id) references user(id),
	foreign key (course_id) references course(id)
);


create table if not exists course_details(
	id int primary key auto_increment,
	title varchar(20) not null,
	content varchar(500),
	course_id int,
	admin_id int,
	foreign key (course_id) references course(id),
	foreign key (admin_id) references admin(id)
);

create table if not exists announcement(
	id int primary key auto_increment,
	title varchar(30) not null,
	content varchar(100),
	created_at date,
	noti_type enum('Admin','Instructor'),
	admin_id int,
	course_id int,
	foreign key (admin_id) references admin(id),
	foreign key (course_id) references course(id)
);

COMMIT;


















