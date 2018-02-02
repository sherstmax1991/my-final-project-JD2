create database credit_applications;
use credit_applications;

CREATE TABLE credits (
  id INT AUTO_INCREMENT,
  title VARCHAR(30),
  guarantors INT NOT NULL,
  interest_rate BIGINT NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO credits (title, guarantors, interest_rate) VALUES ('Profitable', 2, 14);
INSERT INTO credits (title, guarantors, interest_rate) VALUES ('Optima', 1, 15);
INSERT INTO credits (title, guarantors, interest_rate) VALUES ('Express', 0, 16);