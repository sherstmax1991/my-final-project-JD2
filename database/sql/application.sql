DROP DATABASE credit_applications;
CREATE DATABASE credit_applications;
USE credit_applications;

create table credits
(
  id BIGINT AUTO_INCREMENT,
  interest_type VARCHAR(31) NOT NULL,
  guarantors INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  fixed_interest double NULL,
  variable_interest double NULL,
  PRIMARY KEY (id)
);

create table clients
(
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  login VARCHAR(30) UNIQUE,
  birthday DATE NOT NULL,
  gender ENUM ('MALE', 'FEMALE') NOT NULL,
  rating ENUM ('BAD', 'PROBLEM', 'NORMAL', 'GOOD', 'VIP') NOT NULL,
  marital_status ENUM ('SINGLE', 'DIVORCED', 'MARRIED') NOT NULL,
  PRIMARY KEY (id)
);

create table children
(
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  birthday DATE NOT NULL,
  gender ENUM ('MALE', 'FEMALE') NOT NULL,
  PRIMARY KEY (id)
);

create table applications
(
  id BIGINT AUTO_INCREMENT,
  date date NOT NULL,
  client_id BIGINT NULL,
  credit_id BIGINT NULL,
  income INT NOT NULL,
  loan_period INT NOT NULL,
  pledge INT NOT NULL,
  sum INT NOT NULL,
  quality ENUM ('BAD', 'GOOD', 'UNKNOWN') NOT NULL,
  scoring_resolution ENUM ('BAD', 'GOOD', 'UNKNOWN') NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (client_id) REFERENCES clients(id),
  FOREIGN KEY (credit_id) REFERENCES credits(id)
);

create table clients_children
(
  client_id BIGINT NOT NULL,
  child_id BIGINT NOT NULL,
  PRIMARY KEY (client_id, child_id),
  FOREIGN KEY (client_id) REFERENCES clients(id),
  FOREIGN KEY (child_id) REFERENCES children(id)
);

INSERT INTO credit_applications.children (id, first_name, last_name, birthday, gender) VALUES (1, 'Ольга', 'Тофелюк', '2018-06-01', 'FEMALE');

INSERT INTO credit_applications.clients (id, first_name, last_name, login, birthday, gender, rating, marital_status) VALUES (1, 'Виталий', 'Тофелюк', 'kloyn', '1991-12-01', 'MALE', 'GOOD', 'SINGLE');
INSERT INTO credit_applications.clients (id, first_name, last_name, login, birthday, gender, rating, marital_status) VALUES (2, 'Максим', 'Шерстобитов', 'sherstmax1991', '1991-01-29', 'MALE', 'BAD', 'SINGLE');
INSERT INTO credit_applications.clients (id, first_name, last_name, login, birthday, gender, rating, marital_status) VALUES (3, 'Вацлава', 'Шерстобитова', 'valya', '1964-08-27', 'FEMALE', 'GOOD', 'MARRIED');

INSERT INTO credit_applications.clients_children (client_id, child_id) VALUES (1, 1);

INSERT INTO credit_applications.credits (id, interest_type, guarantors, title, fixed_interest, variable_interest) VALUES (1, 'FIXED', 0, 'Standart credit', 14, null);
INSERT INTO credit_applications.credits (id, interest_type, guarantors, title, fixed_interest, variable_interest) VALUES (2, 'VARIABLE', 1, 'Unusual credit', null, 3);

INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (1, '2018-02-01', 1, 1, 3000, 12, 3000, 4000, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (2, '2018-02-02', 1, 2, 3000, 24, 1500, 2000, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (3, '2018-02-03', 1, 1, 3000, 12, 7000, 10000, 'UNKNOWN', 'BAD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (4, '2018-02-04', 2, 1, 800, 12, 300, 400, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (5, '2018-02-05', 2, 1, 800, 24, 150, 200, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (6, '2018-02-06', 2, 1, 800, 12, 700, 1000, 'UNKNOWN', 'BAD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (7, '2018-02-07', 2, 2, 800, 12, 300, 400, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (8, '2018-02-08', 2, 2, 800, 12, 150, 200, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (9, '2018-02-09', 1, 1, 3000, 24, 700, 1000, 'UNKNOWN', 'BAD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (10, '2018-02-10', 3, 2, 600, 12, 300, 400, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (11, '2018-02-10', 3, 1, 600, 24, 150, 200, 'UNKNOWN', 'GOOD');
INSERT INTO credit_applications.applications (id, date, client_id, credit_id, income, loan_period, pledge, sum, quality, scoring_resolution) VALUES (12, '2018-02-10', 3, 1, 600, 18, 700, 1000, 'UNKNOWN', 'BAD');