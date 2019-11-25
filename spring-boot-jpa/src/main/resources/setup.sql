create database mydb; -- Creates the  database
create user 'root'@'%' identified by 'admin'; -- Creates the user
grant all on mydb.* to 'root'@'%'; -- Grants all privileges to the new user on the database