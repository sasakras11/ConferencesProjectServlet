INSERT INTO locations(area, maxPeople, address) VALUES(5000,10000,'Petra 7a');
INSERT INTO locations(area, maxPeople, address) VALUES(1000,1000,'Mogilnoho 3b');
INSERT INTO locations(area, maxPeople, address) VALUES(50550,11110,'Alexa 76a');
INSERT INTO locations(area, maxPeople, address) VALUES(50310,10500,'Vlada 72a');

insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('IT-WEEK',PARSEDATETIME('01/05/2020', 'dd/MM/yyyy'),1,2000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('BEER-FEST',PARSEDATETIME('02/05/2020', 'dd/MM/yyyy'),1,150,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('TWENY FEST',PARSEDATETIME('03/05/2010', 'dd/MM/yyyy'),2,42150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('DIRECTOR-WEEK',PARSEDATETIME('04/05/2050', 'dd/MM/yyyy'),3,66000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('IT-CONFERENCE',PARSEDATETIME('05/05/2040', 'dd/MM/yyyy'),1,100,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('LAMA FEST',PARSEDATETIME('06/05/2030', 'dd/MM/yyyy'),2,42150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('KITTY FEST',PARSEDATETIME('07/05/2019', 'dd/MM/yyyy'),3,42150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('EDA FEST',PARSEDATETIME('08/05/2070', 'dd/MM/yyyy'),2,42150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('EKO FEST',PARSEDATETIME('08/05/2050', 'dd/MM/yyyy'),2,42150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('HALO FEST',PARSEDATETIME('08/05/2040', 'dd/MM/yyyy'),2,43150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('JOHN FEST',PARSEDATETIME('08/05/2030', 'dd/MM/yyyy'),2,150044,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('ALEX FEST',PARSEDATETIME('08/05/2009', 'dd/MM/yyyy'),2,15,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('AMB FEST',PARSEDATETIME('08/05/2010', 'dd/MM/yyyy'),2,150,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('BMV FEST',PARSEDATETIME('08/05/2011', 'dd/MM/yyyy'),2,150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('ADIDAS FEST',PARSEDATETIME('08/05/2012', 'dd/MM/yyyy'),3,150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('XIAOMI FEST',PARSEDATETIME('08/05/2013', 'dd/MM/yyyy'),1,15000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('DAG FEST',PARSEDATETIME('08/05/2014', 'dd/MM/yyyy'),1,6150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('CAT FEST',PARSEDATETIME('08/05/2015', 'dd/MM/yyyy'),3,5150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('BOOTLE FEST',PARSEDATETIME('08/05/2016', 'wdd/MM/yyyy'),3,4150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('CAR FEST',PARSEDATETIME('08/05/2017', 'dd/MM/yyyy'),2,42150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('WAR FEST',PARSEDATETIME('08/05/2018', 'dd/MM/yyyy'),1,8150000,0);
insert into conferences(name, date, location_id, registered_people, visited_people)VALUES ('ASR FEST',PARSEDATETIME('08/05/2019', 'dd/MM/yyyy'),2,9150000,0);


insert  into users(username, password, role) VALUES ('alex','pass','ADMIN');
insert  into users(username, password, role) VALUES ('ivan','password','SPEAKER');
insert  into users(username, password, role) VALUES ('ira','pass22','MODERATOR');
insert  into users(username, password, role) VALUES ('olga','sass','SPEAKER');
insert  into users(username, password, role) VALUES ('egor','erpo','MODERATOR');
insert  into users(username, password, role) VALUES ('maxim','maxes','VISITOR');
insert  into users(username, password, role) VALUES ('david','davidov','VISITOR');
insert  into users(username, password, role) VALUES ('dima','dima12','VISITOR');
insert  into users(username, password, role) VALUES ('katerina','katerina04','VISITOR');

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

insert into speech_id_user_id_relation(speech_id, user_id) VALUES (1,1);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (2,1);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (3,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (4,2);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (5,3);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (6,3);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (7,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (8,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (9,5);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (10,5);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (11,6);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (12,7);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (13,8);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (14,8);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (15,9);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (16,7);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (17,3);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (18,4);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (19,5);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (20,6);
insert into speech_id_user_id_relation(speech_id, user_id) VALUES (21,7);



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


INSERT INTO ratings(rating,speaker_id) values(15,2);
INSERT INTO ratings(rating,speaker_id) values(8,4);