DROP TABLE IF EXISTS Bar_Staff;

CREATE TABLE Bar_Staff(staffID int NOT NULL AUTO_INCREMENT,
                       first_name VARCHAR(30),
                       last_Name VARCHAR(30),
                       ratePerHour double,
                       work_hours int,
                       email VARCHAR(70));


INSERT INTO Bar_Staff VALUES (1, "Manager", "Tanish", "Afre", 15.50, 75, "ta@gmail.com");
INSERT INTO Bar_Staff VALUES (2, "Supervisor", "John", "Wane", 14.50, 50, "jw@gmail.com");
INSERT INTO Bar_Staff VALUES (3, "Assistant", "Bruce", "Banner", 11.75, 20, "bb@gmail.com");
INSERT INTO Bar_Staff VALUES (4, "Bar Staff", "Adolf", "Hitler", 10.50, 15, "ah@gmail.com");
INSERT INTO Bar_Staff VALUES (5, "Bar Staff", "Vladimir", "Putin", 10.25, 20, "vp@gmail.com");
INSERT INTO Bar_Staff VALUES (6, "Bar Staff", "Yug", "Vamos", 10.25, 25, "yv@gmail.com");
INSERT INTO Bar_Staff VALUES (7, "Bar Staff", "Alex", "Costa", 10.80, 30, "ac@gmail.com");
INSERT INTO Bar_Staff VALUES (8, "Floor Staff", "Alexa", "Nosiri", 11.50, 15, "an@gmail.com");
INSERT INTO Bar_Staff VALUES (9, "Floor Staff", "Tom", "Holland", 10.50, 20, "th@gmail.com");
INSERT INTO Bar_Staff VALUES (10, "Floor Staff", "Stephen", "Strange", 12.00, 25, "ss@gmail.com");
INSERT INTO Bar_Staff VALUES (11, "Floor Staff", "Seb", "Dovel", 12.00, 30, "sd@gmail.com");
