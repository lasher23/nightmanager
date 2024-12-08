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



INSERT INTO public.halls (id, name) VALUES (1, 'Inpuls');
INSERT INTO public.halls (id, name) VALUES (2, 'Matte');


-- Kategorien
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category) VALUES (100, 'Schüler Kategorie 1', NULL, 1, 0, NULL);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category) VALUES (200, 'Schüler Kategorie 2', NULL, 1, 0, NULL);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category) VALUES (300, 'Schüler Kategorie 3', NULL, 1, 0, NULL);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category) VALUES (400, 'Schüler Kategorie 4', NULL, 1, 0, NULL);

-- Kategorie 1 Teams
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (100, 'Hockeyhaie', false, false, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (101, 'Die 5 Geister', false, false, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (102, 'High Fives', false, false, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (103, 'Die blauen Unihockey-Kids', false, false, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (104, 'Floorrunners', false, false, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (105, 'Floorball United', false, false, 0, 100);

-- Kategorie 2 Teams
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (200, 'Die fünf Blitze', false, false, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (201, 'Paulberger Gang', false, false, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (202, 'Seetal Power', false, false, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (203, 'FloorBoys', false, false, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (204, 'Yelly Boys', false, false, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (205, 'Floorball-Crasher', false, false, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (206, 'Unihockey-Gang', false, false, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (207, 'Magic Dragons', false, false, 0, 200);
-- Kategorie 3 Teams
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (300, 'Nimms mit Gemütlichkeit', false, false, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (301, 'HC Seetal', false, false, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (302, 'Fire Feathers', false, false, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (303, 'Dragons', false, false, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (304, 'UHC TACOS', false, false, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (305, 'Pinken Schafe', false, false, 0, 300);

-- Kategorie 4 Teams
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (400, 'Möuchschnette', false, false, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (401, 'Floorboys', false, false, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (402, 'Rammböck', false, false, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (403, 'Ou ha de Name fergässe', false, false, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (404, 'UHC Cörry-Worscht', false, false, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (405, 'Red chilis', false, false, 0, 400);


-- Kat 1 Placeholders
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (111, 'HF Kat. 1 (1.)', true, true, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (112, 'HF Kat. 1 (2.)', true, true, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (113, 'HF Kat. 1 (3.)', true, true, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (114, 'HF Kat. 1 (4.)', true, true, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (121, 'Final Kat. 1 (Sieger HF 1.-4.)', true, true, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (122, 'Final Kat. 1 (Sieger HF 2.-3.)', true, true, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (131, 'KF Kat. 1 (Verlierer HF 1.-4.)', true, true, 0, 100);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (132, 'KF Kat. 1 (Verlierer HF 2.-3.)', true, true, 0, 100);
-- Kat 2 Placeholders
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (211, 'HF Kat. 2 (1.)', true, true, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (212, 'HF Kat. 2 (2.)', true, true, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (213, 'HF Kat. 2 (3.)', true, true, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (214, 'HF Kat. 2 (4.)', true, true, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (221, 'Final Kat. 2 (Sieger HF 1.-4.)', true, true, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (222, 'Final Kat. 2 (Sieger HF 2.-3.)', true, true, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (231, 'KF Kat. 2 (Verlierer HF 1.-4.)', true, true, 0, 200);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (232, 'KF Kat. 2 (Verlierer HF 2.-3.)', true, true, 0, 200);
-- Kat 3 Placeholders
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (311, 'HF Kat. 3 (1.)', true, true, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (312, 'HF Kat. 3 (2.)', true, true, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (313, 'HF Kat. 3 (3.)', true, true, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (314, 'HF Kat. 3 (4.)', true, true, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (321, 'Final Kat. 3 (Sieger HF 1.-4.)', true, true, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (322, 'Final Kat. 3 (Sieger HF 2.-3.)', true, true, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (331, 'KF Kat. 3 (Verlierer HF 1.-4.)', true, true, 0, 300);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (332, 'KF Kat. 3 (Verlierer HF 2.-3.)', true, true, 0, 300);
-- Kat 4 Placeholders
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (411, 'HF Kat. 4 (1.)', true, true, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (412, 'HF Kat. 4 (2.)', true, true, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (413, 'HF Kat. 4 (3.)', true, true, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (414, 'HF Kat. 4 (4.)', true, true, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (421, 'Final Kat. 4 (Sieger HF 1.-4.)', true, true, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (422, 'Final Kat. 4 (Sieger HF 2.-3.)', true, true, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (431, 'KF Kat. 4 (Verlierer HF 1.-4.)', true, true, 0, 400);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (432, 'KF Kat. 4 (Verlierer HF 2.-3.)', true, true, 0, 400);


INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (9999, 'Pause', true, true, 0, 300);



-- Kat 1 1. Runde Inpuls
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (1 , 60000, 0, 0, false, '2024-12-14 08:00:00', 0, 0, 1,100,100,101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (2 , 60000, 0, 0, false, '2024-12-14 08:10:00', 0, 0, 1,100,104,105);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (3 , 60000, 0, 0, false, '2024-12-14 08:20:00', 0, 0, 1,200,200,201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (4 , 60000, 0, 0, false, '2024-12-14 08:30:00', 0, 0, 1,200,204,205);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (5 , 60000, 0, 0, false, '2024-12-14 08:40:00', 0, 0, 1,300,300,301);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (6 , 60000, 0, 0, false, '2024-12-14 08:50:00', 0, 0, 1,300,304,305);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (7 , 60000, 0, 0, false, '2024-12-14 09:00:00', 0, 0, 1,400,400,401);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (8 , 60000, 0, 0, false, '2024-12-14 09:10:00', 0, 0, 1,400,404,405);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (9 , 60000, 0, 0, false, '2024-12-14 09:20:00', 0, 0, 1,100,101,104);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (10, 60000, 0, 0, false, '2024-12-14 09:30:00', 0, 0, 1,200,201,204);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (11, 60000, 0, 0, false, '2024-12-14 09:40:00', 0, 0, 1,200,203,206);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (12, 60000, 0, 0, false, '2024-12-14 09:50:00', 0, 0, 1,300,301,304);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (13, 60000, 0, 0, false, '2024-12-14 10:00:00', 0, 0, 1,400,401,404);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (14, 60000, 0, 0, false, '2024-12-14 10:10:00', 0, 0, 1,100,103,100);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (15, 60000, 0, 0, false, '2024-12-14 10:20:00', 0, 0, 1,100,102,104);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (16, 60000, 0, 0, false, '2024-12-14 10:30:00', 0, 0, 1,200,204,200);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (17, 60000, 0, 0, false, '2024-12-14 10:40:00', 0, 0, 1,200,202,206);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (18, 60000, 0, 0, false, '2024-12-14 10:50:00', 0, 0, 1,300,303,300);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (19, 60000, 0, 0, false, '2024-12-14 11:00:00', 0, 0, 1,300,302,304);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (20, 60000, 0, 0, false, '2024-12-14 11:10:00', 0, 0, 1,400,403,400);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (21, 60000, 0, 0, false, '2024-12-14 11:20:00', 0, 0, 1,400,402,404);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (22, 60000, 0, 0, false, '2024-12-14 11:30:00', 0, 0, 1,100,103,101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (23, 60000, 0, 0, false, '2024-12-14 11:40:00', 0, 0, 1,200,206,201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (24, 60000, 0, 0, false, '2024-12-14 11:50:00', 0, 0, 1,200,207,202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (25, 60000, 0, 0, false, '2024-12-14 12:00:00', 0, 0, 1,300,303,301);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (26, 60000, 0, 0, false, '2024-12-14 12:10:00', 0, 0, 1,400,403,401);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (27, 60000, 0, 0, false, '2024-12-14 12:20:00', 0, 0, 1,100,105,100);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (28, 60000, 0, 0, false, '2024-12-14 12:30:00', 0, 0, 1,100,104,103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (29, 60000, 0, 0, false, '2024-12-14 12:40:00', 0, 0, 1,200,200,206);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (30, 60000, 0, 0, false, '2024-12-14 12:50:00', 0, 0, 1,200,202,204);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (31, 60000, 0, 0, false, '2024-12-14 13:00:00', 0, 0, 1,300,305,300);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (32, 60000, 0, 0, false, '2024-12-14 13:10:00', 0, 0, 1,300,304,303);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (33, 60000, 0, 0, false, '2024-12-14 13:20:00', 0, 0, 1,400,405,400);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (34, 60000, 0, 0, false, '2024-12-14 13:30:00', 0, 0, 1,400,404,403);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (35, 60000, 0, 0, false, '2024-12-14 13:40:00', 1, 0, 1,null, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (36, 60000, 0, 0, true, '2024-12-14 13:50:00', 0, 1, 1,100, 111, 114);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (37, 60000, 0, 0, true, '2024-12-14 14:00:00', 0, 1, 1,200, 211, 214);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (38, 60000, 0, 0, true, '2024-12-14 14:10:00', 0, 1, 1,300, 311, 314);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (39, 60000, 0, 0, true, '2024-12-14 14:20:00', 0, 1, 1,400, 411, 414);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (40, 60000, 0, 0, false, '2024-12-14 14:30:00', 1, 0, 1,null, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (41, 60000, 0, 0, true, '2024-12-14 14:40:00', 0, 2, 1,100, 121, 122);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (42, 60000, 0, 0, true, '2024-12-14 14:50:00', 0, 2, 1,200, 221, 222);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (43, 60000, 0, 0, true, '2024-12-14 15:00:00', 0, 2, 1,300, 321, 322);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (44, 60000, 0, 0, true, '2024-12-14 15:10:00', 0, 2, 1,400, 421, 422);


INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (45, 60000, 0, 0, false, '2024-12-14 08:00:00', 0, 0, 2, 100,102,103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (46, 60000, 0, 0, false, '2024-12-14 08:10:00', 0, 0, 2, 200,202,203);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (47, 60000, 0, 0, false, '2024-12-14 08:20:00', 0, 0, 2, 200,206,207);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (48, 60000, 0, 0, false, '2024-12-14 08:30:00', 0, 0, 2, 300,302,303);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (49, 60000, 0, 0, false, '2024-12-14 08:40:00', 0, 0, 2, 400,402,403);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (50, 60000, 0, 0, false, '2024-12-14 08:50:00', 0, 0, 2, 100,100,102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (51, 60000, 0, 0, false, '2024-12-14 09:00:00', 0, 0, 2, 100,105,103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (52, 60000, 0, 0, false, '2024-12-14 09:10:00', 0, 0, 2, 200,200,202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (53, 60000, 0, 0, false, '2024-12-14 09:20:00', 0, 0, 2, 200,205,207);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (54, 60000, 0, 0, false, '2024-12-14 09:30:00', 0, 0, 2, 300,300,302);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (55, 60000, 0, 0, false, '2024-12-14 09:40:00', 0, 0, 2, 300,305,303);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (56, 60000, 0, 0, false, '2024-12-14 09:50:00', 0, 0, 2, 400,400,402);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (57, 60000, 0, 0, false, '2024-12-14 10:00:00', 0, 0, 2, 400,405,403);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (58, 60000, 0, 0, false, '2024-12-14 10:10:00', 0, 0, 2, 100,105,101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (59, 60000, 0, 0, false, '2024-12-14 10:20:00', 0, 0, 2, 200,207,201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (60, 60000, 0, 0, false, '2024-12-14 10:30:00', 0, 0, 2, 200,205,203);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (61, 60000, 0, 0, false, '2024-12-14 10:40:00', 0, 0, 2, 300,305,301);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (62, 60000, 0, 0, false, '2024-12-14 10:50:00', 0, 0, 2, 400,405,401);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (63, 60000, 0, 0, false, '2024-12-14 11:00:00', 0, 0, 2, 100,100,104);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (64, 60000, 0, 0, false, '2024-12-14 11:10:00', 0, 0, 2, 100,102,105);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (65, 60000, 0, 0, false, '2024-12-14 11:20:00', 0, 0, 2, 200,200,205);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (66, 60000, 0, 0, false, '2024-12-14 11:30:00', 0, 0, 2, 200,203,204);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (67, 60000, 0, 0, false, '2024-12-14 11:40:00', 0, 0, 2, 300,300,304);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (68, 60000, 0, 0, false, '2024-12-14 11:50:00', 0, 0, 2, 300,302,305);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (69, 60000, 0, 0, false, '2024-12-14 12:00:00', 0, 0, 2, 400,400,404);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (70, 60000, 0, 0, false, '2024-12-14 12:10:00', 0, 0, 2, 400,402,405);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (71, 60000, 0, 0, false, '2024-12-14 12:20:00', 0, 0, 2, 100,101,102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (72, 60000, 0, 0, false, '2024-12-14 12:30:00', 0, 0, 2, 200,201,205);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (73, 60000, 0, 0, false, '2024-12-14 12:40:00', 0, 0, 2, 200,203,207);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (74, 60000, 0, 0, false, '2024-12-14 12:50:00', 0, 0, 2, 300,301,302);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (75, 60000, 0, 0, false, '2024-12-14 13:00:00', 0, 0, 2, 400,401,402);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (76, 60000, 0, 0, false, '2024-12-14 13:10:00', 1, 0, 2, null, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (77, 60000, 0, 0, false, '2024-12-14 13:20:00', 1, 0, 2, null, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (78, 60000, 0, 0, false, '2024-12-14 13:30:00', 1, 0, 2, null, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (79, 60000, 0, 0, false, '2024-12-14 13:40:00', 1, 0, 2, null, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (80, 60000, 0, 0, false, '2024-12-14 13:50:00', 0, 1, 2, 100, 112, 113);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (81, 60000, 0, 0, false, '2024-12-14 14:00:00', 0, 1, 2, 200, 212, 213);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (82, 60000, 0, 0, true , '2024-12-14 14:10:00', 0, 1, 2, 300, 312, 313);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (83, 60000, 0, 0, true , '2024-12-14 14:20:00', 0, 1, 2, 400, 412, 413);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (84, 60000, 0, 0, false, '2024-12-14 14:30:00', 1, 0, 2, null, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (85, 60000, 0, 0, true , '2024-12-14 14:40:00', 0, 2, 2, 100, 131, 132);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (86, 60000, 0, 0, true , '2024-12-14 14:50:00', 0, 2, 2, 200, 231, 232);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (87, 60000, 0, 0, true , '2024-12-14 15:00:00', 0, 2, 2, 300, 331, 332);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (88, 60000, 0, 0, true , '2024-12-14 15:10:00', 0, 2, 2, 400, 431, 432);