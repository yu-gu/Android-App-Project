CREATE TABLE Food_Table(
id INT,
user_id INT,
category INT,
food_name VARCHAR(20), 
protein NUMERIC,
fat NUMERIC,
calories NUMERIC,
PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES User_Table(id)
);


CREATE TABLE User_Table(
id INT,
user_name VARCHAR(10),
password VARCHAR(10),
height INT,
gender VARCHAR(1),
birthday DATE,
PRIMARY KEY(id)
);

CREATE TABLE Weight_Table(
id INT,
user_id INT,
weight_date date,
PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES User_Table(id)
);

CREATE TABLE diet(
id INT,
food_id INT,
user_id INT,
date DATE,
meal_type CHAR(1),
PRIMARY KEY(id),
FOREIGN KEY(food_id) REFERENCES Food_Table(id),
FOREIGN KEY(user_id) REFERENCES User_Table(id)
);

CREATE TABLE Exercise_Daily(
id INT,
exercise_id INT NOT NULL,
user_id INT NOT NULL,
date DATE,
duration NUMERIC,
PRIMARY KEY(id),
FOREIGN KEY(exercise_id) REFERENCES Exercise_Table(id),
FOREIGN KEY(user_id) REFERENCES User_Table(id)
);

CREATE TABLE Exercise_Table(
id INT,
name CHAR(50),
energy_consumption INT,
PRIMARY KEY(id));

DROP TABLE Exercise_Daily;
DROP TABLE Weight_Table;
DROP TABLE User_Table;
DROP TABLE Food_Table;
DROP TABLE diet;
DROP TABLE Exercise_Table;



INSERT INTO Food_Table(id, user_id, category, food_name, protein, fat, calories)
VALUES (1,1234,1,'skim milk',20,10,300);
INSERT INTO Food_Table(id, user_id, category, food_name, protein, fat, calories)
VALUES (2,1234,1,'20%fat milk',20,15,600);
INSERT INTO Food_Table(id, user_id, category, food_name, protein, fat, calories)
VALUES (3,1234,1,'10%fat milk',20,10,300);

INSERT INTO User_Table(id, user_name, password, height, gender, birthday)
VALUES (1,'Bob','Bob1234',183,'M',STR_TO_DATE('1-01-2012', '%d-%m-%Y'));
INSERT INTO User_Table(id, user_name, password, height, gender, birthday)
VALUES (1234,'Mike','mike',156,'M',STR_TO_DATE('1-03-1994', '%d-%m-%Y'));
INSERT INTO User_Table(id, user_name, password, height, gender, birthday)
VALUES (3,'Kate','ddml123',175,'F',STR_TO_DATE('10-03-1997', '%d-%m-%Y'));
INSERT INTO User_Table(id, user_name, password, height, gender, birthday)
VALUES (4,'Daryl','DDD000',156,'M',STR_TO_DATE('21-03-1987', '%d-%m-%Y'));


INSERT INTO Exercise_Table(id,name,energy_consumption)
VALUES (1,'Jogging', 30),(2,'barbell',400),(3,'volleyball',300);


INSERT INTO Exercise_Daily(id, exercise_id, user_id, date, duration)
VALUES (1,1,1234,STR_TO_DATE('1-03-1994', '%d-%m-%Y'),0.5);

INSERT INTO diet(id, food_id, user_id, date, meal_type)
VALUES (1,1,1234,STR_TO_DATE('1-03-1994', '%d-%m-%Y'),'B');


INSERT INTO Weight_Table(id,user_id,weight_date)
VALUE (1,1234,STR_TO_DATE('1-03-2017', '%d-%m-%Y'));

