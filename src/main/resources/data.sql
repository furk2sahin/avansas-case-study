INSERT INTO users(id, name, surname, email, password, phone_number, birth_date, role, create_date)
values (1, 'Furkan', 'ŞAHİN', 'furkannsahin.7@gmail.com', 'password', '05123154256', '1995-07-12T13:17:09.913+00:00', 1, current_timestamp);

INSERT INTO roles(authority, user_id) VALUES ('ROLE_ADMIN', 1);