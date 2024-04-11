
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

INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (1000,'Plausch',NULL,0,2,NULL);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (2000,'Plausch Winner',NULL,5,0,1000);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (3000,'Plausch Loser',NULL,5,0,1000);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (5000,'Guuggenmusigen und Firmen',NULL,1,0,NULL);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category) VALUES (8000,'Mixed',NULL,1,0,NULL);

INSERT INTO halls (id,name) VALUES (1,'Inpuls');
INSERT INTO halls (id,name) VALUES (2,'Matte');


INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1001,'Herre 3 und Sarah',false,false,0,1000,'+41793929824');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1002,'Bodelosi Borgis',false,false,0,1000,'+41797155546');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1003,'Töffli Club Schongau',false,false,0,1000,'+41789500190');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1004,'Spycher Rümli Chöupu',false,false,0,1000,'+41798524691');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1005,'Eintracht inaktiv',false,false,0,1000,'+41796496559');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1006,'Tätschgeili Borgis',false,false,0,1000,'+41798630009');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1007,'Chöbeljäger Lozärn',false,false,0,1000,'+41793365652');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1008,'Scousers',false,false,0,1000,'+41788848642');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1009,'Chäpslifrönde',false,false,0,1000,'+41774603617');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1010,'Lieblingsnochbere',false,false,0,1000,'+41793806007');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1011,'Edgar Taper Fade',false,false,0,1000,'+41774769195');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1012,'Cracy Parents',false,false,0,1000,'+41796887922');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1013,'Wöudsäu',false,false,0,1000,'+41798885395');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1014,'Panthers',false,false,0,1000,'+41775327555');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1015,'Schnauzfichte',false,false,0,1000,'+41798545557');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1016,'Sauffichten',false,false,0,1000,'+41798545557');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1017,'LÄTERE-GMBH',false,false,0,1000,'+41787536871');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1018,'Chäpslerfrönde Baubu',false,false,0,1000,'+41419113838');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1019,'Stv Neudorf',false,false,0,1000,'+41788062003');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (1020,'die Potänte Chäibe',false,false,0,1000,'+41774665785');

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5001,'Eichleguugger',false,false,0,5000,'+41765804182');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5002,'Bäribrommer',false,false,0,5000,'+41786699936');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5003,'Guggemusig Märebrätscher Sämpech',false,false,0,5000,'+41799141339');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5004,'STEIL ESCH GEIL!',false,false,0,5000,'+41795424543');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5005,'Team Huwyler Sport ',false,false,0,5000,'+41794686072');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5006,'Lüütertüter Höudisriede',false,false,0,5000,'+41798945759');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5007,'Altersheim Bisinsgras',false,false,0,5000,'+41794733566');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5008,'Stägis',false,false,0,5000,'+41797817400');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5009,'MÜ-HA Ranchers',false,false,0,5000,'+41798507145');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (5010,'Gueds us Obwaldä',false,false,0,5000,'+41793458499');

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8001,'Ayio Napa',false,false,0,8000,'+41774581581');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8002,'Gummibierbande',false,false,0,8000,'+41793045881');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8003,'Freunde der angewandten Fluidmechanik',false,false,0,8000,'+41774718121');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8004,'Alte Hosen',false,false,0,8000,'+41765804182');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8005,'Schinkliverteidiger',false,false,0,8000,'+41795437097');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8006,'Schinkliverteidigerinne',false,false,0,8000,'+41795437097');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8007,'UHC Ballyhoger',false,false,0,8000,'+41796596178');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8008,'Mais oder so',false,false,0,8000,'+41791940849');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8009,'De Ueli esch schoud',false,false,0,8000,'+41798152239');
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category,phone_number) VALUES (8010,'Figaro Kings',false,false,0,8000,'+41792637582 ');

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2900,'Winnerboard siehe Beamer',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3900,'Loserboard siehe Beamer',true,true,0,3000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2901,'HF Kat. AW (1. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2902,'HF Kat. AW (4. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2903,'HF Kat. AW (2. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2904,'HF Kat. AW (3. Winner)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2905,'Final Kat. AW (Sieger HF InPuls)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2906,'Final Kat. AW (Sieger HF Matte)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2907,'KF Kat. AW (Verlierer HF InPuls)',true,true,0,2000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (2908,'KF Kat. AW (Verlierer HF Matte)',true,true,0,2000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3901,'HF Kat. AL (1. Loser)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3902,'HF Kat. AL (4. Loser)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3903,'HF Kat. AL (2. Loser)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3904,'HF Kat. AL (3. Loser)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3905,'Final Kat. AL (Sieger HF InPuls)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3906,'Final Kat. AL (Sieger HF Matte)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3907,'KF Kat. AL (Verlierer HF InPuls)',true,true,0,3000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (3908,'KF Kat. AL (Verlierer HF Matte)',true,true,0,3000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5901,'HF Kat. B (1.)',true,true,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5902,'HF Kat. B (2.)',true,true,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5903,'HF Kat. B (3.)',true,true,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5904,'HF Kat. B (4.)',true,true,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5905,'Final Kat. B (Sieger HF InPuls)',true,true,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5906,'Final Kat. B (Sieger HF Matte)',true,true,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5907,'KF Kat. B (Verlierer HF InPuls)',true,true,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5908,'KF Kat. B (Verlierer HF Matte)',true,true,0,5000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8901,'HF Kat. C (1.)',true,true,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8902,'HF Kat. C (2.)',true,true,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8903,'HF Kat. C (3.)',true,true,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8904,'HF Kat. C (4.)',true,true,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8905,'Final Kat. C (Sieger HF InPuls)',true,true,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8906,'Final Kat. C (Sieger HF Matte)',true,true,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8907,'KF Kat. C (Verlierer HF InPuls)',true,true,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8908,'KF Kat. C (Verlierer HF Matte)',true,true,0,8000);

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (9999, 'Pause', true, true, 0, null);


-- Yetis Cup Together Stage 1 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1,60000,0,0,FALSE,'2023-12-09 15:40:00',0,0,1000,1,1003,1001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (2,60000,0,0,FALSE,'2023-12-09 15:50:00',0,0,1000,1,1002,1004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (3,60000,0,0,FALSE,'2023-12-09 16:00:00',0,0,1000,1,1010,1006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (4,60000,0,0,FALSE,'2023-12-09 16:10:00',0,0,1000,1,1007,1009);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (5,60000,0,0,FALSE,'2023-12-09 16:20:00',0,0,1000,1,1005,1008);
-- Cat C Stage 1 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (6,60000,0,0,FALSE,'2023-12-09 16:30:00',0,0,8000,1,8001,8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (7,60000,0,0,FALSE,'2023-12-09 16:40:00',0,0,8000,1,8003,8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (8,60000,0,0,FALSE,'2023-12-09 16:50:00',0,0,8000,1,8005,8006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (9,60000,0,0,FALSE,'2023-12-09 17:00:00',0,0,8000,1,8007,8008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10,60000,0,0,FALSE,'2023-12-09 17:10:00',0,0,8000,1,8009,8010);
-- Yetis Cup Together Stage 2 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (11,60000,0,0,FALSE,'2023-12-09 17:20:00',0,0,1000,1,1011,1012);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (12,60000,0,0,FALSE,'2023-12-09 17:30:00',0,0,1000,1,1016,1017);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (13,60000,0,0,FALSE,'2023-12-09 17:40:00',0,0,1000,1,1014,1015);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (14,60000,0,0,FALSE,'2023-12-09 17:50:00',0,0,1000,1,1018,1013);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (15,60000,0,0,FALSE,'2023-12-09 18:00:00',0,0,1000,1,1019,1020);
-- Cat C Stage 2 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (16,60000,0,0,FALSE,'2023-12-09 18:10:00',0,0,8000,1,8010,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (17,60000,0,0,FALSE,'2023-12-09 18:20:00',0,0,8000,1,8002,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (18,60000,0,0,FALSE,'2023-12-09 18:30:00',0,0,8000,1,8004,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (19,60000,0,0,FALSE,'2023-12-09 18:40:00',0,0,8000,1,8006,8007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (20,60000,0,0,FALSE,'2023-12-09 18:50:00',0,0,8000,1,8008,8009);
-- Yetis Cup Winner Stage 1 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (21,60000,0,0,TRUE ,'2023-12-09 19:00:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (22,60000,0,0,TRUE ,'2023-12-09 19:10:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (23,60000,0,0,TRUE ,'2023-12-09 19:20:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (24,60000,0,0,TRUE ,'2023-12-09 19:30:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (25,60000,0,0,TRUE ,'2023-12-09 19:40:00',0,0,2000,1,2900,2900);
-- Cat B Stage 3 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (26,60000,0,0,FALSE,'2023-12-09 19:50:00',0,0,5000,1,5001,5008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (27,60000,0,0,FALSE,'2023-12-09 20:00:00',0,0,5000,1,5003,5010);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (28,60000,0,0,FALSE,'2023-12-09 20:10:00',0,0,5000,1,5005,5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (29,60000,0,0,FALSE,'2023-12-09 20:20:00',0,0,5000,1,5007,5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (30,60000,0,0,FALSE,'2023-12-09 20:30:00',0,0,5000,1,5009,5006);
-- Yetis Cup Looser Stage 2 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (31,60000,0,0,TRUE ,'2023-12-09 20:40:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (32,60000,0,0,TRUE ,'2023-12-09 20:50:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (33,60000,0,0,TRUE ,'2023-12-09 21:00:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (34,60000,0,0,TRUE ,'2023-12-09 21:10:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (35,60000,0,0,TRUE ,'2023-12-09 21:20:00',0,0,3000,1,3900,3900);
-- Cat B Stage 4 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (36,60000,0,0,FALSE,'2023-12-09 21:30:00',0,0,5000,1,5006,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (37,60000,0,0,FALSE,'2023-12-09 21:40:00',0,0,5000,1,5008,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (38,60000,0,0,FALSE,'2023-12-09 21:50:00',0,0,5000,1,5010,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (39,60000,0,0,FALSE,'2023-12-09 22:00:00',0,0,5000,1,5002,5007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (40,60000,0,0,FALSE,'2023-12-09 22:10:00',0,0,5000,1,5004,5009);
-- Yetis Cup Winner Stage 3 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (41,60000,0,0,TRUE ,'2023-12-09 22:20:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (42,60000,0,0,TRUE ,'2023-12-09 22:30:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (43,60000,0,0,TRUE ,'2023-12-09 22:40:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (44,60000,0,0,TRUE ,'2023-12-09 22:50:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (45,60000,0,0,TRUE ,'2023-12-09 23:00:00',0,0,2000,1,2900,2900);
-- Cat B Stage 5 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (46,60000,0,0,FALSE,'2023-12-09 23:10:00',0,0,5000,1,5004,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (47,60000,0,0,FALSE,'2023-12-09 23:20:00',0,0,5000,1,5006,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (48,60000,0,0,FALSE,'2023-12-09 23:30:00',0,0,5000,1,5008,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (49,60000,0,0,FALSE,'2023-12-09 23:40:00',0,0,5000,1,5010,5007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (50,60000,0,0,FALSE,'2023-12-09 23:50:00',0,0,5000,1,5002,5009);
-- semis inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (51,60000,0,0,TRUE ,'2023-12-10 00:00:00',0,1,2000,1,2901,2902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (52,60000,0,0,TRUE ,'2023-12-10 00:10:00',0,1,3000,1,3901,3902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (53,60000,0,0,TRUE ,'2023-12-10 00:20:00',0,1,5000,1,5901,5904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (54,60000,0,0,TRUE ,'2023-12-10 00:30:00',0,1,8000,1,8901,8904);
-- pause
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (55,60000,0,0,TRUE ,'2023-12-10 00:40:00',1,0,null,1,9999,9999);
-- finals inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (56,60000,0,0,TRUE ,'2023-12-10 00:50:00',0,2,2000,1,2905,2906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (57,60000,0,0,TRUE ,'2023-12-10 01:00:00',0,2,3000,1,3905,3906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (58,60000,0,0,TRUE ,'2023-12-10 01:10:00',0,2,5000,1,5905,5906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (59,60000,0,0,TRUE ,'2023-12-10 01:20:00',0,2,8000,1,8905,8906);
-- Yetis Cup Together Stage 1 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (60,60000,0,0,FALSE,'2023-12-09 15:40:00',0,0,1000,2,1013,1011);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (61,60000,0,0,FALSE,'2023-12-09 15:50:00',0,0,1000,2,1012,1014);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (62,60000,0,0,FALSE,'2023-12-09 16:00:00',0,0,1000,2,1020,1016);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (63,60000,0,0,FALSE,'2023-12-09 16:10:00',0,0,1000,2,1017,1019);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (64,60000,0,0,FALSE,'2023-12-09 16:20:00',0,0,1000,2,1015,1018);
-- Cat B Stage 1 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (65,60000,0,0,FALSE,'2023-12-09 16:30:00',0,0,5000,2,5001,5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (66,60000,0,0,FALSE,'2023-12-09 16:40:00',0,0,5000,2,5003,5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (67,60000,0,0,FALSE,'2023-12-09 16:50:00',0,0,5000,2,5005,5006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (68,60000,0,0,FALSE,'2023-12-09 17:00:00',0,0,5000,2,5007,5008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (69,60000,0,0,FALSE,'2023-12-09 17:10:00',0,0,5000,2,5009,5010);
-- Yetis Cup Together Stage 2 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (70,60000,0,0,FALSE,'2023-12-09 17:20:00',0,0,1000,2,1001,1002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (71,60000,0,0,FALSE,'2023-12-09 17:30:00',0,0,1000,2,1006,1007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (72,60000,0,0,FALSE,'2023-12-09 17:40:00',0,0,1000,2,1004,1005);
--exception grusigi lüütertüter
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (73,60000,0,0,FALSE,'2023-12-09 17:50:00',0,0,5000,2,5006,5007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (74,60000,0,0,FALSE,'2023-12-09 18:00:00',0,0,1000,2,1008,1003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (75,60000,0,0,FALSE,'2023-12-09 18:10:00',0,0,1000,2,1009,1010);
-- Cat B Stage 2 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (76,60000,0,0,FALSE,'2023-12-09 18:20:00',0,0,5000,2,5010,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (77,60000,0,0,FALSE,'2023-12-09 18:30:00',0,0,5000,2,5002,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (78,60000,0,0,FALSE,'2023-12-09 18:40:00',0,0,5000,2,5004,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (79,60000,0,0,FALSE,'2023-12-09 18:50:00',0,0,5000,2,5008,5009);
-- Yetis Cup Looser Stage 1 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (80,60000,0,0,TRUE ,'2023-12-09 19:00:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (81,60000,0,0,TRUE ,'2023-12-09 19:10:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (82,60000,0,0,TRUE ,'2023-12-09 19:20:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (83,60000,0,0,TRUE ,'2023-12-09 19:30:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (84,60000,0,0,TRUE ,'2023-12-09 19:40:00',0,0,3000,2,3900,3900);
-- Cat C Stage 3 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (85,60000,0,0,FALSE,'2023-12-09 19:50:00',0,0,8000,2,8001,8008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (86,60000,0,0,FALSE,'2023-12-09 20:00:00',0,0,8000,2,8003,8010);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (87,60000,0,0,FALSE,'2023-12-09 20:10:00',0,0,8000,2,8005,8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (88,60000,0,0,FALSE,'2023-12-09 20:20:00',0,0,8000,2,8007,8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (89,60000,0,0,FALSE,'2023-12-09 20:30:00',0,0,8000,2,8009,8006);
-- Yetis Cup Winner Stage 2 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (90,60000,0,0,TRUE ,'2023-12-09 20:40:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (91,60000,0,0,TRUE ,'2023-12-09 20:50:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (92,60000,0,0,TRUE ,'2023-12-09 21:00:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (93,60000,0,0,TRUE ,'2023-12-09 21:10:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (94,60000,0,0,TRUE ,'2023-12-09 21:20:00',0,0,2000,2,2900,2900);
-- Cat C Stage 4 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (95,60000,0,0,FALSE,'2023-12-09 21:30:00',0,0,8000,2,8006,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (96,60000,0,0,FALSE,'2023-12-09 21:40:00',0,0,8000,2,8008,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (97,60000,0,0,FALSE,'2023-12-09 21:50:00',0,0,8000,2,8010,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (98,60000,0,0,FALSE,'2023-12-09 22:00:00',0,0,8000,2,8002,8007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (99,60000,0,0,FALSE,'2023-12-09 22:10:00',0,0,8000,2,8004,8009);
-- Yetis Cup Looser Stage 3 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100,60000,0,0,TRUE ,'2023-12-09 22:20:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (101,60000,0,0,TRUE ,'2023-12-09 22:30:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (102,60000,0,0,TRUE ,'2023-12-09 22:40:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (103,60000,0,0,TRUE ,'2023-12-09 22:50:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (104,60000,0,0,TRUE ,'2023-12-09 23:00:00',0,0,3000,2,3900,3900);
-- Cat B Stage 5 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (105,60000,0,0,FALSE,'2023-12-09 23:10:00',0,0,8000,2,8004,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (106,60000,0,0,FALSE,'2023-12-09 23:20:00',0,0,8000,2,8006,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (107,60000,0,0,FALSE,'2023-12-09 23:30:00',0,0,8000,2,8008,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (108,60000,0,0,FALSE,'2023-12-09 23:40:00',0,0,8000,2,8010,8007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (109,60000,0,0,FALSE,'2023-12-09 23:50:00',0,0,8000,2,8002,8009);
-- semis matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (110,60000,0,0,TRUE ,'2023-12-10 00:00:00',0,1,2000,2,2903,2904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (111,60000,0,0,TRUE ,'2023-12-10 00:10:00',0,1,3000,2,3903,3904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (112,60000,0,0,TRUE ,'2023-12-10 00:20:00',0,1,5000,2,5902,5903);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (113,60000,0,0,TRUE ,'2023-12-10 00:30:00',0,1,8000,2,8902,8903);
-- pause
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (114,60000,0,0,TRUE ,'2023-12-10 00:40:00',1,0,null,2,9999,9999);
-- finals matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (115,60000,0,0,TRUE ,'2023-12-10 00:50:00',0,2,2000,2,2908,2907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (116,60000,0,0,TRUE ,'2023-12-10 01:00:00',0,2,3000,2,3908,3907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (117,60000,0,0,TRUE ,'2023-12-10 01:10:00',0,2,5000,2,5908,5907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (118,60000,0,0,TRUE ,'2023-12-10 01:20:00',0,2,8000,2,8908,8907);