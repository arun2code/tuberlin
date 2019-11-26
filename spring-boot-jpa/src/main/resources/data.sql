DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_Name VARCHAR(250) NOT NULL,
  last_Name VARCHAR(250) NOT NULL,
  age int,
  department VARCHAR(250)
);

--insert into user(first_name, last_name, age, department) values 
--('Arun', 'bhardwaj', 32, 'IT');