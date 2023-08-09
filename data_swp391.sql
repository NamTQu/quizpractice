--  drop database iquiz;
--  create database iquiz;

use iquiz;

INSERT INTO `iquiz`.`role`
(
  `name`,
  `status`)
VALUES
  ('ROLE_ADMIN',1),
  ('ROLE_EXPERT',1),
  ('ROLE_SALE',1),
  ('ROLE_MARKETING',1),
  ('ROLE_CUSTOMER',1);
INSERT INTO `iquiz`.`slider`
(
  `title`,
  `image`,
  `back_link`,
  `status`)
VALUES
('Become a Full Stack Software Engineer with The Hottest Technologies Right Now', 'slider1.png', '/subjects/subject-detail?id=5', 1),
('Anyone can get 6.5 IELTS!', 'slider2.png', '/subjects/subject-detail?id=3', 1),
('First Step to become a BA - Statistics and Probability', 'slider3.png', '/subjects/subject-detail?id=2', 1);

INSERT INTO `iquiz`.`lessontype`
(`name`,
 `status`)
VALUES
  ('quiz', 1),
  ('lession', 1);
INSERT INTO `iquiz`.`lessontype`
(`name`,
 `status`)
VALUES
  ('Subject Topic', 1);

INSERT INTO `iquiz`.`dimension`
(
  `description`,
  `name`,
  `status`,
  `type`)
VALUES
  ('The distance between two points in space', 'Business', 1, 'domain'),
  ('The extent of something from side to side', 'Process', 1, 'domain'),
  ('How tall something is', 'People', 1, 'domain'),
  ('The distance between the front and back or top and bottom surfaces of something', 'Initiating', 1, 'domain'),
  ('A continuous sequence of events that occur in an irreversible succession from the past through the present to the future', 'Planning', 1, 'domain'),
  ('A measure of the degree of hotness or coldness of an object or substance', 'Executing', 1, 'group');

INSERT INTO `iquiz`.`user`
(
  `full_name`,
  `email`,
  `gender`,
  `mobile`,
  `password`,
  `role_id`,
  `enable`,`avatar`, `last_update_date`, `token` )
VALUES
('Nguyen Anh Quan', 'forwork.quanna@gmail.com', 1, '0379039267', '$2a$10$Y4FuCb/0u/nBk3aoF8vlLuVRMQ9h2d0oJSeQQZUtKCMr.sTSsQ5xm', 1,1, 'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Nguyen Van A', 'duongmai.hoamkt76@gmail.com', 1, '012345678', '$2a$10$vvipoMflT5Wdj0JyoX4J/eeuYiGr8yRTXuGjmDTi5mr6CwDfa7Iwu', 1,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Nguyen Thi B', 'dungnpn28@gmail.com', 1, '0987654321', '$2a$10$w4LDTQp5Ipa1BXIPBwKBU.GdSX2THJzQZI4tCsDxWQ1gt06CaJHl.', 1,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr. GiCungGioi', 'quannahe176660@gmail.com', 0, '068686868', '$2a$10$awkejTMdluxHD7cAMbLgZecxu3aIRz.ZaG/WQ/qST4sstd4Kpomj6', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr. ThaoTungTamLy', 'maintnhe176347@gmail.com', 0, '0973600757', '$2a$10$t5umNOy/O0Yc01ayXiRdb.012.9F0XO0E3nAjVcbTZTOT4XMTykm2', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr. FixBugAllNight', 'expert3@gmail.com', 0, '0978779519', '$2a$10$Uzk3RS/EzofVGptWv.J56e86sZyyknsX8V9fCyKfAuswCzFG6/pzy', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Lung Thi Linh', 'lunglinh2003@gmail.com', 0, '0982712755', '$2a$10$lgsnmItDSSUGHSqmgSNrxu43.l2VX4btPVDzC8mipIhMamdpL/uKi', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mr. ConNhaNguoiTa', 'ntnm222003@gmail.com', 0, '0978779519', '$2a$10$IfeQU2m6ZnVlmPtBPkvSQe/LO60yGx/Ns87feVP2BhxfPLRMWAIdC', 2,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Khanh Ly', 'ntnm2003@gmail.com', 1, '0978279519', '$2a$10$fClf8Ai6tSdGBps4Qg/T/O0Vf3ZmD7T0o6Ublsr8Qh1t2FtNb6A0m', 3,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Phuong Anh', 'jsclub.bannd@gmail.com', 1, '0978379519', '$2a$10$U0QdIeEgZW1uiaKJTudIAOkLk8zHsGlpEzjepkKsvGs.PuBUhFsmq', 5,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Hoang Ha', 'iamntnm@gmail.com', 1, '0978775519', '$2a$10$X8h5vBAFQlYM87eo9KGn/u6eKM78Hj9osWmw5ebuZKDo/GUDhcELG', 5,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Mai Linh', 'laitrang143@gmail.com', 1, '0868459509', '$2a$10$m5VhkfqM1KRv7KY.NMDnte3Fz8WnYnLFiqpayJjAvsfKnSgIg1jHK', 5,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Anh Hong', 'lopchungminh@gmail.com', 1, '0978775519', '$2a$10$0GNydfb8QuvQsPLbIo2HeO/BXILworYeL0ox5QcUD5WcJmEASZ8qK', 4,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02',''),
('Tran Thi Anh', 'maiaiai@gmail.com', 1, '0978775519', '$2a$10$AeYGCoDfuRQbAA2/Fh400e/jEng7glVlTJRudIBzG4Hrx7MKDCMLi', 4,1,'https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg','2023-05-02','');

INSERT INTO `iquiz`.`subject`
(
  `brief_info`,
  `description`,
  `thumbnail`,
  `title`, `created_time`, `last_updated_time`, `status`,
  `owner_id`)
