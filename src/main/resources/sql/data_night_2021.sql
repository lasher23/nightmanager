delete from user_role_assignment;
delete from application_user;
delete from role;
delete from games;
delete from teams;
delete from categories;
delete from chats;
delete from halls;

-- User: admin
-- Password: password
insert into application_user(id, password,username) values (1,'$2a$10$lWMGhesQi8rACnnS1x9XpOZymhALQdKg1eMzDxy0mKhRGsinKNFZm','admin');
insert into role(id,name) values (1,'ADMIN');
insert into user_role_assignment( fk_role,fk_user) values (1,1);

-- User: referee
-- Password: password
insert into application_user(id,password,username) values (2,'$2a$10$lWMGhesQi8rACnnS1x9XpOZymhALQdKg1eMzDxy0mKhRGsinKNFZm','referee' );
insert into role(id,name) values (2,'REFEREE');
insert into user_role_assignment(fk_role,fk_user) values (2,2);

INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (1000,'Kategorie A',NULL,0,2,NULL);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (2000,'Kategorie A Winner',NULL,5,0,1000);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (3000,'Kategorie A Loser',NULL,5,0,1000);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (4000,'Kategorie B',NULL,5,1,NULL);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (5000,'Kategorie B 1',NULL,1,1,4000);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (6000,'Kategorie B 2',NULL,1,1,4000);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (7000,'Kategorie C','Frauen Tore zählen nur Einfach!!!',5,1,NULL);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (8000,'Kategorie C 1','Frauen Tore zählen nur Einfach!!!',1,1,7000);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (9000,'Kategorie C 2','Frauen Tore zählen nur Einfach!!!',1,1,7000);

INSERT INTO halls (id,name) VALUES (1,'Inpuls');
INSERT INTO halls (id,name) VALUES (2,'Matte');

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (9999, 'Pause', true, true, 0, 3000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1000,	'Töffli Club Schongau',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1001,	'Breiter als erlaubt!',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1002,	'Ab is Chränzli',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1003,	'Schinkliverteidiger',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1004,	'Rambazamba v2',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1005,	'Unihockeycenter',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1006,	'Spycherrümli Chöupu',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1007,	'Badwannepirate',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1008,	'Team Wasserschlangen',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1009,	'Chronisch überhopft',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1010,	'Sportsfreunde Chiller',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1011,	'Zockerwatte',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1012,	'Nottu 1',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1013,	'Pfadi Ohmstu',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1014,	'Steftetropp',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1015,	'Stärnejäger',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1016,	'Fichte',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1017,	'De Pestalozzi esch gstorbe',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1018,	'Die Babas',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1019,	'Corona',false,false,0,1000);













INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5001,	'Mü-Ha Ranchers'            ,false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5002,	'Top Angebot'               ,false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5003,	'Bäribrommer'               ,false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5004,	'Nottu Elite'               ,false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5005,	'd Musig'                   ,false,false,0,5000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6001,	'Stägis'                    ,false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6002,	'New SuugerPower'           ,false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6003,	'Figaro Kings'              ,false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6004,	'Märebrätscher Sämpech'     ,false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6005,	'Lüütertüter Höudisriede'   ,false,false,0,6000);


INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8001,	'Dobros'                                ,false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8002,	'Turbotonytötscher 69'                  ,false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8003,	'Don Promillo'                          ,false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8004,	'Les luchadores con bolas muy rapido'   ,false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8005,	'Team OB'                               ,false,false,0,8000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9001,	'Esti s Bus Cowboy'                     ,false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9002,	'Ski- und Snowboardverein Schlupfig'    ,false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9003,	'B wie Möister'                         ,false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9004,	'SchinkliverteidigerInnen'              ,false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9005,	'Catch Us If You Can!'                  ,false,false,0,9000);

