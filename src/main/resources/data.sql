insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (9998, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'BB');

insert into public.users (id, age, email, first_name, last_name, password, phone, role) VALUES (9998, 35, 'test1@gmail.com', 'test', 'test', '$2a$10$DZhaQBnYmtHs9tG5qLcexOEZ4G/Uo3hCqusJj135bzx.DSMq6lgKu', '237591231', 'ADMIN');

insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (9998, '2021-05-17 22:15:13.000000', 'SUBMITTED', 9998, 9998);