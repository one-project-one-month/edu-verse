USE EduVerse_db;

## adminRole table insertion
INSERT INTO admin_role (id, code, role_name) VALUES (1, '1111', 'ROLE_ADMIN');
INSERT INTO admin_role (id, code, role_name) VALUES (2, '1222', 'ROLE_INSTRUCTOR');
INSERT INTO admin_role (id, code, role_name) VALUES (4, '2333', 'ROLE_MANAGER');
INSERT INTO admin_role (id, code, role_name) VALUES (5, '3444', 'ROLE_ADMIN');
SELECT * FROM admin_role;

## Admin Table insertion
INSERT INTO admin (id, user_name, password, email, phone_number, status, role_id) VALUES (1, 'admin', 'password', 'admin@gmail.com', '09283473722', 1, 1);
INSERT INTO admin (id, user_name, password, email, phone_number, status, role_id) VALUES (2, 'admin2', 'password', 'admin2@gmail.com', '0947264822', 1, 1);
INSERT INTO admin (id, user_name, password, email, phone_number, status, role_id) VALUES (3, 'admin3', 'password', 'admin3@gmail.com', '094720822', 1, 1);
SELECT * FROM admin;

# User table insertion
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address) VALUES ('John', 'john@gmail.com', 'JohnYone12@', '099 999 989', 21, '2002-12-02', 'Male', 'Yangon');
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address) VALUES ('Alice', 'alice@example.com', 'AlicePass123', '123 456 789', 25, '1999-08-15', 'Female', 'New York');
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address) VALUES ('Bob', 'bob@example.com', 'BobPass456', '987 654 321', 30, '1994-04-20', 'Male', 'London');
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address) VALUES ('Emma', 'emma@example.com', 'EmmaPass789', '456 789 012', 28, '1996-11-10', 'Female', 'Los Angeles');
SELECT * FROM user;

## pathway
INSERT INTO pathway (name, description) VALUES ('Software Development', 'Pathway focusing on software development skills');
INSERT INTO pathway (name, description) VALUES ('Data Science', 'Pathway focusing on data science and analytics');
INSERT INTO pathway (name, description) VALUES ('Cybersecurity', 'Pathway focusing on cybersecurity skills');
INSERT INTO pathway (name, description) VALUES ('Cloud Computing', 'Pathway focusing on cloud computing technologies');
SELECT * FROM pathway;

## category
INSERT INTO category (name, pathway_id) VALUES ('ProgrammingLanguages', 1);
INSERT INTO category (name, pathway_id) VALUES ('Web Development', 1);
INSERT INTO category (name, pathway_id) VALUES ('Machine Learning', 2);
INSERT INTO category (name, pathway_id) VALUES (' (AWS)', 4);
INSERT INTO category (name, pathway_id) VALUES ('Microsoft Azure', 4);
SELECT * FROM category;

## Course
INSERT INTO course (name, level, duration, short_description, long_description, status, admin_id) VALUES ('CPP Basic', 'Basic', '12', 'Basic for CPP', 'CPP ', TRUE, 1);
INSERT INTO course (name, level, duration, short_description, long_description, status, admin_id) VALUES ('Java Fundamentals', 'Basic', '10', 'Java basics', 'Java programming fundamentals', TRUE, 2);
INSERT INTO course (name, level, duration, short_description, long_description, status, admin_id) VALUES ('Python Intermediate', 'Intermediate', '15', 'Intermediate Python', 'Advanced Python concepts', TRUE, 3);
INSERT INTO course (name, level, duration, short_description, long_description, status, admin_id) VALUES ('MachineLearning', 'Intermediate', '20', 'Intro to ML', 'Machine Learning fundamentals', TRUE, 2);
SELECT * FROM course;

# Course Category
INSERT INTO course_category (category_id, course_id) VALUES (1, 1);
INSERT INTO course_category (category_id, course_id) VALUES (2, 1);
INSERT INTO course_category (category_id, course_id) VALUES (3, 2);
INSERT INTO course_category (category_id, course_id) VALUES (1, 4);
SELECT * FROM course_category;

# Module
INSERT INTO module (name, content, duration, course_id) VALUES ('Chapter 1', 'Intro to variable', '12min', 1);
INSERT INTO module (name, content, duration, course_id) VALUES ('Chapter 2', 'Data types and operators', '15min', 1);
INSERT INTO module (name, content, duration, course_id) VALUES ('Chapter 3', 'Control flow statements', '20min', 1);
INSERT INTO module (name, content, duration, course_id) VALUES ('Chapter 4', 'Arrays and Strings', '18min', 4);
INSERT INTO module (name, content, duration, course_id) VALUES ('Chapter 5', 'Functions and Scope', '22min', 1);
SELECT * FROM module;

# user_course
INSERT INTO user_course (created_at, user_id, course_id) VALUES ('2024-05-12', 1, 1);
INSERT INTO user_course (created_at, user_id, course_id) VALUES ('2024-05-13', 2, 2);
INSERT INTO user_course (created_at, user_id, course_id) VALUES ('2024-05-14', 3, 1);
INSERT INTO user_course (created_at, user_id, course_id) VALUES ('2024-05-15', 1, 4);
INSERT INTO user_course (created_at, user_id, course_id) VALUES ('2024-05-16', 2, 3);
SELECT * FROM user_course;

# Course_Details
INSERT INTO course_details (title, content, course_id, admin_id) VALUES ('Introduction', 'Welcome to the course.', 1, 1);
INSERT INTO course_details (title, content, course_id, admin_id) VALUES ('Chapter 1', 'Overview of the topic.', 1, 2);
INSERT INTO course_details (title, content, course_id, admin_id) VALUES ('Chapter 2', 'In-depth study of concepts.', 2, 1);
INSERT INTO course_details (title, content, course_id, admin_id) VALUES ('Chapter 3', 'Advanced topics covered.', 2, 2);
INSERT INTO course_details (title, content, course_id, admin_id) VALUES ('Conclusion', 'Summary and final remarks.', 4, 1);
SELECT * FROM course_details;

# announcement
INSERT INTO announcement (title, content, noti_type, admin_id, course_id) VALUES ('System level update', 'We will update the system in 10 hours', 'Admin', 1, 1);
INSERT INTO announcement (title, content, noti_type, admin_id, course_id) VALUES ('New Course Added', 'We have added a new course on Advanced JavaScript.', 'Admin', 1, 2);
INSERT INTO announcement (title, content, noti_type, admin_id, course_id) VALUES ('Upcoming Maintenance', 'Scheduled maintenance will take place this weekend. Expect some downtime.', 'Admin', 2, 4);
INSERT INTO announcement (title, content, noti_type, admin_id, course_id) VALUES ('Holiday Closure', 'Please note that our offices will be closed next Monday for the holiday.', 'Admin', 2, 4);
INSERT INTO announcement (title, content, noti_type, admin_id, course_id) VALUES ('Exam Schedule Reminder', 'Do not forget, the midterm exams are scheduled for next week. Be prepared!', 'Admin', 1, 1);
SELECT * FROM announcement;
