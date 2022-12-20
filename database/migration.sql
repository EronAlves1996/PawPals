USE pawpals;

CREATE TABLE animal(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    color VARCHAR(10) NOT NULL,
    is_adult BOOLEAN NOT NULL,
    size VARCHAR(10) NOT NULL,
    type VARCHAR(3) NOT NULL
);

CREATE TABLE user(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    is_looking_for_animal BOOLEAN NOT NULL,
    animal_color VARCHAR(10),
    animal_is_adult BOOLEAN,
    animal_size VARCHAR(10),
    animal_type VARCHAR(3)
);