DROP TABLE IF EXISTS EMPLOYEES;

CREATE TABLE EMPLOYEES(
    ids INT AUTO_INCREMENT PRIMARY KEY,
    first_names varchar(250) not null,
    last_names varchar(250) not null,
    emails varchar(250) default null
);