-- templates
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2900,'Winnerboard siehe Screen',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3900,'Loserboard siehe Screen',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2901,'HF Kat. AW (1. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2902,'HF Kat. AW (4. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2903,'HF Kat. AW (2. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2904,'HF Kat. AW (3. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2905,'Final Kat. AW (Sieger HF Matte)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2906,'Final Kat. AW (Sieger HF InPuls)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2907,'KF Kat. AW (Verlierer HF Matte)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2908,'KF Kat. AW (Verlierer HF InPuls)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3901,'HF Kat. AL (1. Winner)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3902,'HF Kat. AL (4. Winner)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3903,'HF Kat. AL (2. Winner)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3904,'HF Kat. AL (3. Winner)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3905,'Final Kat. AL (Sieger HF Matte)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3906,'Final Kat. AL (Sieger HF InPuls)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3907,'KF Kat. AL (Verlierer HF Matte)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3908,'KF Kat. AL (Verlierer HF InPuls)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4901,'HF Kat. B (1. Gruppe 1)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4902,'HF Kat. B (2. Gruppe 2)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4903,'HF Kat. B (1. Gruppe 2)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4904,'HF Kat. B (2. Gruppe 1)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4905,'Final Kat. B (Sieger HF Matte)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4906,'Final Kat. B (Sieger HF InPuls)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4907,'KF Kat. B (Verlierer HF Matte)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (4908,'KF Kat. B (Verlierer HF InPuls)',true,true,0,4000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7901,'HF Kat. C (1. Gruppe 1)',true,true,0,7000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7902,'HF Kat. C (2. Gruppe 2)',true,true,0,7000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7903,'HF Kat. C (1. Gruppe 2)',true,true,0,7000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7904,'HF Kat. C (2. Gruppe 1)',true,true,0,7000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7905,'Final Kat. C (Sieger HF Matte)',true,true,0,7000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7906,'Final Kat. C (Sieger HF InPuls)',true,true,0,7000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7907,'KF Kat. C (Verlierer HF Matte)',true,true,0,7000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (7908,'KF Kat. C (Verlierer HF InPuls)',true,true,0,7000);

INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1 ,720000,0,0,FALSE,'2021-12-11 16:24:00',0,0,5000,1,5004,	5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (2 ,720000,0,0,FALSE,'2021-12-11 16:36:00',0,0,6000,1,6004,	6002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (3 ,720000,0,0,FALSE,'2021-12-11 16:48:00',0,0,8000,1,8004,	8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (4 ,720000,0,0,FALSE,'2021-12-11 17:00:00',0,0,9000,1,9004,	9002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (5 ,720000,0,0,FALSE,'2021-12-11 17:12:00',0,0,1000,1,1000,	1005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (6 ,720000,0,0,FALSE,'2021-12-11 17:24:00',0,0,1000,1,1004,	1009);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (7 ,720000,0,0,FALSE,'2021-12-11 17:36:00',0,0,1000,1,1001,	1006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (8 ,720000,0,0,FALSE,'2021-12-11 17:48:00',0,0,1000,1,1003,	1008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (9 ,720000,0,0,FALSE,'2021-12-11 18:00:00',0,0,1000,1,1002,	1007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10,720000,0,0,FALSE,'2021-12-11 18:12:00',0,0,5000,1,5001,	5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (11,720000,0,0,FALSE,'2021-12-11 18:24:00',0,0,6000,1,6001,	6004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (12,720000,0,0,FALSE,'2021-12-11 18:36:00',0,0,8000,1,8001,	8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (13,720000,0,0,FALSE,'2021-12-11 18:48:00',0,0,9000,1,9001,	9004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (14,720000,0,0,FALSE,'2021-12-11 19:00:00',0,0,1000,1,1015,	1014);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (15,720000,0,0,FALSE,'2021-12-11 19:12:00',0,0,1000,1,1016,	1010);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (16,720000,0,0,FALSE,'2021-12-11 19:24:00',0,0,1000,1,1017,	1011);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (17,720000,0,0,FALSE,'2021-12-11 19:36:00',0,0,1000,1,1018,	1012);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (18,720000,0,0,FALSE,'2021-12-11 19:48:00',0,0,1000,1,1014,	1013);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (19,720000,0,0,FALSE,'2021-12-11 20:00:00',0,0,5000,1,5003,	5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (20,720000,0,0,FALSE,'2021-12-11 20:12:00',0,0,6000,1,6003,	6001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (21,720000,0,0,FALSE,'2021-12-11 20:24:00',0,0,8000,1,8003,	8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (22,720000,0,0,FALSE,'2021-12-11 20:36:00',0,0,9000,1,9003,	9001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (23,720000,0,0,TRUE ,'2021-12-11 20:48:00',0,0,2000,1,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (24,720000,0,0,TRUE ,'2021-12-11 21:00:00',0,0,2000,1,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (25,720000,0,0,TRUE ,'2021-12-11 21:12:00',0,0,2000,1,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (26,720000,0,0,TRUE ,'2021-12-11 21:24:00',0,0,2000,1,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (27,720000,0,0,TRUE ,'2021-12-11 21:36:00',0,0,2000,1,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (28,720000,0,0,FALSE,'2021-12-11 21:48:00',0,0,5000,1,5002,	5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (29,720000,0,0,FALSE,'2021-12-11 22:00:00',0,0,6000,1,6002,	6005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (30,720000,0,0,FALSE,'2021-12-11 22:12:00',0,0,8000,1,8002,	8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (31,720000,0,0,FALSE,'2021-12-11 22:24:00',0,0,9000,1,9002,	9005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (32,720000,0,0,TRUE ,'2021-12-11 22:36:00',0,0,3000,1,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (33,720000,0,0,TRUE ,'2021-12-11 22:48:00',0,0,3000,1,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (34,720000,0,0,TRUE ,'2021-12-11 23:00:00',0,0,3000,1,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (35,720000,0,0,TRUE ,'2021-12-11 23:12:00',0,0,3000,1,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (36,720000,0,0,TRUE ,'2021-12-11 23:24:00',0,0,3000,1,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (37,720000,0,0,FALSE,'2021-12-11 23:36:00',0,0,5000,1,5005,	5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (38,720000,0,0,FALSE,'2021-12-11 23:48:00',0,0,6000,1,6005,	6003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (39,720000,0,0,FALSE,'2021-12-12 00:00:00',0,0,8000,1,8005,	8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (40,720000,0,0,FALSE,'2021-12-12 00:12:00',0,0,9000,1,9005,	9003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (41,720000,0,0,TRUE ,'2021-12-12 00:24:00',0,1,2000,1,2901,2902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (42,720000,0,0,TRUE ,'2021-12-12 00:48:00',0,1,3000,1,3901,3902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (43,720000,0,0,TRUE ,'2021-12-12 01:00:00',0,1,4000,1,4901,4902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (44,720000,0,0,TRUE ,'2021-12-12 01:12:00',0,1,7000,1,7901,7902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (45,720000,0,0,TRUE ,'2021-12-12 01:24:00',0,2,2000,1,2905,2906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (46,720000,0,0,TRUE ,'2021-12-12 01:36:00',0,2,3000,1,3905,3906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (47,720000,0,0,TRUE ,'2021-12-12 01:48:00',0,2,4000,1,4905,4906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (48,720000,0,0,TRUE ,'2021-12-12 02:00:00',0,2,7000,1,7905,7906);

INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (49,720000,0,0,FALSE,'2021-12-11 16:24:00',0,0,5000,2,5005,	5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (50,720000,0,0,FALSE,'2021-12-11 16:36:00',0,0,6000,2,6005,	6001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (51,720000,0,0,FALSE,'2021-12-11 16:48:00',0,0,8000,2,8005,	8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (52,720000,0,0,FALSE,'2021-12-11 17:00:00',0,0,9000,2,9005,	9001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (53,720000,0,0,FALSE,'2021-12-11 17:12:00',0,0,1000,2,1010,	1015);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (54,720000,0,0,FALSE,'2021-12-11 17:24:00',1,0,null,2,9999,	9999);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (55,720000,0,0,FALSE,'2021-12-11 17:36:00',0,0,1000,2,1011,	1016);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (56,720000,0,0,FALSE,'2021-12-11 17:48:00',0,0,1000,2,1013,	1018);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (57,720000,0,0,FALSE,'2021-12-11 18:00:00',0,0,1000,2,1012,	1017);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (58,720000,0,0,FALSE,'2021-12-11 18:12:00',0,0,5000,2,5002,	5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (59,720000,0,0,FALSE,'2021-12-11 18:24:00',0,0,6000,2,6002,	6003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (60,720000,0,0,FALSE,'2021-12-11 18:36:00',0,0,8000,2,8002,	8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (61,720000,0,0,FALSE,'2021-12-11 18:48:00',0,0,9000,2,9002,	9003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (62,720000,0,0,FALSE,'2021-12-11 19:00:00',0,0,1000,2,1005,	1004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (63,720000,0,0,FALSE,'2021-12-11 19:12:00',0,0,1000,2,1006,	1000);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (64,720000,0,0,FALSE,'2021-12-11 19:24:00',0,0,1000,2,1009,	1003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (65,720000,0,0,FALSE,'2021-12-11 19:36:00',0,0,1000,2,1007,	1001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (66,720000,0,0,FALSE,'2021-12-11 19:48:00',0,0,1000,2,1008,	1002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (67,720000,0,0,FALSE,'2021-12-11 20:00:00',0,0,5000,2,5004,	5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (68,720000,0,0,FALSE,'2021-12-11 20:12:00',0,0,6000,2,6004,	6005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (69,720000,0,0,FALSE,'2021-12-11 20:24:00',0,0,8000,2,8004,	8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (70,720000,0,0,FALSE,'2021-12-11 20:36:00',0,0,9000,2,9004,	9005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (71,720000,0,0,TRUE ,'2021-12-11 20:48:00',0,0,3000,2,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (72,720000,0,0,TRUE ,'2021-12-11 21:00:00',0,0,3000,2,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (73,720000,0,0,TRUE ,'2021-12-11 21:12:00',0,0,3000,2,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (74,720000,0,0,TRUE ,'2021-12-11 21:24:00',0,0,3000,2,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (75,720000,0,0,TRUE ,'2021-12-11 21:36:00',0,0,3000,2,3900,	3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (76,720000,0,0,FALSE,'2021-12-11 21:48:00',0,0,5000,2,5003,	5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (77,720000,0,0,FALSE,'2021-12-11 22:00:00',0,0,6000,2,6003,	6004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (78,720000,0,0,FALSE,'2021-12-11 22:12:00',0,0,8000,2,8003,	8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (79,720000,0,0,FALSE,'2021-12-11 22:24:00',0,0,9000,2,9003,	9004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (80,720000,0,0,TRUE ,'2021-12-11 22:36:00',0,0,2000,2,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (81,720000,0,0,TRUE ,'2021-12-11 22:48:00',0,0,2000,2,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (82,720000,0,0,TRUE ,'2021-12-11 23:00:00',0,0,2000,2,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (83,720000,0,0,TRUE ,'2021-12-11 23:12:00',0,0,2000,2,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (84,720000,0,0,TRUE ,'2021-12-11 23:24:00',0,0,2000,2,2900,	2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (85,720000,0,0,FALSE,'2021-12-11 23:36:00',0,0,5000,2,5001,	5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (86,720000,0,0,FALSE,'2021-12-11 23:48:00',0,0,6000,2,6001,	6002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (87,720000,0,0,FALSE,'2021-12-12 00:00:00',0,0,8000,2,8001,	8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (88,720000,0,0,FALSE,'2021-12-12 00:12:00',0,0,9000,2,9001,	9002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (89,720000,0,0,TRUE ,'2021-12-12 00:24:00',0,1,2000,2,2903, 2904  );
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (90,720000,0,0,TRUE ,'2021-12-12 00:48:00',0,1,3000,2,  3903,3904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (91,720000,0,0,TRUE ,'2021-12-12 01:00:00',0,1,4000,2,4903, 4904  );
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (92,720000,0,0,TRUE ,'2021-12-12 01:12:00',0,1,7000,2, 7903,7904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (93,720000,0,0,TRUE ,'2021-12-12 01:24:00',0,2,2000,2,2907, 2908  );
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (94,720000,0,0,TRUE ,'2021-12-12 01:36:00',0,2,3000,2,3907, 3908  );
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (95,720000,0,0,TRUE ,'2021-12-12 01:48:00',0,2,4000,2,  4907,4908);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (96,720000,0,0,TRUE ,'2021-12-12 02:00:00',0,2,7000,2,7907, 7908  );
