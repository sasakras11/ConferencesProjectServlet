INSERT INTO locations(area, maxPeople, address) VALUES(5000,10000,'Petra 7a');
INSERT INTO locations(area, maxPeople, address) VALUES(1000,1000,'Mogilnoho 3b');
INSERT INTO locations(area, maxPeople, address) VALUES(50550,11110,'Alexa 76a');
INSERT INTO locations(area, maxPeople, address) VALUES(50310,10500,'Vlada 72a');

insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('IT-WEEK','03.10.2000 17:00',1,2000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('BEER-FEST','11.05.2020 7:20',1,150,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('TWENY FEST','03.10.2019 16:03',2,150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('DIRECTOR-WEEK','13.12.2202 16:30',3,66000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('IT-CONFERENCE','23.01.1999 22:30',1,100,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('LAMA FEST','16.02.2003 14:03',2,150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('KITTY FEST','17.05.2123 03:55',3,150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('EDA FEST','23.07.2022 17:55',2,150000,0);


insert  into users(username, password, status) VALUES ('alex','pass','ADMIN');
insert  into users(username, password, status) VALUES ('ivan','password','SPEAKER');
insert  into users(username, password, status) VALUES ('ira','pass22','MODERATOR');
insert  into users(username, password, status) VALUES ('olga','sass','SPEAKER');
insert  into users(username, password, status) VALUES ('egor','erpo','MODERATOR');
insert  into users(username, password, status) VALUES ('maxim','maxes','VISITOR');
insert  into users(username, password, status) VALUES ('david','davidov','VISITOR');
insert  into users(username, password, status) VALUES ('dima','dima12','VISITOR');
insert  into users(username, password, status) VALUES ('katerina','katerina04','VISITOR');

insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('vlada Ukrainy','',2,5,2,1);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('Pro nas','',1,2,4,1);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('O stb','',2,5,2,1);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('vugoranie','',3,5,2,2);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('za4em v it ','',4,5,4,2);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('kak stat milionerom','',6,5,2,2);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('vstre4a s ivanom groznum','',7,5,4,3);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('top filmov','',8,5,2,3);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('100 ottenkov belogo','',9,5,4,3);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('vlada Rosiii','',10,5,2,3);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('O garri pottere','',11,5,4,3);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('kak stat veselum','',12,5,2,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('top 7 navukov','',13,5,4,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('konferentsia EPAM','',14,5,2,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('8 geroev','',15,5,4,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('Rama v 10','',16,5,2,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('eda lonferentsia','',20,21,2,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('vstre4a s Vladimirov','',22,23,4,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('Vstre4a s Olegom Velikim','',23,24,2,5);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('vlada Belarusi','',0,1,4,5);
insert into speeches(topic, suggested_topic, start_hour, end_hour, speaker_id, conference_id) values ('Ser Man Meeting','',2,3,4,5);

insert into speech_id_user_id_relation(speech_id, user_id) VALUES (1,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (2,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (3,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (4,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (5,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (6,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (7,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (8,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (9,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (10,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (11,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (12,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (13,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (14,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (15,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (16,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (17,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (18,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (19,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (20,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (21,4);



insert into user_id_conference_id_relation(user_id, conference_id) values (1,1);
insert into user_id_conference_id_relation(user_id, conference_id) values (1,2);
insert into user_id_conference_id_relation(user_id, conference_id) values (1,3);
insert into user_id_conference_id_relation(user_id, conference_id) values (2,4);
insert into user_id_conference_id_relation(user_id, conference_id) values (2,5);
insert into user_id_conference_id_relation(user_id, conference_id) values (2,6);
insert into user_id_conference_id_relation(user_id, conference_id) values (3,7);
insert into user_id_conference_id_relation(user_id, conference_id) values (3,1);
insert into user_id_conference_id_relation(user_id, conference_id) values (3,2);
insert into user_id_conference_id_relation(user_id, conference_id) values (3,3);
insert into user_id_conference_id_relation(user_id, conference_id) values (4,4);
insert into user_id_conference_id_relation(user_id, conference_id) values (4,5);
insert into user_id_conference_id_relation(user_id, conference_id) values (4,6);
insert into user_id_conference_id_relation(user_id, conference_id) values (5,7);
insert into user_id_conference_id_relation(user_id, conference_id) values (5,1);
insert into user_id_conference_id_relation(user_id, conference_id) values (5,2);
insert into user_id_conference_id_relation(user_id, conference_id) values (5,3);
insert into user_id_conference_id_relation(user_id, conference_id) values (6,4);
insert into user_id_conference_id_relation(user_id, conference_id) values (6,5);
insert into user_id_conference_id_relation(user_id, conference_id) values (6,6);
insert into user_id_conference_id_relation(user_id, conference_id) values (7,7);
insert into user_id_conference_id_relation(user_id, conference_id) values (7,8);
insert into user_id_conference_id_relation(user_id, conference_id) values (7,1);
insert into user_id_conference_id_relation(user_id, conference_id) values (7,2);
insert into user_id_conference_id_relation(user_id, conference_id) values (7,3);
insert into user_id_conference_id_relation(user_id, conference_id) values (8,4);
insert into user_id_conference_id_relation(user_id, conference_id) values (8,5);
insert into user_id_conference_id_relation(user_id, conference_id) values (8,6);
insert into user_id_conference_id_relation(user_id, conference_id) values (8,7);
insert into user_id_conference_id_relation(user_id, conference_id) values (9,8);
insert into user_id_conference_id_relation(user_id, conference_id) values (9,1);
insert into user_id_conference_id_relation(user_id, conference_id) values (9,2);
insert into user_id_conference_id_relation(user_id, conference_id) values (9,4);
insert into user_id_conference_id_relation(user_id, conference_id) values (9,5);
insert into user_id_conference_id_relation(user_id, conference_id) values (9,6);