VALUES
  ('Introduction to Calculus', 'Learn the basics of calculus, including limits, derivatives, and integrals.', 'https://example.com/thumbnails/calculus.jpg', 'Calculus', '2023-03-02','2023-03-02', 1, 4),
  ('Statistics for Data Analysis', 'Learn the basics of statistical analysis, including probability distributions and hypothesis testing.', 'https://example.com/thumbnails/stats.jpg', 'Statistics', '2023-04-02','2023-04-02',  1, 4),
  ('Finance Fundamentals', 'An introduction to financial concepts such as accounting, budgeting, and investment strategies.', 'https://example.com/thumbnails/finance.jpg', 'Finance', '2023-03-31', '2023-03-31', 1, 4),
  ('Marketing Principles', 'Learn the basics of marketing, including market research, branding, and promotional strategies.', 'https://example.com/thumbnails/marketing.jpg', 'Marketing', '2023-04-04','2023-04-04',  1, 4),
  ('Art History', 'A comprehensive study of art throughout history, including major movements, styles, and artists.', 'https://example.com/thumbnails/art_history.jpg', 'Art History', '2023-02-02','2023-02-02', 1, 4),
  ('Creative Writing', 'Learn how to write fiction, poetry, and creative nonfiction. Explore different writing styles and techniques.', 'https://example.com/thumbnails/creative_writing.jpg', 'Creative Writing', '2023-02-06','2023-02-06', 1, 4),
  ('Public Speaking Skills', 'Develop effective public speaking skills and techniques, including audience analysis, delivery, and persuasion.', 'https://example.com/thumbnails/public_speaking.jpg', 'Public Speaking', '2022-12-02','2022-12-02',  1, 5),
  ('Critical Thinking', 'Learn how to analyze and evaluate arguments, make sound decisions, and develop logical reasoning skills.', 'https://example.com/thumbnails/critical_thinking.jpg', 'Critical Thinking', '2022-12-25','2022-12-25',  1, 4),
  ('Psychology Basics', 'An introduction to the fundamental concepts of psychology, such as perception, learning, memory, and motivation.', 'https://example.com/thumbnails/psychology.jpg', 'Psychology', '2023-05-02','2023-05-02',  1, 5),
  ('Sociology Fundamentals', 'Learn about the social structures and dynamics of human societies, including topics such as culture, inequality, and social change.', 'https://example.com/thumbnails/sociology.jpg', 'Sociology', '2023-01-02','2023-01-02',  1, 5),
  ('Political Science Basics', 'An introduction to the study of politics and government, including different political systems, ideologies, and international relations.', 'https://example.com/thumbnails/political_science.jpg', 'Political Science', '2023-03-21','2023-03-21', 1, 5),
  ('Philosophy for Beginners', 'Explore the major philosophical questions and ideas, such as ethics, metaphysics, and epistemology.', 'https://example.com/thumbnails/philosophy.jpg', 'Philosophy', '2023-05-02', '2023-05-02',  1, 5),
  ('World Religions', 'A comparative study of the major world religions, including their beliefs, practices, and historical development.', 'https://example.com/thumbnails/religion.jpg', 'World Religions', '2023-01-20',  1, 5),
  ('Artificial Intelligence Applications', 'Learn about the practical applications of artificial intelligence in fields such as healthcare, finance, and robotics.', 'https://example.com/thumbnails/ai_applications.jpg', 'AI Applications', '2023-03-02','2023-03-02',  1, 6),
  ('Database Design and Management', 'A deeper dive into database design and management, covering advanced SQL queries, normalization, and performance tuning.', 'https://example.com/thumbnails/dbms_advanced.jpg', 'Advanced Database Management', '2022-09-02','2022-09-02',   1, 6),
  ('Web Development with Frameworks', 'Learn how to build web applications using popular frameworks such as React, Angular, and Vue.', 'https://example.com/thumbnails/frameworks.jpg', 'Web Development with Frameworks','2023-03-20', '2023-03-20', 1, 6),
  ('Mobile App Development', 'Learn how to develop mobile apps for iOS and Android platforms using native languages or cross-platform tools.', 'https://example.com/thumbnails/mobile_dev.jpg', 'Mobile App Development', '2023-02-20','2023-02-20',  1, 6),
  ('Cybersecurity Basics', 'An introduction to cybersecurity principles, including risk assessment, threat modeling, and defense strategies.', 'https://example.com/thumbnails/cybersecurity.jpg', 'Cybersecurity', '2023-04-02','2023-04-02',  1, 7),
  ('An introduction to Algebra', 'This subject covers the basics of algebraic equations and their solutions.', 'https://example.com/thumbnails/algebra.jpg', 'Algebra', '2023-04-02','2023-04-02',  1, 8),
  ('Introduction to Geometry', 'This subject covers the basics of geometric shapes, angles, and measurements.', 'https://example.com/thumbnails/geometry.jpg', 'Geometry', '2023-03-20','2023-03-20',  1, 6),
  ('Trigonometry for Beginners', 'This subject covers the basics of trigonometric functions and their applications in real life.', 'https://example.com/thumbnails/trigonometry.jpg', 'Trigonometry','2023-03-02', '2023-03-02', 1, 6),
  ('World War II History', 'A comprehensive study of World War II, including key events, causes, and consequences.', 'https://example.com/thumbnails/ww2.jpg', 'World War II', '2023-05-02','2023-05-02',  1, 5),
  ('American Revolution', 'A comprehensive study of the American revolution, including key events, causes, and consequences.', 'https://example.com/thumbnails/amrev.jpg', 'American Revolution', '2023-01-02', '2023-01-02', 1, 5),
  ('Ancient Civilizations: Egypt and Mesopotamia', 'A study of the ancient civilizations of Egypt and Mesopotamia, including their culture, religion, and achievements.', 'https://example.com/thumbnails/ancient_civ.jpg', 'Ancient Civilizations', '2023-05-02','2023-05-02',  1, 5),
  ('Goal Setting for Success', 'Learn how to set achievable goals and develop a plan to achieve them.', 'https://example.com/thumbnails/goal_setting.jpg', 'Goal Setting',  '2022-12-02', '2022-12-02', 1, 5),
  ('Planning Techniques for Better Time Management', 'Learn effective planning techniques to manage your time more efficiently.', 'https://example.com/thumbnails/planning.jpg', 'Planning',  '2022-12-02','2022-12-02',  1, 5),
  ('Prioritization Strategies', 'Learn how to prioritize tasks and make informed decisions about how to allocate your time and resources.', 'https://example.com/thumbnails/prioritization.jpg', 'Prioritization', '2023-03-02','2023-03-02',   1, 6),
  ('Improving Productivity', 'Learn techniques and strategies to improve your productivity and get more done in less time.', 'https://example.com/thumbnails/productivity.jpg', 'Productivity','2023-03-20', '2023-03-20', 1, 8),
  ('Human Anatomy Basics', 'Learn the basic anatomy of the human body, including its organs, systems, and functions.', 'https://example.com/thumbnails/human_anatomy.jpg', 'Human Anatomy','2023-04-20', '2023-04-20',1, 8),
  ('Introduction to Genetics', 'Learn the basics of genetics, including DNA structure, inheritance patterns, and mutations.', 'https://example.com/thumbnails/genetics.jpg', 'Genetics','2023-02-20', '2023-02-20', 1, 8),
  ('Ecology and Environmental Science', 'Study the relationship between living organisms and their environment, and learn about topics such as climate change, conservation, and sustainability.', 'https://example.com/thumbnails/ecology.jpg', 'Ecology','2023-04-10', '2023-04-10', 1, 7),
  ('Artificial Intelligence Fundamentals', 'Learn the basics of artificial intelligence, including machine learning, neural networks, and natural language processing.', 'https://example.com/thumbnails/ai.jpg', 'Artificial Intelligence', '2023-05-03', '2023-05-03', 1, 7),
  ('Introduction to Database Management', 'Learn the basics of database design, management, and querying using SQL.', 'https://example.com/thumbnails/dbms.jpg', 'Database Management', '2023-02-28', '2023-02-28', 1, 8),
  ('Web Development Fundamentals', 'Learn the basics of web development, including HTML, CSS, and JavaScript.', 'https://example.com/thumbnails/webdev.jpg', 'Web Development','2023-04-02','2023-04-02',  1, 7),
  ('Advanced Algebra', 'A more advanced study of algebraic equations, including higher-degree polynomials and complex numbers.', 'https://example.com/thumbnails/adv_algebra.jpg', 'Advanced Algebra', '2023-01-22', '2023-01-22', 1, 7),
  ('Geometry in Three Dimensions', 'An introduction to three-dimensional geometry, including vectors, planes, and surfaces.', 'https://example.com/thumbnails/3d_geometry.jpg', 'Three-Dimensional Geometry', '2023-04-02','2023-04-02',  1, 8),
  ('Trigonometry in Real Life Applications', 'Learn how trigonometric functions are used in practical applications such as navigation, engineering, and physics.', 'https://example.com/thumbnails/trig_applications.jpg', 'Trigonometry Applications', '2023-02-22', '2023-02-22', 1, 6);

update iquiz.subject
set featured = 1 where id=1;
update iquiz.subject
set featured = 1 where id=2;
update iquiz.subject
set featured = 1 where id=3;
update iquiz.subject
set featured = 1 where id=4;

update iquiz.subject
set tagline = 'MathIsEasy' where id=1;
update iquiz.subject
set tagline = 'WorldOfData' where id=2;
update iquiz.subject
set tagline = 'ILoveMoney' where id=3;
update iquiz.subject
set tagline = 'MarketingFundamentals' where id=4;

update iquiz.subject
set thumbnail = 'subject1_img.png' where id=1;
update iquiz.subject
set thumbnail = 'subject2_img.png' where id=2;
update iquiz.subject
set thumbnail = 'subject3_img.png' where id=3;
update iquiz.subject
set thumbnail = 'subject6_img.png' where id=4;

update iquiz.subject
set brief_info = 'Chemistry Fundamentals'
where id = 8;

update iquiz.subject
set brief_info = 'Exploring the Human Mind'
where id = 13;

update iquiz.subject
set brief_info = 'Understanding Environmental Biology'
where id = 15;

update iquiz.subject
set brief_info = 'Mastering Communication Skills'
where id = 17;

update iquiz.subject
set status = 0
where id = 37;

INSERT INTO `iquiz`.`pricepackage`
(
  `subject_id`,
  `name`,
  `access_duration`,
  `list_price`,
  `description`,
  `sale_price`,
  `status`)
