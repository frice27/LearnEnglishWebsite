DROP TABLE IF EXISTS client;

CREATE TABLE client (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        FIRSTNAME VARCHAR(50),
                        LASTNAME VARCHAR(50),
                        EMAIL VARCHAR(100),
                        PASSWORD VARCHAR(255)
);

INSERT INTO client (FIRSTNAME, LASTNAME, EMAIL, PASSWORD)
VALUES ('Neptune', 'Some', 'neptune@gmail.com', '888');
