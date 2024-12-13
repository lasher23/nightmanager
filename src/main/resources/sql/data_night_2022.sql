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


INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1001,'UHC Cordon Bleu & Pommes',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1002,'STV Rickenbach',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1003,'Schinkliverteidiger',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1004,'Inglorius Beer Stars',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1005,'Siloballe United',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1006,'Risktaker',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1007,'Spezbuebe',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1008,'Derä willi de wiedär äinisch!',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1009,'chronisch überhopft',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1010,'Were here for the beer',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1011,'Chöbeljäger Lozärn',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1012,'Zockerwatte',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1013,'Spice Girls',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1014,'Hobbyglöön',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1015,'Töffli Club Schongau',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1016,'Lochruitiwillys',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1017,'NCC Flexer',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1018,'Brunos Chästruppe',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1019,'keinBiervorvier',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1020,'Fichte',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5001,'Bäribrommer',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5002,'Lüütertüter',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5003,'Rammler',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5004,'Hakuna Matata',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5005,'Eichhoffront',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6001,'Rosswöschwyber',false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6002,'d''Musig',false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6003,'Stägis',false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6004,'Mü Ha Ranchers',false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (6005,'Hornochse',false,false,0,6000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8001,'Rainbow Club',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8002,'Äi seif die Äier',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8003,'Heitere Fahne',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8004,'Schinkliverteidigerinne',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8005,'nollsebetuusig du besch gruusig',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9001,'#keiahnig',false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9002,'eis:zwoi:drüü:orangeschüü',false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9003,'Vollmilch',false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9004,'Chräääbelis',false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (9005,'MOKA EFTI',false,false,0,9000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2900,'Winnerboard siehe Beamer',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3900,'Loserboard siehe Beamer',true,true,0,3000);
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

INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (1,720000,0,0,FALSE,'2018-12-08 15:48:00',0,0,5000,1,5002,5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (2,720000,0,0,FALSE,'2018-12-08 16:00:00',0,0,6000,1,6002,6004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (3,720000,0,0,FALSE,'2018-12-08 16:12:00',0,0,8000,1,8002,8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (4,720000,0,0,FALSE,'2018-12-08 16:24:00',0,0,9000,1,9002,9004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (5,720000,0,0,FALSE,'2018-12-08 16:36:00',0,0,1000,1,1006,1001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (6,720000,0,0,FALSE,'2018-12-08 16:48:00',0,0,1000,1,1010,1005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (7,720000,0,0,FALSE,'2018-12-08 17:00:00',0,0,1000,1,1007,1002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (8,720000,0,0,FALSE,'2018-12-08 17:12:00',0,0,1000,1,1009,1004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (9,720000,0,0,FALSE,'2018-12-08 17:24:00',0,0,1000,1,1008,1003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (10,720000,0,0,FALSE,'2018-12-08 17:36:00',0,0,5000,1,5004,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (11,720000,0,0,FALSE,'2018-12-08 17:48:00',0,0,6000,1,6004,6001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (12,720000,0,0,FALSE,'2018-12-08 18:00:00',0,0,8000,1,8004,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (13,720000,0,0,FALSE,'2018-12-08 18:12:00',0,0,9000,1,9004,9001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (14,720000,0,0,FALSE,'2018-12-08 18:24:00',0,0,1000,1,1015,1016);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (15,720000,0,0,FALSE,'2018-12-08 18:36:00',0,0,1000,1,1011,1017);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (16,720000,0,0,FALSE,'2018-12-08 18:48:00',0,0,1000,1,1014,1020);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (17,720000,0,0,FALSE,'2018-12-08 19:00:00',0,0,1000,1,1012,1018);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (18,720000,0,0,FALSE,'2018-12-08 19:12:00',0,0,1000,1,1013,1019);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (19,720000,0,0,FALSE,'2018-12-08 19:24:00',0,0,5000,1,5001,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (20,720000,0,0,FALSE,'2018-12-08 19:36:00',0,0,6000,1,6001,6003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (21,720000,0,0,FALSE,'2018-12-08 19:48:00',0,0,8000,1,8001,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (22,720000,0,0,FALSE,'2018-12-08 20:00:00',0,0,9000,1,9001,9003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (23,720000,0,0,TRUE,'2018-12-08 20:12:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (24,720000,0,0,TRUE,'2018-12-08 20:24:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (25,720000,0,0,TRUE,'2018-12-08 20:36:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (26,720000,0,0,TRUE,'2018-12-08 20:48:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (27,720000,0,0,TRUE,'2018-12-08 21:00:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (28,720000,0,0,FALSE,'2018-12-08 21:12:00',0,0,5000,1,5005,5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (29,720000,0,0,FALSE,'2018-12-08 21:24:00',0,0,6000,1,6005,6002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (30,720000,0,0,FALSE,'2018-12-08 21:36:00',0,0,8000,1,8005,8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (31,720000,0,0,FALSE,'2018-12-08 21:48:00',0,0,9000,1,9005,9002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (32,720000,0,0,TRUE,'2018-12-08 22:00:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (33,720000,0,0,TRUE,'2018-12-08 22:12:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (34,720000,0,0,TRUE,'2018-12-08 22:24:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (35,720000,0,0,TRUE,'2018-12-08 22:36:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (36,720000,0,0,TRUE,'2018-12-08 22:48:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (37,720000,0,0,FALSE,'2018-12-08 23:00:00',0,0,5000,1,5003,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (38,720000,0,0,FALSE,'2018-12-08 23:12:00',0,0,6000,1,6003,6005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (39,720000,0,0,FALSE,'2018-12-08 23:24:00',0,0,8000,1,8003,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (40,720000,0,0,FALSE,'2018-12-08 23:36:00',0,0,9000,1,9003,9005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (41,720000,0,0,TRUE,'2018-12-08 23:48:00',0,1,2000,1,2902,2901);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (42,720000,0,0,TRUE,'2018-12-09 00:00:00',0,1,3000,1,3902,3901);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (43,720000,0,0,TRUE,'2018-12-09 00:12:00',0,1,4000,1,4902,4901);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (44,720000,0,0,TRUE,'2018-12-09 00:24:00',0,1,7000,1,7902,7901);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (45,720000,0,0,TRUE,'2018-12-09 00:48:00',0,2,2000,1,2906,2905);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (46,720000,0,0,TRUE,'2018-12-09 01:00:00',0,2,3000,1,3906,3905);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (47,720000,0,0,TRUE,'2018-12-09 01:12:00',0,2,4000,1,4906,4905);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (48,720000,0,0,TRUE,'2018-12-09 01:24:00',0,2,7000,1,7906,7905);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (49,720000,0,0,FALSE,'2018-12-08 15:48:00',0,0,5000,2,5001,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (50,720000,0,0,FALSE,'2018-12-08 16:00:00',0,0,6000,2,6001,6005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (51,720000,0,0,FALSE,'2018-12-08 16:12:00',0,0,8000,2,8001,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (52,720000,0,0,FALSE,'2018-12-08 16:24:00',0,0,9000,2,9001,9005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (53,720000,0,0,FALSE,'2018-12-08 16:36:00',0,0,1000,2,1016,1011);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (54,720000,0,0,FALSE,'2018-12-08 16:48:00',0,0,1000,2,1020,1015);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (55,720000,0,0,FALSE,'2018-12-08 17:00:00',0,0,1000,2,1017,1012);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (56,720000,0,0,FALSE,'2018-12-08 17:12:00',0,0,1000,2,1019,1014);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (57,720000,0,0,FALSE,'2018-12-08 17:24:00',0,0,1000,2,1018,1013);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (58,720000,0,0,FALSE,'2018-12-08 17:36:00',0,0,5000,2,5003,5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (59,720000,0,0,FALSE,'2018-12-08 17:48:00',0,0,6000,2,6003,6002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (60,720000,0,0,FALSE,'2018-12-08 18:00:00',0,0,8000,2,8003,8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (61,720000,0,0,FALSE,'2018-12-08 18:12:00',0,0,9000,2,9003,9002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (62,720000,0,0,FALSE,'2018-12-08 18:24:00',0,0,1000,2,1005,1006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (63,720000,0,0,FALSE,'2018-12-08 18:36:00',0,0,1000,2,1001,1007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (64,720000,0,0,FALSE,'2018-12-08 18:48:00',0,0,1000,2,1004,1010);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (65,720000,0,0,FALSE,'2018-12-08 19:00:00',0,0,1000,2,1002,1008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (66,720000,0,0,FALSE,'2018-12-08 19:12:00',0,0,1000,2,1003,1009);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (67,720000,0,0,FALSE,'2018-12-08 19:24:00',0,0,5000,2,5005,5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (68,720000,0,0,FALSE,'2018-12-08 19:36:00',0,0,6000,2,6005,6004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (69,720000,0,0,FALSE,'2018-12-08 19:48:00',0,0,8000,2,8005,8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (70,720000,0,0,FALSE,'2018-12-08 20:00:00',0,0,9000,2,9005,9004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (71,720000,0,0,TRUE,'2018-12-08 20:12:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (72,720000,0,0,TRUE,'2018-12-08 20:24:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (73,720000,0,0,TRUE,'2018-12-08 20:36:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (74,720000,0,0,TRUE,'2018-12-08 20:48:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (75,720000,0,0,TRUE,'2018-12-08 21:00:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (76,720000,0,0,FALSE,'2018-12-08 21:12:00',0,0,5000,2,5004,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (77,720000,0,0,FALSE,'2018-12-08 21:24:00',0,0,6000,2,6004,6003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (78,720000,0,0,FALSE,'2018-12-08 21:36:00',0,0,8000,2,8004,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (79,720000,0,0,FALSE,'2018-12-08 21:48:00',0,0,9000,2,9004,9003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (80,720000,0,0,TRUE,'2018-12-08 22:00:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (81,720000,0,0,TRUE,'2018-12-08 22:12:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (82,720000,0,0,TRUE,'2018-12-08 22:24:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (83,720000,0,0,TRUE,'2018-12-08 22:36:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (84,720000,0,0,TRUE,'2018-12-08 22:48:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (85,720000,0,0,FALSE,'2018-12-08 23:00:00',0,0,5000,2,5002,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (86,720000,0,0,FALSE,'2018-12-08 23:12:00',0,0,6000,2,6002,6001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (87,720000,0,0,FALSE,'2018-12-08 23:24:00',0,0,8000,2,8002,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (88,720000,0,0,FALSE,'2018-12-08 23:36:00',0,0,9000,2,9002,9001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (89,720000,0,0,TRUE,'2018-12-08 23:48:00',0,1,2000,2,2904,2903);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (90,720000,0,0,TRUE,'2018-12-09 00:00:00',0,1,3000,2,3904,3903);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (91,720000,0,0,TRUE,'2018-12-09 00:12:00',0,1,4000,2,4904,4903);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (92,720000,0,0,TRUE,'2018-12-09 00:24:00',0,1,7000,2,7904,7903);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (93,720000,0,0,TRUE,'2018-12-09 00:48:00',0,2,2000,2,2908,2907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (94,720000,0,0,TRUE,'2018-12-09 01:00:00',0,2,3000,2,3908,3907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (95,720000,0,0,TRUE,'2018-12-09 01:12:00',0,2,4000,2,4908,4907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (96,720000,0,0,TRUE,'2018-12-09 01:24:00',0,2,7000,2,7908,7907);