VALUES
  (1, 'Gói truy cập 3 tháng', 3, 500,'available', 320, 1),
  (1, 'Gói truy cập 6 tháng', 6, 900,'available', 680, 1),
  (1, 'Gói truy cập vô th?i hạn', -1, 1500,'submitted', 1000, 1),
  (2, 'Gói truy cập 3 tháng', 3, 400,'available' ,300, 1),
  (2, 'Gói truy cập 6 tháng', 6, 700, 'available',650, 1),
  (2, 'Gói truy cập vô th?i hạn', -1, 1000,'submitted', 800, 1),
  (3, 'Gói truy cập 3 tháng', 3, 700,'available', 549, 1),
  (3, 'Gói truy cập 6 tháng', 6, 1200,'available', 999, 1),
  (3, 'Gói truy cập vô th?i hạn', -1, 1800,'available', 1000, 1),
  (4, 'Gói truy cập 3 tháng', 3, 500,'available', 320, 1),
  (4, 'Gói truy cập 6 tháng', 6, 900,'submitted', 680, 1),
  (4, 'Gói truy cập vô th?i hạn', -1, 1500, 'available',1000, 1),
  (5, 'Gói truy cập 3 tháng', 3, 500,'available', 320, 0),
  (5, 'Gói truy cập 6 tháng', 6, 900,'submitted', 680, 0),
  (5, 'Gói truy cập vô th?i hạn', -1, 1500,'available', 1000, 1),
  (6, 'Gói truy cập 3 tháng', 3, 500, 'available',320, 1),
  (6, 'Gói truy cập 6 tháng', 6, 900, 'submitted',680, 0),
  (6, 'Gói truy cập vô th?i hạn', -1, 1500, 'available',1000, 0),
  (7, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
  (7, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
  (7, 'Gói truy cập vô th?i hạn', -1, 1600, 'submitted', 1200, 1),
  (8, 'Gói truy cập 3 tháng', 3, 450, 'available', 350, 1),
  (8, 'Gói truy cập 6 tháng', 6, 750, 'available', 550, 1),
  (8, 'Gói truy cập vô th?i hạn', -1, 1300, 'available', 900, 1),
  (9, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
  (9, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
  (9, 'Gói truy cập vô th?i hạn', -1, 1500, 'available', 1100, 1),
  (10, 'Gói truy cập 3 tháng', 3, 600, 'available', 450, 1),
  (10, 'Gói truy cập 6 tháng', 6, 1100, 'submitted', 900, 1),
  (10, 'Gói truy cập vô th?i hạn', -1, 1800, 'available', 1400, 1),
  (11, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
  (11, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
  (11, 'Gói truy cập vô th?i hạn', -1, 2000, 'available', 1500, 1),
  (12, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
  (12, 'Gói truy cập 6 tháng', 6, 950, 'available', 700, 1),
  (12, 'Gói truy cập vô th?i hạn', -1, 1400, 'submitted', 1100, 1),
  (13, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
  (13, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
  (13, 'Gói truy cập vô th?i hạn', -1, 1500, 'available', 1200, 1),
  (14, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
  (14, 'Gói truy cập 6 tháng', 6, 800, 'submitted', 650, 1),
  (14, 'Gói truy cập vô th?i hạn', -1, 1300, 'available', 1000, 1),
  (15, 'Gói truy cập 3 tháng', 3, 550, 'available', 400,1),
  (15, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
  (15, 'Gói truy cập vô th?i hạn', -1, 1500, 'submitted', 1200, 1),
  (16, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
  (16, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
  (16, 'Gói truy cập vô th?i hạn', -1, 1600, 'available', 1200, 1),
  (17, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
  (17, 'Gói truy cập 6 tháng', 6, 800, 'available', 600, 1),
  (17, 'Gói truy cập vô th?i hạn', -1, 1300, 'available', 1000, 1),
  (18, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
  (18, 'Gói truy cập 6 tháng', 6, 950, 'available', 700, 1),
  (18, 'Gói truy cập vô th?i hạn', -1, 1400, 'submitted', 1100, 1),
  (19, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
  (19, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
  (19, 'Gói truy cập vô th?i hạn', -1, 1500, 'available', 1200, 1),
  (20, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
  (20, 'Gói truy cập 6 tháng', 6, 800, 'submitted', 650, 1),
  (20, 'Gói truy cập vô th?i hạn', -1, 1300, 'available', 1000, 1),
  (21, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
  (21, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
  (21, 'Gói truy cập vô th?i hạn', -1, 1600, 'available', 1200, 1),
  (22, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
  (22, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
  (22, 'Gói truy cập vô th?i hạn', -1, 1500, 'submitted', 1200, 1),
  (23, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
  (23, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
  (23, 'Gói truy cập vô th?i hạn', -1, 2000, 'available', 1500, 1),
  (24, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
  (24, 'Gói truy cập 6 tháng', 6, 950, 'available', 700, 1),
  (24, 'Gói truy cập vô th?i hạn', -1, 1400, 'submitted', 1100, 1),
  (25, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
  (25, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
  (25, 'Gói truy cập vô th?i hạn', -1, 1500, 'available', 1200, 1),
  (26, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
  (26, 'Gói truy cập 6 tháng', 6, 800, 'submitted', 650, 1),
  (26, 'Gói truy cập vô th?i hạn', -1, 1300, 'available', 1000, 1),
  (27, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
  (27, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
  (27, 'Gói truy cập vô th?i hạn', -1, 1600, 'available', 1200, 1),
  (28, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
  (28, 'Gói truy cập 6 tháng', 6, 1000, 'available', 750, 1),
  (28, 'Gói truy cập vô th?i hạn', -1, 1500, 'submitted', 1200, 1),
  (29, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
  (29, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
  (29, 'Gói truy cập vô th?i hạn', -1, 2000, 'available', 1500, 1),
  (30, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
  (30, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
  (30, 'Gói truy cập vô th?i hạn', -1, 1500, 'available', 1200, 1),
  (31, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
  (31, 'Gói truy cập 6 tháng', 6, 800, 'available', 600, 1),
  (31, 'Gói truy cập vô th?i hạn', -1, 1300, 'submitted', 1000, 1),
  (32, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
  (32, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
  (32, 'Gói truy cập vô th?i hạn', -1, 1600, 'available', 1200, 1),
  (33, 'Gói truy cập 3 tháng', 3, 550, 'available', 400, 1),
  (33, 'Gói truy cập 6 tháng', 6, 1000, 'submitted', 750, 1),
  (33, 'Gói truy cập vô th?i hạn', -1, 1500, 'available', 1200, 1),
  (34, 'Gói truy cập 3 tháng', 3, 700, 'available', 500, 1),
  (34, 'Gói truy cập 6 tháng', 6, 1200, 'available', 900, 1),
  (34, 'Gói truy cập vô th?i hạn', -1, 2000, 'available', 1500, 1),
  (35, 'Gói truy cập 3 tháng', 3, 500, 'available', 350, 0),
  (35, 'Gói truy cập 6 tháng', 6, 900, 'available', 600, 0),
  (35, 'Gói truy cập vô th?i hạn', -1, 1500, 'available', 1200, 1),
  (36, 'Gói truy cập 3 tháng', 3, 450, 'available', 300, 1),
  (36, 'Gói truy cập 6 tháng', 6, 800, 'available', 600, 1),
  (36, 'Gói truy cập vô th?i hạn', -1, 1300, 'submitted', 1000, 1),
  (37, 'Gói truy cập 3 tháng', 3, 600, 'available', 400, 1),
  (37, 'Gói truy cập 6 tháng', 6, 1100, 'available', 800, 1),
  (37, 'Gói truy cập vô th?i hạn', -1, 1600, 'available', 1200, 1);
INSERT INTO `iquiz`.`level`
(
  `name`,
  `status`)
VALUES
  ('easy', 1),
  ('medium', 1),
  ('difficult', 1),
  ('challenging', 1);
INSERT INTO `iquiz`.`category`
(`name`, `description`, `status`)
VALUES
  ('Mathematics', 'This category covers topics related to numbers, operations, and mathematical reasoning.',1),
  ('History', 'This category covers topics related to past events, people, and societies.',1),
  ('Time Management', 'This category covers topics related to managing time effectively, including goal setting, planning, prioritization, and productivity.',1),
  ('Biology', 'This category covers topics related to living organisms and their processes.',1),
  ('Computer Science', 'This category covers topics related to computing technology and its applications.',1);

INSERT INTO `iquiz`.`subcategory`
(
  `name`,
  `status`,
  `category_id`)
VALUES
  ('Algebra',1, 1),
  ('Geometry',1, 1),
  ('Trigonometry',1, 1),
  ('World War II',1, 2),
  ('American Revolution',1, 2),
  ('Ancient Civilizations',1, 2),
  ('Goal Setting', 1, 3),
  ('Planning', 1, 3),
  ('Prioritization', 1, 3),
  ('Productivity', 1, 3),
  ('Human Anatomy',1, 4),
  ('Genetics', 1,4),
  ('Ecology', 1,4),
  ('Artificial Intelligence',1, 5),
  ('Database Management',1, 5),
  ('Web Development', 1,5);

INSERT INTO `iquiz`.`Subject_subcategory` (`subject_id`, `subcategory_id`)
VALUES
  (1, 1), (1, 2), (1, 3),
  (2, 4), (2, 5), (2, 6),
  (3, 7), (3, 8), (3, 9), (3, 10),
  (4, 11), (4, 12), (4, 13),
  (5, 14), (5, 15), (5, 16),
  (6, 7), (6, 8), (6, 9), (6, 10),
  (7, 14), (7, 15), (7, 16),
  (8, 11), (8, 12), (8, 13),
  (9, 11), (9, 12), (9, 13),
  (10, 14), (10, 15), (10, 16),
  (11, 2), (11, 3),
  (12, 1), (12, 3),
  (13, 2), (13, 3),
  (14, 14), (14, 15), (14, 16),
  (15, 11), (15, 12), (15, 13),
  (16, 14), (16, 15), (16, 16),
  (17, 11),
  (18, 2),
  (19, 3),
  (20, 4),
  (21, 5),
  (22, 6),
  (23, 7),
  (24, 8),
  (25, 9),
  (26, 10),
  (27, 11),
  (28, 12),
  (29, 13),
  (30, 14),
  (31, 15),
  (32, 16),
  (33, 14),
  (34, 15),
  (35, 11),
  (36, 3),
  (37, 6);

INSERT INTO `iquiz`.`testtype`
(
  `name`,
  `status`)
VALUES
  ('Simulation Tests', 1),
  ('Lesson-Quiz Tests', 1);


INSERT INTO `iquiz`.`lesson`
(`lesson_type_id`,`subject_id`,`dimension_id`,`name`,`order`,`status`,`topic`,`creator_id`,`html_content`,
 `duration`,`pass_rate`,
 `test_type_id`,`level_id`,
 `video_link`)
VALUES
  (1, 1, 2, 'Calculus Basics Quiz'
  , 1, 1, '', 4, '', '00:45:00', 0.5, 1, 1, ''),
  (1, 1, 2, 'Limits and Derivatives Quiz'
  , 2, 1, '', 4, '', '01:15:00', 0.5, 1, 2, ''),
  (1, 1, 2, 'Integral Calculus Quiz'
  , 3, 1, '', 4, '', '01:30:00', 0.6, 1, 3, ''),
  (1, 2, 2, 'Probability Theory Quiz'
  , 1, 1, '', 4, '', '00:50:00', 0.6, 1, 2, ''),
  (1, 2, 2, 'Descriptive Statistics Quiz'
  , 2, 1, '', 4, '', '00:20:00', 0.7, 1, 1, ''),
  (1, 2, 2, 'Statistical Inference Quiz'
  , 3, 1, '', 4, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 3, 3, 'Financial Statements Quiz'
  , 1, 1, '', 4, '', '00:30:00', 0.6, 1, 2, ''),
  (1, 3, 3, 'Time Value of Money Quiz'
  , 2, 1, '', 4, '', '01:10:00', 0.7, 1, 3, ''),
  (1, 3, 3, 'Investment Analysis Quiz'
  , 3, 1, '', 4, '', '01:20:00', 0.7, 1, 4, ''),
  (1, 4, 3, 'Product Development Quiz'
  , 1, 1, '', 4, '', '00:40:00', 0.7, 1, 2, ''),
  (1, 4, 3, 'Consumer Behavior Quiz'
  , 2, 1, '', 4, '', '00:20:00', 0.8, 1, 1, ''),
  (1, 4, 3, 'Marketing Strategy Quiz'
  , 3, 1, '', 4, '', '01:30:00', 0.8, 1, 4, ''),
  (1, 5, 3, 'Ancient Art Quiz'
  , 1, 1, '', 4, '', '00:50:00', 0.8, 1, 3, ''),
  (1, 5, 3, 'Renaissance Art Quiz'
  , 2, 1, '', 4, '', '01:15:00', 0.9, 1, 4, ''),
  (1, 5, 3, 'Modern Art Quiz'
  , 3, 1, '', 4, '', '00:30:00', 0.6, 1, 2, ''),
  (1, 6, 3, 'World Geography Quiz 1'
  , 1, 1, '', 4, '', '00:45:00', 0.7, 1, 2, ''),
  (1, 6, 3, 'World Geography Quiz 2'
  , 2, 1, '', 4, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 6, 3, 'World Geography Quiz 3'
  , 3, 1, '', 4, '', '01:30:00', 0.8, 1, 2,''),
  (1, 7, 3, 'Computer Networks Quiz'
  , 1, 1, '', 5, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 7, 3, 'Operating Systems Quiz'
  , 2, 1, '', 5, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 7, 3, 'Database Management Quiz'
  , 3, 1, '', 5, '', '01:30:00', 0.8, 1, 3, ''),
  (1, 8, 3, 'Basic Chemistry Quiz'
  , 1, 1, '', 4, '', '00:45:00', 0.6, 1, 2, ''),
  (1, 8, 3, 'Organic Chemistry Quiz'
  , 2, 1, '', 4, '', '01:15:00', 0.7, 1, 3, ''),
  (1, 8, 3, 'Biochemistry Quiz'
  , 3, 1, '', 4, '', '01:30:00', 0.8, 1, 4, ''),
  (1, 9, 3, 'World History Quiz 1'
  , 1, 1, '', 5, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 9, 3, 'World History Quiz 2'
  , 2, 1, '', 5, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 9, 3, 'Modern History Quiz'
  , 3, 1, '', 5, '', '01:30:00', 0.8, 1, 3, ''),
  (1, 10, 3, 'Microeconomics Quiz'
  , 1, 1, '', 5, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 10, 3, 'Macroeconomics Quiz'
  , 2, 1, '', 5, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 10, 3, 'International Trade Quiz'
  , 3, 1, '', 5, '', '01:30:00', 0.8, 1, 3, ''),
  (1, 11, 3, 'Foundations of Ethics Quiz'
  , 1, 1, '', 5, '', '00:45:00', 0.6, 1, 2, ''),
  (1, 11, 3, 'Applied Ethics Quiz'
  , 2, 1, '', 5, '', '01:15:00', 0.7, 1, 3, ''),
  (1, 11, 3, 'Metaethics Quiz'
  , 3, 1, '', 5, '', '01:30:00', 0.8, 1, 4, ''),
  (1, 12, 3, 'Intro to Law Quiz'
  , 1, 1, '', 5, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 12, 3, 'Business Law Quiz'
  , 2, 1, '',5, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 12, 3, 'Intellectual Property Law Quiz'
  , 3, 1, '',5, '', '01:30:00', 0.8, 1, 3,''),
  (1, 13, 3, 'Intro to Psychology Quiz'
  , 1, 1, '', 5, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 13, 3, 'Social Psychology Quiz'
  , 2, 1, '', 5, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 13, 3, 'Cognitive Psychology Quiz'
  , 3, 1, '', 5, '', '01:30:00', 0.8, 1, 3, ''),
  (1, 14, 3, 'Political Science Quiz'
  , 1, 1, '', 6, '', '00:45:00', 0.6, 1, 2, ''),
  (1, 14, 3, 'Comparative Politics Quiz'
  , 2, 1, '',6, '', '01:15:00', 0.7, 1, 3, ''),
  (1, 14, 3, 'International Relations Quiz'
  , 3, 1, '', 6, '', '01:30:00', 0.8, 1, 4, ''),
  (1, 15, 3, 'Environmental Science Quiz'
  , 1, 1, '', 6, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 15, 3, 'Ecology Quiz'
  , 2, 1, '', 6, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 15, 3, 'Conservation Biology Quiz'
  , 3, 1, '', 6, '', '01:30:00', 0.8, 1, 3, ''),
  (1, 16, 3, 'Intro to Philosophy Quiz'
  , 1, 1, '', 6, '', '00:45:00', 0.6, 1, 2, ''),
  (1, 16, 3, 'Ethics Quiz'
  , 2, 1, '', 6, '', '01:15:00', 0.7, 1, 3, ''),
  (1, 16, 3, 'Logic and Critical Thinking Quiz'
  , 3, 1, '', 6, '', '01:30:00', 0.8, 1, 4, ''),
  (1, 17, 3, 'Public Speaking Quiz'
  , 1, 1, '', 6, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 17, 3, 'Interpersonal Communication Quiz'
  , 2, 1, '', 6, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 17, 3, 'Intercultural Communication Quiz'
  , 3, 1, '', 6, '', '01:30:00', 0.8, 1, 3, ''),
  (1, 18, 3, 'Anthropology Quiz'
  , 1, 1, '', 7, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 18, 3, 'Sociology Quiz'
  , 2, 1, '', 7, '', '01:20:00', 0.8, 1, 4, ''),
  (1, 18, 3, 'Cultural Studies Quiz'
  , 3, 1, '', 7, '', '01:30:00', 0.8, 1, 2, ''),
  (1, 19, 3, 'Intro to Music Quiz'
  , 1, 1, '', 8, '', '00:45:00', 0.6, 1, 2, ''),
  (1, 19, 3, 'Music Theory Quiz'
  , 2, 1, '', 8, '', '01:15:00', 0.7, 1, 3, ''),
  (1, 20, 3, 'Intro to Programming Quiz'
  , 1, 1, '', 6, '', '01:00:00', 0.7, 1, 3, ''),
  (1, 20, 3, 'Data Structures Quiz'
  , 2, 1, '', 6, '', '01:30:00', 0.8, 1, 1, '');

INSERT INTO `iquiz`.`registrationstatus`
(
  `name`)
VALUES
  ('paid'),
  ('unpaid'),
  ('pending'),
  ('cancelled');


INSERT INTO `iquiz`.`user_subject`
(`user_id`,
 `subject_id`,
 `price_package_id`,
 `registration_time`,
 `last_updated_by`,
 `valid_from`,
 `registration_status_id`,
 `created_by`)
VALUES
(9, 3, 7, '2023-05-05', '2023-05-12', 1,9,9),
(9, 4, 12, '2023-04-05', '2023-04-12', 1,9,9),
(9, 1, 26, '2023-05-02', '2023-05-09', 1,1,1),
(9, 12, 33, '2023-05-05', '2023-05-12', 1,9,9),
(9, 18, 54, '2023-01-05', '2023-01-12', 1,9,9),
(10, 1, 6, '2023-03-05', '2023-03-12', 1,9,9),
(10, 3, 17, '2023-04-30', '2023-05-07', 1,1,1),
(10, 8, 22, '2023-05-05', '2023-05-12', 1,1,1),
(10, 13, 39, '2023-05-05', '2023-05-12', 1,9,9),
(10, 15, 43, '2023-05-05', '2023-05-12', 1,9,9),
(10, 17, 50, '2023-05-05', '2023-05-12', 1,9,9),
(11, 1, 37, '2023-05-05', '2023-05-12', 1,1,1),
(11, 14, 41, '2023-05-05', '2023-05-12', 1,9,9),
(12, 3, 28, '2023-05-05', '2023-05-12', 1,1,1),
(12, 11, 32, '2023-05-05', '2023-05-12', 1,9,9);

INSERT INTO `iquiz`.`question`
(`dimension_id`,
 `question_level_id`,
 `audio`,
 `content`,
 `image`,
 `status`,

 `video`)
VALUES
  (3, 2, '','What is the limit of f(x) = (x^2 - 2x - 3)/(x - 3) as x approaches 3?' ,'', 1, ''),
  (3, 3, '','What is the derivative of f(x) = 4x^3 - 5x^2 + 2x - 1 using the power rule?' ,'', 0, ''),
  (3, 1, '','Find the integral of f(x) = 2x + 1 dx.' ,'', 0, ''),
  (3, 2, '','What is the second derivative of f(x) = 3x^2 + 2x - 1?' ,'', 0, ''),
  (3, 2, '','Evaluate the following limit: lim x->-infinity (3x^3 + 4x^2 - 2x)/(x^3 - 2x^2 + x)' ,'', 1, ''),
  (3, 2, '','Find the critical points of the function f(x) = 2x^3 - 3x^2 + 6x + 1.' ,'', 1, ''),
  (3, 2, '','What is the antiderivative of f(x) = sin(x)?' ,'', 1, ''),
  (3, 2, '','Find the derivative of f(x) = ln(x^2 + 1).' ,'', 1, ''),
  (3, 2, '','Evaluate the integral of f(x) = e^x dx.' ,'', 1, ''),
  (3, 2, '','Find the limit of f(x) = (sin(x) - x)/(x^3) as x approaches 0.' ,'', 1, ''),
  (2, 2, '','Which financial statement shows the companys revenues and expenses during a specific period?' ,'', 1, ''),
  (2, 2, '','What is the purpose of the balance sheet?' ,'', 1, ''),
  (2, 2, '','Which financial statement shows a companys cash inflows and outflows over a specific period of time?' ,'', 1, ''),
  (2, 2, '','What is the formula for calculating net income?' ,'', 1, ''),
  (2, 2, '','Which financial statement shows how a companys retained earnings have changed over a specific period?' ,'', 1, ''),
  (2, 2, '','What is the formula for calculating total assets?' ,'', 1, ''),
  (2, 2, '','Which financial statement shows a companys debt and equity financing activities?' ,'', 1, ''),
  (2, 2, '','What is the formula for calculating gross profit?' ,'', 1, ''),
  (2, 2, '','Which financial statement shows a companys revenues, expenses, and net income over a specific period of time?' ,'', 1, ''),
  (2, 2, '','What is the formula for calculating current ratio?' ,'', 1, ''),
  (4, 2, '','Which financial statement shows how a companys assets are financed (with debt or equity)?' ,'', 1, ''),
  (4, 2, '','What is the formula for calculating return on equity?' ,'', 1, ''),
  (4, 2, '','Which financial statement shows the cash balance at the beginning and end of a specific period?' ,'', 1, ''),
  (2, 2, '','What is the formula for calculating debt-to-equity ratio?' ,'', 1, ''),
  (5, 2, '','Which financial statement shows a companys dividends paid to shareholders over a specific period?' ,'', 1, ''),
  (5, 2, '','The Venus of Willendorf is an example of which type of art?' ,'', 1, ''),
  (5, 2, '','What was the main reason that ancient Egyptians created art?' ,'', 1, ''),
  (5, 2, '','Which civilization is known for creating the Parthenon temple?' ,'', 1, ''),
  (3, 2, '','Which ancient civilization is known for their elaborate burial practices and tomb paintings?' ,'', 1, ''),
  (2, 2, '','What material was often used to create sculptures during the ancient Roman empire?' ,'', 1, ''),
  (2, 2, '','What type of pottery is typically associated with the ancient Greeks?' ,'', 1, ''),
  (3, 2, '','What was the primary function of the ancient Chinese terracotta army?' ,'', 1, ''),
  (3, 2, '','Which ancient civilization is known for inventing the wheel and using it to create pottery?' ,'', 1, ''),
  (3, 2, '','What type of art is the Lascaux cave paintings an example of?' ,'', 1, ''),
  (4, 2, '','Which ancient civilization is known for creating large stone heads with distinctive features such as prominent nose and lips?' ,'', 1, ''),
  (4, 2, '','What is the process of dividing a market into smaller groups with similar needs or characteristics called?' ,'', 1, ''),
  (4, 2, '','Which of the following is NOT one of the 4 Ps of marketing?' ,'', 1, ''),
  (4, 2, '','What is the difference between a products actual benefits and its perceived benefits known as?' ,'', 1, ''),
  (4, 2, '','What is the process of creating a unique image and name for a product in the consumers mind called?' ,'', 1, ''),
  (5, 2, '','Which of the following is a pricing strategy where a company charges a high price for a new product with the intention of lowering it over time?' ,'', 1, ''),
  (5, 2, '','What is the process of communicating the value of a product or service to potential customers called?' ,'', 1, ''),
  (5, 2, '','Which of the following is NOT one of the stages in the product life cycle?' ,'', 1, ''),
  (3, 2, '','What is the process of developing and maintaining relationships with customers over time called?' ,'', 1, ''),
  (4, 2, '','What is a marketing strategy where a company creates different versions of the same product for different market segments called?' ,'', 1, ''),
  (4, 2, '','Which of the following advertising mediums allows for the most precise targeting of a specific audience?' ,'', 1, ''),
  (5, 2, '','What is the term for a musical piece with three or more independent parts played simultaneously?' ,'', 1, ''),
  (5, 2, '','Which of these terms describes a gradual increase in volume in music?' ,'', 1, ''),
  (5, 2, '','What is the name of the symbol that indicates a note should be played shorter than its full duration?' ,'', 1, ''),
  (5, 2, '','Which of these keys has only white keys on the piano?' ,'', 1, ''),
  (3, 2, '','What is the Italian term for "playful" or "lively"?' ,'', 1, ''),
  (3, 2, '','Which of these philosophical theories suggests that actions are morally right if they lead to the greatest amount of happiness for the most people?' ,'', 1, ''),
  (4, 2, '','In ethics, what is the term for a moral principle or rule that determines whether an action is right or wrong?' ,'', 1, ''),
  (3, 2, '','According to Aristotle, what is the ultimate goal of human life?' ,'', 1, ''),
  (3, 2, '','Which of these ethical theories emphasizes the importance of character traits such as honesty, courage, and compassion?' ,'', 1, ''),
  (4, 2, '','What is the term for a branch of ethics that deals with questions about right and wrong in society, such as justice, rights, and obligations?' ,'', 1, '');
INSERT INTO `iquiz`.`answer`
(
  `checking`,
  `explanation`,
  `status`,
  `question_id`)
VALUES
  ( 1, '6', 1, 1),
  ( 0, '0', 1, 1),
  ( 0, '-6', 1, 1),
  ( 0, 'undefined', 1, 1),
  ( 1, '12x^2 - 10x + 2', 1, 2),
  ( 0, '12x^2 - 10x', 1, 2),
  ( 0, '12x^4 - 10x^3 + 2x^2 - x', 1, 2),
  ( 0, '12x^4 - 10x^3 + 2x^2', 1, 2),
  ( 0, 'x^2 + x + C', 1, 3),
  ( 0, 'x^2 + 2x + C', 1, 3),
  ( 1, 'x^2/2 + x + C', 1, 3),
  ( 0, 'x^2/2 + 2x + C', 1, 3),
  ( 1, '6x + 2', 1, 4),
  ( 0, '6x', 1, 4),
  ( 0, '6', 1, 4),
  ( 0, '0', 1, 4),
  ( 0, '-3', 1, 5),
  ( 1, '3', 1, 5),
  ( 0, '-4', 1, 5),
  ( 0, '4', 1, 5),
  ( 1, 'x = -1, x = 1/2', 1, 6),
  ( 0, 'x = -1/2, x = 1', 1, 6),
  ( 0, 'x = -1, x = 3/2', 1, 6),
  ( 0, 'x = -3/2, x = 1', 1, 6),
  ( 1, '-cos(x)', 1, 7),
  ( 0, 'cos(x)', 1, 7),
  ( 0, '-sin(x)', 1, 7),
  ( 0, 'sin(x)', 1, 7),
  ( 1, '(2x)/(x^2 + 1)', 1, 8),
  ( 0, '(2x)/(2x^2 + 1)', 1, 8),
  ( 0, '(2x^2)/(x^2 + 1)', 1, 8),
  ( 0, '(2)/(x^2 + 1)', 1, 8),
  ( 1, 'e^x + C', 1, 9),
  ( 0, 'e^x^2 / 2 + C', 1, 9),
  ( 0, ' ln|x| + C', 1, 9),
  ( 0, '1/x + C', 1, 9),
  ( 0, '1/6', 1, 10),
  ( 1, '-1/6', 1, 10),
  ( 0, '1/3', 1, 10),
  ( 0, '-1/3', 1, 10) ,
  ( 0, 'Balance Sheet', 1, 11),
  ( 0, 'Cash Flow Statement', 1, 11),
  ( 1, 'Income Statement', 1, 11),
  ( 0, 'Retained Earnings Statement', 1, 11),
  ( 0, 'To show a companys revenues and expenses over a period of time', 1, 12),
  ( 0, 'To show the amount of cash flow in and out of a company', 1, 12),
  ( 1, 'To show a companys assets, liabilities, and equity at a specific point in time', 1, 12),
  ( 0, 'To show the difference between a companys current assets and current liabilities', 1, 12),
  ( 0, 'Balance Sheet', 1, 13),
  ( 1, 'Cash Flow Statement', 1, 13),
  ( 0, 'Income Statement', 1, 13),
  ( 0, 'Retained Earnings Statement', 1, 13),
  ( 0, 'Revenues + Expenses = Net Income', 1, 14),
  ( 0, 'Assets - Liabilities = Net Income', 1, 14),
  ( 0, 'Gross Profit - Operating Expenses = Net Income', 1, 14),
  ( 1, 'Total Revenue - Total Expenses = Net Income', 1, 14),
  ( 0, 'Balance Sheet', 1, 15),
  ( 0, 'Cash Flow Statement', 1, 15),
  ( 0, 'Income Statement', 1, 15),
  ( 1, 'Retained Earnings Statement', 1, 15),
  ( 0, 'Total Revenue - Operating Expenses', 1, 16),
  ( 1, 'Total Revenue - Cost of Goods Sold', 1, 16),
  ( 0, 'Gross Profit Margin x Total Revenue', 1, 16),
  ( 0, 'Net Income - Dividends', 1, 16),
  ( 0, 'Balance Sheet', 1, 17),
  ( 1, 'Cash Flow Statement', 1, 17),
  ( 0, 'Income Statement', 1, 17),
  ( 0, 'Retained Earnings Statement', 1, 17),
  ( 0, 'Total Revenue - Operating Expenses', 1, 18),
  ( 1, 'Total Revenue - Cost of Goods Sold', 1, 18),
  ( 0, 'Gross Profit Margin x Total Revenue', 1, 18),
  ( 0, 'Net Income - Dividends', 1, 18),
  ( 0, 'Balance Sheet', 1, 19),
  ( 0, 'Cash Flow Statement', 1, 19),
  ( 1, 'Income Statement', 1, 19),
  ( 0, 'Retained Earnings Statement', 1, 19),
  ( 1, 'Current Assets / Current Liabilities', 1, 20),
  ( 0, 'Total Assets / Total Liabilities', 1, 20),
  ( 0, 'Total Equity / Total Liabilities', 1, 20),
  ( 0, 'Net Income / Total Revenue', 1, 20),
  ( 1, 'Balance Sheet', 1, 21),
  ( 0, 'Cash Flow Statement', 1, 21),
  ( 0, 'Income Statement', 1, 21),
  ( 0, 'Retained Earnings Statement', 1, 21),
  ( 0, 'Net Income / Total Assets', 1, 22),
  ( 0, 'Net Income / Total Equity', 1, 22),
  ( 0, 'Gross Profit / Total Assets', 1, 22),
  ( 0, 'Gross Profit / Total Equity', 1, 22),
  ( 0, 'Balance Sheet', 1, 23),
  ( 1, 'Cash Flow Statement', 1, 23),
  ( 0, 'Income Statement', 1, 23),
  ( 0, 'Retained Earnings Statement', 1, 23),
  ( 1, 'Total Liabilities / Total Equity', 1, 24),
  ( 0, 'Current Assets / Current Liabilities', 1, 24),
  ( 0, 'Gross Profit / Total Equity', 1, 24),
  ( 0, 'Net Income / Total Equity', 1, 24),
  ( 0, 'Balance Sheet', 1, 25),
  ( 0, 'Cash Flow Statement', 1, 25),
  ( 0, 'Income Statement', 1, 25),
  ( 1, 'Retained Earnings Statement', 1, 25),
  ( 0, 'Egyptian art', 1, 26),
  ( 0, 'Greek art', 1, 26),
  ( 1, 'Prehistoric art', 1, 26),
  ( 0, 'Roman art', 1, 26),
  ( 0, 'To showcase their wealth', 1, 27),
  ( 1, 'To please their gods', 1, 27),
  ( 0, 'To celebrate their victories in war', 1, 27),
  ( 0, 'For personal enjoyment', 1, 27),
  ( 0, 'Ancient Rome', 1, 28),
  ( 1, 'Ancient Greece', 1, 28),( 0, 'Ancient Egypt', 1, 28),( 0, 'Ancient China', 1, 28),
  ( 0, 'Aztec civilization', 1, 29),( 0, 'Incan civilization', 1, 29), ( 0, 'Mayan civilization', 1, 29),( 1, 'Egyptian civilization', 1, 29),
  ( 1, 'Marble', 1, 30), ( 0, 'Bronze', 1, 30),( 0, 'Gold', 1, 30),( 0, 'Silver', 1, 30),
  ( 1, 'Terracotta', 1, 31), ( 0, 'Porcelain', 1, 31), ( 0, 'Stoneware', 1, 31), ( 0, 'Earthenware', 1, 31),
  ( 1, 'To protect the emperor in the afterlife', 1, 32), ( 0, 'To defend against invading armies', 1, 32),
  ( 0, 'To serve as a symbol of power and authority', 1, 32), ( 0, 'To assist with farming and agriculture', 1, 32),
  ( 0, 'Ancient Rome', 1, 33),( 0, 'Ancient Greece', 1, 33),( 0, 'Ancient Egypt', 1, 33),( 1, 'Sumerian civilization', 1, 33),
  ( 0, 'Sculpture', 1, 34), ( 0, 'Architecture', 1, 34), ( 1, 'Painting', 1, 34), ( 0, 'Mosaic', 1, 34),
  ( 1, 'Olmec civilization', 1, 35), ( 0, 'Aztec civilization', 1, 35), ( 0, 'Incan civilization', 1, 35), ( 0, 'Mayan civilization', 1, 35),
  ( 0, 'Targeting', 1, 36), ( 1, 'Segmentation', 1, 36), ( 0, 'Positioning', 1, 36), ( 0, 'Differentiation', 1, 36),
  ( 0, 'Price', 1, 37),   ( 0, 'Product', 1, 37),  ( 0, 'Promotion', 1, 37),  ( 0, 'Placement', 1, 37),
  ( 0, 'Actualization gap', 1, 38), ( 1, 'Perception gap', 1, 38), ( 0, 'Value gap', 1, 38), ( 0, 'Quality gap', 1, 38),
  ( 0, 'Segmentation', 1, 39), ( 0, 'Targeting', 1, 39), ( 1, 'Positioning', 1, 39), ( 0, 'Differentiation', 1, 39),
  ( 1, 'Skimming pricing', 1, 40), ( 0, 'Penetration pricing', 1, 40), ( 0, 'Discount pricing', 1, 40), ( 0, 'Premium pricing', 1, 40),
  ( 0, 'Segmentation', 1, 41), ( 0, 'Targeting', 1, 41), ( 0, 'Positioning', 1, 41), ( 1, 'Promotion', 1, 41),
  ( 0, 'Introduction', 1, 42), ( 0, 'Growth', 1, 42), ( 0, 'Maturity', 1, 42), ( 1, 'Saturation', 1, 42),
  ( 0, 'Customer acquisition', 1, 43), ( 1, 'Customer retention', 1, 43), ( 0, 'Customer segmentation', 1, 43), ( 0, 'Customer targeting', 1, 43),
  ( 0, 'Concentrated marketing', 1, 44), ( 0, 'Undifferentiated marketing', 1, 44), ( 1, 'Differentiated marketing', 1, 44), ( 0, 'Niche marketing', 1, 44),
  ( 0, 'Television commercials', 1, 45), ( 0, 'Print ads', 1, 45), ( 0, 'Radio spots', 1, 45), ( 1, 'Digital ads', 1, 45),
  ( 0, 'Solo', 1, 46), ( 0, 'Duet', 1, 46), ( 1, 'Trio', 1, 46), ( 0, 'Quartet', 1, 46),
  ( 1, 'Crescendo', 1, 47), ( 0, 'Decrescendo', 1, 47), ( 0, 'Staccato', 1, 47), ( 0, 'Legato', 1, 47),
  ( 0, 'Flat', 1, 48), ( 0, 'Sharp', 1, 48), ( 1, 'Rest', 1, 48), ( 0, 'Fermata', 1, 48),
  ( 1, 'C Major', 1, 49), ( 0, 'G Major', 1, 49), ( 0, 'F Major', 1, 49), ( 0, 'Bb Major', 1, 49),
  ( 0, 'Adagio', 1, 50), ( 1, 'Allegro', 1, 50), ( 0, 'Andante', 1, 50), ( 0, 'Largo', 1, 50),
  ( 0, 'Virtue ethics', 1, 51), ( 0, 'Deontological ethics', 1, 51), ( 1, 'Utilitarianism', 1, 51), ( 0, 'Existentialism', 1, 51),
  ( 1, 'Duty', 1, 52), ( 0, 'Virtue', 1, 52), ( 0, 'Obligation', 1, 52), ( 0, 'Norm', 1, 52),
  ( 0, 'Pleasure', 1, 53), ( 0, 'Wealth', 1, 53), ( 1, 'Happiness', 1, 53), ( 0, 'Knowledge', 1, 53),
  ( 0, 'Utilitarianism', 1, 54), ( 0, 'Deontological ethics', 1, 54), ( 1, 'Virtue ethics', 1, 54), ( 0, 'Existentialism', 1, 54),
  ( 0, 'Metaethics', 1, 55), ( 0, 'Normative ethics', 1, 55), ( 1, 'Applied ethics', 1, 55), ( 0, 'Social ethics', 1, 55);
INSERT INTO `iquiz`.`question_subcategory`
(`question_id`,
 `subcategory_id`)
VALUES
  (1, 1), (31, 2), (41, 3),
  (2, 1), (32, 2), (42, 3),
  (3, 1), (33, 2), (43, 3),
  (4, 1), (34, 2), (44, 3),
  (5, 1), (35, 2), (45, 3),
  (6, 1), (36, 2), (46, 3),
  (7, 1), (37, 2), (47, 3),
  (8, 1), (38, 2), (48, 3),
  (9, 1), (39, 2), (49, 3),
  (10, 1), (50, 3),
  (11, 7), (51,8),
  (12, 7), (52,8),
  (13, 7), (53,8),
  (14, 7), (54,8),
  (15, 7), (55,8),
  (16, 7),
  (25, 7), (25,8),
  (17, 7),
  (18, 6),
  (19, 6),
  (20, 7), (26,8),
  (21, 7), (27,8),
  (22, 7), (28,8),
  (23, 7), (29,8),
  (24, 7), (30,8)
;
INSERT INTO `iquiz`.`lesson_question`
(`lesson_id`,
 `question_id`)
VALUES
  (1, 2), (1, 1), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
  (2, 11), (2, 12), (2, 13), (2, 14), (2, 15), (2, 16), (2, 17), (2, 18), (2, 19), (2, 20), (2, 21), (2, 22), (2, 23), (2, 24), (2, 25),
  (3, 2), (3, 1), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10),
  (4, 12), (4, 11), (4, 13), (4, 14), (4, 15), (4, 16), (4, 7), (4, 18), (4, 19), (4, 10),
  (5, 22), (5, 21), (5, 32), (5, 24), (5, 25), (5, 26), (5, 27), (5, 28), (5, 9), (5, 13),
  (6, 32), (6, 31), (6, 33), (6, 44), (6, 45), (6, 36), (6, 27), (6, 28), (6, 19), (6, 15),
  (7, 11), (7, 12), (7, 13), (7, 14), (7, 15), (7, 16), (7, 17), (7, 18), (7, 19), (7, 20), (7, 21), (7, 22), (7, 23), (7, 24), (7, 25),
  (8, 32), (8, 31), (8, 33), (8, 44), (8, 45), (8, 36), (8, 27), (8, 28), (8, 19), (8, 15),
  (9, 32), (9, 31), (9, 33), (9, 44), (9, 45), (9, 36), (9, 27), (9, 28), (9, 19), (9, 15),
  (10, 2), (10, 1), (10, 3), (10, 4), (10, 5), (10, 6), (10, 7), (10, 8), (10, 9), (10, 10),
  (11, 2), (11, 1), (11, 3), (11, 4), (11, 5), (11, 6), (11, 7), (11, 8), (11, 9), (11, 10),
  (12, 36), (12, 37), (12, 38), (12, 39), (12, 40), (12, 41), (12, 42), (12, 43),(12, 44), (12, 45),
  (13, 26), (13, 27), (13, 28), (13, 29), (13, 30), (13, 31), (13, 32), (13, 33), (13, 34), (13, 35),
  (14, 26), (14, 27), (14, 28), (14, 29), (14, 30), (14, 31), (14, 32), (14, 33), (14, 34), (14, 35),
  (15, 26), (15, 27), (15, 28), (15, 29), (15, 30), (15, 31), (15, 32), (15, 33), (15, 34), (15, 35),
  (16, 26), (16, 27), (16, 28), (16, 29), (16, 30), (16, 31), (16, 32), (16, 33), (16, 34), (16, 35),
  (17, 26), (17, 27), (17, 28), (17, 29), (17, 30), (17, 31), (17, 32), (17, 33), (17, 34), (17, 35),
  (18, 26), (18, 27), (18, 28), (18, 29), (18, 30), (18, 31), (18, 32), (18, 33), (18, 34), (18, 35),
  (19, 26), (19, 27), (19, 28), (19, 29), (19, 30), (19, 31), (19, 32), (19, 33), (19, 34), (19, 35),
  (20, 36), (20, 37), (20, 38), (20, 39), (20, 40), (20, 41), (20, 42), (20, 43),(20, 44), (20, 45),
  (21, 36), (21, 37), (21, 38), (21, 39), (21, 40), (21, 41), (21, 42), (21, 43),(21, 44), (21, 45),
  (22, 36), (22, 37), (22, 38), (22, 39), (22, 40), (22, 41), (22, 42), (22, 43),(22, 44), (22, 45),
  (23, 36), (23, 37), (23, 38), (23, 39), (23, 40), (23, 41), (23, 42), (23, 43),(23, 44), (23, 45),
  (24, 36), (24, 37), (24, 38), (24, 39), (24, 40), (24, 41), (24, 42), (24, 43),(24, 44), (24, 45),
  (25, 36), (25, 37), (25, 38), (25, 39), (25, 40), (25, 41), (25, 42), (25, 43),(25, 44), (25, 45),
  (26, 36), (26, 37), (26, 38), (26, 39), (26, 40), (26, 41), (26, 42), (26, 43),(26, 44), (26, 45),
  (27, 36), (27, 37), (27, 38), (27, 39), (27, 40), (27, 41), (27, 42), (27, 43),(27, 44), (27, 45),
  (28, 36), (28, 37), (28, 38), (28, 39), (28, 40), (28, 41), (28, 42), (28, 43),(28, 44), (28, 45),
  (29, 36), (29, 37), (29, 38), (29, 39), (29, 40), (29, 41), (29, 42), (29, 43),(29, 44), (29, 45),
  (30, 36), (30, 37), (30, 38), (30, 39), (30, 40), (30, 41), (30, 42), (30, 43),(30, 44), (30, 45),
  (31, 36), (31, 37), (31, 38), (31, 39), (31, 40), (31, 41), (31, 42), (31, 43),(31, 44), (31, 45), (31,51), (31,52), (31,53), (31,54), (31,55),
  (32, 36), (32, 37), (32, 38), (32, 39), (32, 40), (32, 41), (32, 42), (32, 43),(32, 44), (32, 45),
  (33, 36), (33, 37), (33, 38), (33, 39), (33, 40), (33, 41), (33, 42), (33, 43),(33, 44), (33, 45),
  (34, 36), (34, 37), (34, 38), (34, 39), (34, 40), (34, 41), (34, 42), (34, 43),(34, 44), (34, 45),
  (35, 36), (35, 37), (35, 38), (35, 39), (35, 40), (35, 41), (35, 42), (35, 43),(35, 44), (35, 45),
  (36, 36), (36, 37), (36, 38), (36, 39), (36, 40), (36, 41), (36, 42), (36, 43),(36, 44), (36, 45),
  (37, 36), (37, 37), (37, 38), (37, 39), (37, 40), (37, 41), (37, 42), (37, 43),(37, 44), (37, 45),
  (38, 36), (38, 37), (38, 38), (38, 39), (38, 40), (38, 41), (38, 42), (38, 43),(38, 44), (38, 45),
  (39, 36), (39, 37), (39, 38), (39, 39), (39, 40), (39, 41), (39, 42), (39, 43),(39, 44), (39, 45),
  (40, 36), (40, 37), (40, 38), (40, 39), (40, 40), (40, 41), (40, 42), (40, 43),(40, 44), (40, 45),
  (41, 36), (41, 37), (41, 38), (41, 39), (41, 40), (41, 41), (41, 42), (41, 43),(41, 44), (41, 45),
  (42, 36), (42, 37), (42, 38), (42, 39), (42, 40), (42, 41), (42, 42), (42, 43),(42, 44), (42, 45),
  (43, 36), (43, 37), (43, 38), (43, 39), (43, 40), (43, 41), (43, 42), (43, 43),(43, 44), (43, 45),
  (44, 36), (44, 37), (44, 38), (44, 39), (44, 40), (44, 41), (44, 42), (44, 43),(44, 44), (44, 45),
  (45, 36), (45, 37), (45, 38), (45, 39), (45, 40), (45, 41), (45, 42), (45, 43),(45, 44), (45, 45),
  (46, 36), (46, 37), (46, 38), (46, 39), (46, 40), (46, 41), (46, 42), (46, 43),(46, 44), (46, 45),
  (47, 36), (47, 37), (47, 38), (47, 39), (47, 40), (47, 41), (47, 42), (47, 43),(47, 44), (47, 45),
  (48, 36), (48, 37), (48, 38), (48, 39), (48, 40), (48, 41), (48, 42), (48, 43),(48, 44), (48, 45),
  (49, 36), (49, 37), (49, 38), (49, 39), (49, 40), (49, 41), (49, 42), (49, 43),(49, 44), (49, 45),
  (50, 36), (50, 37), (50, 38), (50, 39), (50, 40), (50, 41), (50, 42), (50, 43),(50, 44), (50, 45),
  (51, 2), (51, 1), (51, 3), (51, 4), (51, 5), (51, 6), (51, 7), (51, 8), (51, 9), (51, 10),
  (52, 36), (52, 37), (52, 38), (52, 39), (52, 40), (52, 41), (52, 42), (52, 43), (52, 44), (52, 45),
  (53, 26), (53, 27), (53, 28), (53, 29), (53, 30), (53, 31), (53, 32), (53, 33), (53, 34), (53, 35),
  (54, 26), (54, 27), (54, 28), (54, 29), (54, 30), (54, 31), (54, 32), (54, 33), (54, 34), (54, 35),
  (55, 26), (55, 27), (55, 28), (55, 29), (55, 30), (55, 31), (55, 32), (55, 33), (55, 34), (55, 35),
  (56, 26), (56, 27), (56, 28), (56, 29), (56, 30), (56, 31), (56, 32), (56, 33), (56, 34), (56, 35),
  (57, 26), (57, 27), (57, 28), (57, 29), (57, 30), (57, 31), (57, 32), (57, 33), (57, 34), (57, 35),
  (58, 26), (58, 27), (58, 28), (58, 29), (58, 30), (58, 31), (58, 32), (58, 33), (58, 34), (58, 35);

INSERT INTO `iquiz`.`lesson_subcategory`
(`lesson_id`,
 `subcategory_id`)
VALUES
  (1,1 );


INSERT INTO `iquiz`.`quizreview`
(`id`,
 `date_taken`,
 `duration`,
 `lesson_id`,
 `user_id`)
VALUES
  (1, '2023-06-01', '00:05:03' , 7, 9),
  (2, '2023-06-02', '00:03:03' , 7, 10),
  (3, '2023-06-02', '00:07:30' , 7, 12),
  (4, '2023-06-06', '00:06:03' , 1, 10),
  (5, '2023-06-06', '00:06:03' , 1, 9);

update iquiz.quizreview
set user_id = 10 where id=5;

update iquiz.quizreview
set user_id = 9 where id=2;

update iquiz.quizreview
set user_id = 10 where id=1;

update iquiz.quizreview
set user_id = 9 where id=5;

INSERT INTO `iquiz`.`quizreview_question`
(`bookmark`,
 `status`,
 `user_answer`,
 `quizreview_id`,
 `question_id`,
 `is_correct`)
VALUES
  (0, 1, '6', 5, 1, 1),
  (1, 1, '12x^2 - 10x + 2', 5, 2, 1),
  (0, 1,'x^2 + x + C' , 5, 3, 0),
  (1, 1, '6x' , 5, 4, 0),
  (1, 1, '3', 5, 5, 1),
  (0, 1, 'x = -1, x = 1/2', 5, 6, 1),
  (1, 1,'-sin(x)' , 5, 7, 0),
  (0, 1, '(2x)/(x^2 + 1)' , 5, 8, 1),
  (0, 1, 'e^x + C', 5, 9, 1),
  (0, 1, '-1/6', 5, 10, 1),
  (0, 1,'Income Statement' , 1, 11, 1),
  (0, 1, 'To show a companys assets, liabilities, and equity at a specific point in time', 1, 12, 1),
  (0, 1, 'Cash Flow Statement', 1, 13, 1),
  (0, 1, 'Assets - Liabilities = Net Income' , 1, 14, 0),
  (1, 1, 'Income Statement' , 1, 15, 0),
  (0, 1, 'Total Revenue - Cost of Goods Sold', 1, 16, 1),
  (0, 1, 'Cash Flow Statement', 1, 17, 1),
  (0, 1, 'Total Revenue - Cost of Goods Sold', 1, 18, 1),
  (1, 1,'Income Statement' , 1, 19, 1),
  (0, 1, 'Current Assets / Current Liabilities' , 1, 20, 1),
  (1, 1, 'Balance Sheet', 1, 21, 1),
  (0, 1, 'Net Income / Total Equity', 1, 22, 0),
  (0, 1, 'Cash Flow Statement', 1, 23, 1),
  (1, 1,'Total Liabilities / Total Equity' , 1, 24, 1),
  (0, 1, 'Retained Earnings Statement', 1, 25, 1),
  (0, 1, '6', 4, 1, 1),
  (1, 1, '12x^2 - 10x + 2', 4, 2, 1),
  (0, 1,'x^2 + x + C' , 4, 3, 0),
  (1, 1, '6x' , 4, 4, 0),
  (0, 1,'3' , 4, 5, 1),
  (0, 1, 'x = -1, x = 1/2', 4, 6, 1),
  (1, 1,'-sin(x)' , 4, 7, 0),
  (0, 1, '(2x)/(x^2 + 1)' , 4, 8, 1),
  (1, 1, 'e^x + C', 4, 9, 1),
  (0, 1, '-1/6', 4, 10, 1);
-- (0, 1,'Income Statement' , 4, 11, 1),
-- (0, 1, 'To show a companys assets, liabilities, and equity at a specific point in time', 4, 12, 1),
-- (0, 1, 'Cash Flow Statement', 4, 13, 1),
-- (0, 1, 'Assets - Liabilities = Net Income' , 4, 14, 0),
-- (1, 1, 'Income Statement' , 4, 15, 0),
-- (0, 1, 'Total Revenue - Cost of Goods Sold', 4, 16, 1),
-- (0, 1, 'Cash Flow Statement', 4, 17, 1),
-- (0, 1, 'Total Revenue - Cost of Goods Sold', 4, 18, 1),
-- (1, 1, 'Income Statement' , 4, 19, 1),
-- (0, 1, 'Current Assets / Current Liabilities' , 4, 20, 1),
-- (1, 1, 'Balance Sheet', 4, 21, 1),
-- (0, 1, 'Net Income / Total Equity', 4, 22, 0),
-- (0, 1, 'Cash Flow Statement', 4, 23, 1),
-- (1, 1,'Total Liabilities / Total Equity' , 4, 24, 1),
-- (0, 1, 'Retained Earnings Statement', 4, 25, 1);

-- drop table iquiz.quizreview_question;

INSERT INTO `iquiz`.`blogcategory`
(`name`, `status`)
VALUES
  ('Technology', true),
  ('Travel', true),
  ('Food', false);

-- drop table iquiz.blog;
INSERT INTO `iquiz`.`blog`
(`brief_info`, `content`, `featuring`, `last_updated`, `status`, `thumbnail`, `title`, `user_id`)
VALUES
  ('Brief info about technology', 'Content about technology', 1, '2023-06-05', 1, 'post4_thumbnail.png', 'Technology Blog', 14),
  ('Brief info about travel', 'Content about travel', 1, '2023-06-05', 1, 'post5_thumbnail.png', 'Travel Blog', 13),
  ('Brief info about food', 'Content about food', 1, '2023-06-05', 1, 'post3_thumbnail.png', 'Food Blog', 14),
  ('Brief info about education', 'Content about education', 1, '2023-06-05', 1, 'post2_thumbnail.png', 'Education Blog', 14),
  ('Brief info about life style', 'Content about life style', 0, '2023-07-12', 1, 'post5_thumbnail.png', 'Life Style Blog', 14);

--  drop table iquiz.blog_category;
INSERT INTO `iquiz`.`blog_category`
(`blog_id`, `category_id`)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 2),
  (5, 2);


INSERT INTO `iquiz`.`settings`
(
  `order`,
  `status`,
  `type`,
  `value`,
  `role_id`)
VALUES
  (1,true,'User Roles','admin',1),
  (2,true,'User Roles','expert',2),
  (3,true,'User Roles','sale',3),
  (4,true,'User Roles','marketing',4),
  (5,true,'User Roles','customer',5);

INSERT INTO `iquiz`.`settings`
(
  `order`,
  `status`,
  `type`,
  `value`,
  `blogcategory_id`)
VALUES
  (1,true,'Post Categories','Technology',1),
  (2,true,'Post Categories','Travel',2),
  (3,false,'Post Categories','Food',3);

INSERT INTO `iquiz`.`settings`
(
  `order`,
  `status`,
  `type`,
  `value`,
  `category_id`)
VALUES
  (1,true,'Subject Categories','Mathematics',1),
  (2,true,'Subject Categories','History',2),
  (3,true,'Subject Categories','Time Management',3),
  (4,true,'Subject Categories','Biology',4),
  (5,true,'Subject Categories','Computer Science',5);
INSERT INTO `iquiz`.`settings`
(
  `order`,
  `status`,
  `type`,
  `value`,
  `testtype_id`)
VALUES
  (1,true,'Test Types','Simulation Tests',1),
  (2,true,'Test Types','Lesson-Quiz Tests',2);


INSERT INTO `iquiz`.`settings`
(
  `order`,
  `status`,
  `type`,
  `value`,
  `level_id`)
VALUES
  (1,true,'Question Levels','easy',1),
  (2,true,'Question Levels','medium',2),
  (3,true,'Question Levels','difficult',3),
  (4,true,'Question Levels','challenging',4);

INSERT INTO `iquiz`.`settings`
(
  `order`,
  `status`,
  `type`,
  `value`,
  `lessontype_id`)
VALUES
  (1,true,'Lesson Types','quiz',1),
  (2,true,'Lesson Types','lession',2);

INSERT INTO `iquiz`.`settings`
(
  `order`,
  `status`,
  `type`,
  `value`,
  `dimension_id`)
VALUES
  (1,true,'Subject Dimension','Business',1),
  (2,true,'Subject Dimension','Process',2),
  (3,true,'Subject Dimensions','People',3),
  (4,true,'Subject Dimension','Initiating',4),
  (5,true,'Subject Dimension','Planning',5),
  (6,true,'Subject Dimension','Executing',6);

insert into `iquiz`.`lesson_properties` (lesson_id, subcategory_id, dimension_id , sort_by, no_question)
values (1, 1, null, 0, 4),
       (1, 2, null, 0, 4),
       (1, 3, null, 0, 2),
       (2, null, 1, 1, 4),
       (2, null, 2, 1, 3),
       (2, null, 3, 1, 3),
       (3, null, 4, 2, 3),
       (3, null, 5, 2, 3),
       (3, null, 6, 2, 4);
insert into `iquiz`.`lesson_question` (lesson_id, question_id)
values  (1, 11),
        (1, 12),
        (1, 13),
        (1, 14),
        (1, 15),
        (1, 16),
        (1, 17),
        (1, 18),
        (1, 19),
        (1, 20),
        (1, 21),
        (1, 22),
        (1, 23),
        (1, 24),
        (1, 25),
        (1, 26),
        (1, 27),
        (1, 28),
        (1, 29),
        (1, 30),
        (1, 31),
        (1, 32),
        (1, 33),
        (1, 34),
        (1, 35),
        (1, 36),
        (1, 37),
        (1, 38),
        (1, 39),
        (1, 40),
        (1, 41),
        (1, 42),
        (1, 43),
        (2, 44),
        (2, 45),
        (2, 46),
        (2, 47),
        (2, 48),
        (2, 49),
        (2, 50),
        (2, 51),
        (2, 52),
        (2, 53),
        (2, 54),
        (2, 55),
        (2, 26),
        (2, 27),
        (2, 28),
        (2, 29),
        (2, 30),
        (2, 31),
        (2, 32),
        (2, 33),
        (2, 34),
        (2, 35),
        (2, 36),
        (2, 37),
        (2, 38),
        (2, 39),
        (2, 40),
        (2, 41),
        (2, 42),
        (2, 43);

insert into `iquiz`.`subject_dimension` (subject_id, dimension_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6);
