DROP TABLE IF EXISTS user_id_conference_id_relation;

DROP TABLE IF EXISTS speech_id_conference_id_relation;

DROP TABLE IF EXISTS speech_id_user_id_relation;

DROP TABLE IF EXISTS speeches;

DROP TABLE IF EXISTS conferences;

DROP table  if exists ratings;


DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS locations;


CREATE TABLE users
(
    user_id  INT auto_increment NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    role   VARCHAR(30) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE locations
(
    location_id INT auto_increment NOT NULL,
    area        INT NOT NULL,
    maxPeople   INT NOT NULL,
    address     VARCHAR(50) NOT NULL,
    PRIMARY KEY (location_id)
);

CREATE TABLE conferences
(
    conference_id     INT auto_increment NOT NULL,
    name              VARCHAR(100) NOT NULL,
    date             DATE NOT NULL,
    location_id       INT NOT NULL,
    registered_people INT NOT NULL,
    visited_people    INT,
    PRIMARY KEY(conference_id),
    FOREIGN KEY (location_id) REFERENCES locations(location_id)
);

CREATE TABLE speeches
(
    speech_id       INT auto_increment NOT NULL,
    topic           VARCHAR(60) NOT NULL,
    suggested_topic VARCHAR(60),
    start_hour      INT NOT NULL,
    end_hour        INT NOT NULL,
    conference_id   INT NOT NULL,
    speaker_id      INT NOT NULL,
    PRIMARY KEY (speech_id),
    FOREIGN KEY (conference_id) REFERENCES conferences(conference_id) ON UPDATE
        CASCADE ON DELETE CASCADE,
    FOREIGN KEY (speaker_id) REFERENCES users(user_id) ON UPDATE CASCADE ON
        DELETE CASCADE
);

CREATE TABLE user_id_conference_id_relation
(
    id            INT auto_increment NOT NULL,
    user_id       INT NOT NULL,
    conference_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (conference_id) REFERENCES conferences(conference_id) ON UPDATE
        CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE
        CASCADE
);

CREATE TABLE speech_id_user_id_relation
(
    id        INT auto_increment NOT NULL,
    speech_id INT NOT NULL,
    user_id   INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (speech_id) REFERENCES speeches(speech_id) ON UPDATE CASCADE ON
        DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE
        CASCADE
);

create table ratings(
                        rating_id int auto_increment not null,
                        rating int not null,
                        speaker_id int not null,
                        primary key(rating_id),
                        foreign key (speaker_id) references users(user_id)
);
