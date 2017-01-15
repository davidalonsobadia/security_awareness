--INSERT INTO user (username,email, password, activated) VALUES ('admin', 'admin@mail.me', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1', true);
--INSERT INTO user (username,email, password, activated) VALUES ('user', 'user@mail.me', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', true);
--INSERT INTO user (username,email, password, activated) VALUES ('rajith', 'rajith@abc.com', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', true);
--
--INSERT INTO authority (name) VALUES ('ROLE_USER');
--INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
--
--INSERT INTO user_authority (username,authority) VALUES ('rajith', 'ROLE_USER');
--INSERT INTO user_authority (username,authority) VALUES ('user', 'ROLE_USER');
--INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_USER');
--INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_ADMIN');

-- password is 123456
--INSERT INTO user(first_name, last_name, password, username) 
--	VALUES ('David', 'Alonso', '$2a$06$d1ykHl7qf8bfB9xrqpdXze0LNsa01XaItCvKdwqEHw51lm4oXNdr6', 'alonso_50');
--INSERT INTO user(first_name, last_name, password, username)
--	VALUES ('Sergi', 'Alonso', '$2a$06$d1ykHl7qf8bfB9xrqpdXze0LNsa01XaItCvKdwqEHw51lm4oXNdr6', 'sergi_50');
	
	
	
----------------------
	DROP TABLE IF EXISTS auth_details;
CREATE TABLE auth_details (
  username varchar(256),
  password varchar(256),
  PRIMARY KEY (username)
) ENGINE=InnoDB ;

DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id varchar(256) DEFAULT NULL,
  token blob,
  authentication_id varchar(256) DEFAULT NULL,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  authentication blob,
  refresh_token varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id varchar(256) DEFAULT NULL,
  token blob,
  authentication blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;