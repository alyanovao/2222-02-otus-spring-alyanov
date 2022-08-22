--password:"password"
insert into users(id, login, salt, hash, role) values (1, 'user', 'spring', '$2a$10$ASASQEe6LmQZHx86MOHHteQHBZzSBMkesDzPeIsS4Y4TFfRrVlsni', 'ROLE_USER');
insert into users(id, login, salt, hash, role) values (2, 'admin', 'security', '$2a$10$EIi6WhVE5Kh7Oh8Z26hPj.0PNjQfXQ/h0R7HnHVu5CvcUeBzOgZgW', 'ROLE_ADMIN');