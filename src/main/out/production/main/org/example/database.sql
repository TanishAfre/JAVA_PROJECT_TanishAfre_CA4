DROP TABLE IF EXISTS Bar_Staff;

CREATE TABLE `ca_bar`.`Bar_Staff` ( `staff_id` INT NOT NULL ,
                                    `staff_position` VARCHAR(50) NOT NULL ,
                                    `first_name` CHAR(50) NOT NULL ,
                                    `last_name` CHAR(50) NOT NULL ,
                                    `rate_per_hour` DOUBLE NOT NULL ,
                                    `work_hours` INT NOT NULL ,
                                    `email` VARCHAR(50) NOT NULL,
                                     PRIMARY KEY (`staff_id`(3))) ENGINE = InnoDB;


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
