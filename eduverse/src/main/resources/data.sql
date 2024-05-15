use EduVerse_db;
## adminROle table insertion
INSERT INTO `admin_role`(`id`, `code`, `role_name`) VALUES ('1','1111','ROLE_ADMIN');
INSERT INTO `admin_role`(`id`, `code`, `role_name`) VALUES ('2','1222','ROLE_INSTRUCTOR');
INSERT INTO `admin_role`(id, code, role_name) VALUES ('3', '2333', 'ROLE_MANAGER');

##Admin Table insertion
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('1','admin','password','admin@gmail.com','09283473722','1','1');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('2','admin2','password','admin2@gmail.com','0947264822','1','2');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('3','admin3','password','admin3@gmail.com','094720822','1','3');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('4','admin4','password','admin4@gmail.com','094720822','1','2');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('5','admin5','password','admin5@gmail.com','094720822','1','2');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('6','admin6','password','admin6@gmail.com','094720822','1','3');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('7','admin7','password','admin7@gmail.com','094720822','1','2');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('8','admin8','password','admin8@gmail.com','094720822','1','2');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('9','admin9','password','admin9@gmail.com','094720822','1','3');
INSERT INTO `admin`(`id`, `user_name`, `password`, `email`, `phone_number`, `status`, `role_id`) VALUES ('10','admin10','password','admin10@gmail.com','094720822','1','2');


#User table insertion
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address) values ("John","john@gmail.com", "JohnYone12@", "099 999 989", "21","2002-12-02", "Male", "Yangon");
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address)VALUES ("Alice", "alice@example.com", "AlicePass123", "123 456 789", 25, "1999-08-15", "Female", "New York");
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address)VALUES ("Bob", "bob@example.com", "BobPass456", "987 654 321", 30, "1994-04-20", "Male", "London");
INSERT INTO user (name, email, password, phone_number, age, dob, gender, address)VALUES ("Emma", "emma@example.com", "EmmaPass789", "456 789 012", 28, "1996-11-10", "Female", "Los Angeles");


## pathway
INSERT INTO pathway (id,name, description) VALUES (1,'Software Development', 'Pathway focusing on software development skills');
INSERT INTO pathway (id,name, description) VALUES (2,'Data Science', 'Pathway focusing on data science and analytics');
INSERT INTO pathway (id,name, description) VALUES (3,'Cybersecurity', 'Pathway focusing on cybersecurity skills');
INSERT INTO pathway (id,name, description) VALUES (4,'Cloud Computing', 'Pathway focusing on cloud computing technologies');


##category
INSERT INTO category (id,name, pathway_id) VALUES (1,'ProgrammingLanguages', 1);
INSERT INTO category (id,name, pathway_id) VALUES (2,'Web Development', 2);
INSERT INTO category (id,name, pathway_id) VALUES (3,'Machine Learning', 3);
INSERT INTO category (id,name, pathway_id) VALUES (4,'(AWS)', 4);
INSERT INTO category (id,name, pathway_id) VALUES (5,'Microsoft Azure', 4);

## Course
insert into course (id,name, level, duration, short_description, long_description, status, admin_id) values (1,"CPP Basic", "Basic", "12", "Basic for CPP","CPP ",true, "1");
INSERT INTO course (id,name, level, duration, short_description, long_description, status, admin_id)VALUES (2,"Java Fundamentals", "Basic", "10", "Java basics", "Java programming fundamentals", true, "2");
INSERT INTO course (id,name, level, duration, short_description, long_description, status, admin_id)VALUES (3,"Python Intermediate", "Intermediate", "15", "Intermediate Python", "Advanced Python concepts", true, "3");
INSERT INTO course (id,name, level, duration, short_description, long_description, status, admin_id)VALUES (4,"MachineLearning", "Intermediate", "20", "Intro to ML", "Machine Learning fundamentals", true, "2");

#Course Category
INSERT INTO course_category (category_id, course_id) VALUES (1, 1);
INSERT INTO course_category (category_id, course_id) VALUES (2, 2);
INSERT INTO course_category (category_id, course_id) VALUES (3, 3);
INSERT INTO course_category (category_id, course_id) VALUES (4, 4);

# Module
INSERT INTO  module (id,name, content, duration, course_id) values (1,"Chapter 1", "Intro to varibale","12min", "1");
INSERT INTO module (id,name, content, duration, course_id)VALUES (2,"Chapter 2", "Data types and operators", "15min", "2");
INSERT INTO module (id,name, content, duration, course_id)VALUES (3,"Chapter 3", "Control flow statements", "20min", "3");
INSERT INTO module (id,name, content, duration, course_id)VALUES (4,"Chapter 4", "Arrays and Strings", "18min", "4");

#user_course
INSERT INTO user_course (id,created_at, user_id, course_id) VALUES (1,'2024-05-12', 1, 1);
INSERT INTO user_course (id,created_at, user_id, course_id) VALUES (2,'2024-05-13', 2, 2);
INSERT INTO user_course (id,created_at, user_id, course_id) VALUES (3,'2024-05-14', 3, 2);
INSERT INTO user_course (id,created_at, user_id, course_id) VALUES (4,'2024-05-15', 1, 4);
INSERT INTO user_course (id,created_at, user_id, course_id) VALUES (5,'2024-05-16', 2, 3);

#Course_Details
INSERT INTO course_details (id,title, content, course_id, admin_id) VALUES (1,"Introduction", "Welcome to the course.", 1, 1);
INSERT INTO course_details (id,title, content, course_id, admin_id) VALUES (2,"Chapter 1", "Overview of the topic.", 3, 2);
INSERT INTO course_details (id,title, content, course_id, admin_id) VALUES (3,"Chapter 2", "In-depth study of concepts.", 2, 3);
INSERT INTO course_details (id,title, content, course_id, admin_id) VALUES (4,"Chapter 3", "Advanced topics covered.", 2, 2);
INSERT INTO course_details (id,title, content, course_id, admin_id) VALUES (5,"Conclusion", "Summary and final remarks.", 4, 2);

# announcement
INSERT INTO announcement (id,title, content, noti_type, admin_id, course_id)VALUES (1,"System level update", "We will update the system in 10 hours","Admin", "1","1");
INSERT INTO announcement (id,title, content, noti_type, admin_id, course_id)VALUES (2,"New Course Added", "We have added a new course on Advanced JavaScript.", "Admin", "2", "2");
INSERT INTO announcement (id,title, content, noti_type, admin_id, course_id)VALUES (3,"Upcoming Maintenance", "Scheduled maintenance will take place this weekend. Expect some downtime.", "Admin", "3", "3");



