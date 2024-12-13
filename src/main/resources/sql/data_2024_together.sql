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
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category, show_on_display) VALUES (100, 'Schüler Kategorie 1', 'Kleine Tore', 1, 0, NULL, true);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category, show_on_display) VALUES (200, 'Schüler Kategorie 2', 'Kleine Tore', 1, 0, NULL, true);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category, show_on_display) VALUES (300, 'Schüler Kategorie 3', NULL, 1, 0, NULL, true);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category, show_on_display) VALUES (400, 'Schüler Kategorie 4', NULL, 1, 0, NULL, true);

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
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (401, 'Stickstormers', false, false, 0, 400);
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
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (1 , 60000, 0, 0, false, '2024-12-14 08:00:00', 0, 0, 1,100,102,104);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (2 , 60000, 0, 0, false, '2024-12-14 08:10:00', 0, 0, 1,100,103,105);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (3 , 60000, 0, 0, false, '2024-12-14 08:20:00', 0, 0, 1,200,200,204);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (4 , 60000, 0, 0, false, '2024-12-14 08:30:00', 0, 0, 1,200,201,205);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (5 , 60000, 0, 0, false, '2024-12-14 08:40:00', 0, 0, 1,300,302,304);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (6 , 60000, 0, 0, false, '2024-12-14 08:50:00', 0, 0, 1,300,303,305);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (7 , 60000, 0, 0, false, '2024-12-14 09:00:00', 0, 0, 1,400,402,404);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (8 , 60000, 0, 0, false, '2024-12-14 09:10:00', 0, 0, 1,400,403,405);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (9 , 60000, 0, 0, false, '2024-12-14 09:20:00', 0, 0, 1,100,104,100);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (10, 60000, 0, 0, false, '2024-12-14 09:30:00', 0, 0, 1,200,202,207);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (11, 60000, 0, 0, false, '2024-12-14 09:40:00', 0, 0, 1,200,203,206);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (12, 60000, 0, 0, false, '2024-12-14 09:50:00', 0, 0, 1,300,304,300);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (13, 60000, 0, 0, false, '2024-12-14 10:00:00', 0, 0, 1,400,404,400);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (14, 60000, 0, 0, false, '2024-12-14 10:10:00', 0, 0, 1,100,102,103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (15, 60000, 0, 0, false, '2024-12-14 10:20:00', 0, 0, 1,100,101,104);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (16, 60000, 0, 0, false, '2024-12-14 10:30:00', 0, 0, 1,200,206,200);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (17, 60000, 0, 0, false, '2024-12-14 10:40:00', 0, 0, 1,200,207,201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (18, 60000, 0, 0, false, '2024-12-14 10:50:00', 0, 0, 1,300,302,303);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (19, 60000, 0, 0, false, '2024-12-14 11:00:00', 0, 0, 1,300,301,304);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (20, 60000, 0, 0, false, '2024-12-14 11:10:00', 0, 0, 1,400,402,403);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (21, 60000, 0, 0, false, '2024-12-14 11:20:00', 0, 0, 1,400,401,404);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (22, 60000, 0, 0, false, '2024-12-14 11:30:00', 0, 0, 1,100,103,100);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (23, 60000, 0, 0, false, '2024-12-14 11:40:00', 0, 0, 1,200,205,202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (24, 60000, 0, 0, false, '2024-12-14 11:50:00', 0, 0, 1,200,204,203);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (25, 60000, 0, 0, false, '2024-12-14 12:00:00', 0, 0, 1,300,303,300);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (26, 60000, 0, 0, false, '2024-12-14 12:10:00', 0, 0, 1,400,403,400);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (27, 60000, 0, 0, false, '2024-12-14 12:20:00', 0, 0, 1,100,100,102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (28, 60000, 0, 0, false, '2024-12-14 12:30:00', 0, 0, 1,100,105,101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (29, 60000, 0, 0, false, '2024-12-14 12:40:00', 0, 0, 1,200,200,201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (30, 60000, 0, 0, false, '2024-12-14 12:50:00', 0, 0, 1,200,204,205);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (31, 60000, 0, 0, false, '2024-12-14 13:00:00', 0, 0, 1,300,300,302);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (32, 60000, 0, 0, false, '2024-12-14 13:10:00', 0, 0, 1,300,305,301);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (33, 60000, 0, 0, false, '2024-12-14 13:20:00', 0, 0, 1,400,400,402);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall, fk_category, fk_team_home, fk_team_guest) VALUES (34, 60000, 0, 0, false, '2024-12-14 13:30:00', 0, 0, 1,400,405,401);
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


INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (45, 60000, 0, 0, false, '2024-12-14 08:00:00', 0, 0, 2, 100,100,101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (46, 60000, 0, 0, false, '2024-12-14 08:10:00', 0, 0, 2, 200,202,206);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (47, 60000, 0, 0, false, '2024-12-14 08:20:00', 0, 0, 2, 200,203,207);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (48, 60000, 0, 0, false, '2024-12-14 08:30:00', 0, 0, 2, 300,300,301);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (49, 60000, 0, 0, false, '2024-12-14 08:40:00', 0, 0, 2, 400,400,401);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (50, 60000, 0, 0, false, '2024-12-14 08:50:00', 0, 0, 2, 100,101,103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (51, 60000, 0, 0, false, '2024-12-14 09:00:00', 0, 0, 2, 100,102,105);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (52, 60000, 0, 0, false, '2024-12-14 09:10:00', 0, 0, 2, 200,200,205);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (53, 60000, 0, 0, false, '2024-12-14 09:20:00', 0, 0, 2, 200,201,204);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (54, 60000, 0, 0, false, '2024-12-14 09:30:00', 0, 0, 2, 300,301,303);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (55, 60000, 0, 0, false, '2024-12-14 09:40:00', 0, 0, 2, 300,302,305);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (56, 60000, 0, 0, false, '2024-12-14 09:50:00', 0, 0, 2, 400,401,403);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (57, 60000, 0, 0, false, '2024-12-14 10:00:00', 0, 0, 2, 400,402,405);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (58, 60000, 0, 0, false, '2024-12-14 10:10:00', 0, 0, 2, 100,105,100);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (59, 60000, 0, 0, false, '2024-12-14 10:20:00', 0, 0, 2, 200,204,202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (60, 60000, 0, 0, false, '2024-12-14 10:30:00', 0, 0, 2, 200,205,203);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (61, 60000, 0, 0, false, '2024-12-14 10:40:00', 0, 0, 2, 300,305,300);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (62, 60000, 0, 0, false, '2024-12-14 10:50:00', 0, 0, 2, 400,405,400);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (63, 60000, 0, 0, false, '2024-12-14 11:00:00', 0, 0, 2, 100,101,102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (64, 60000, 0, 0, false, '2024-12-14 11:10:00', 0, 0, 2, 100,104,105);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (65, 60000, 0, 0, false, '2024-12-14 11:20:00', 0, 0, 2, 200,207,200);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (66, 60000, 0, 0, false, '2024-12-14 11:30:00', 0, 0, 2, 200,206,201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (67, 60000, 0, 0, false, '2024-12-14 11:40:00', 0, 0, 2, 300,301,302);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (68, 60000, 0, 0, false, '2024-12-14 11:50:00', 0, 0, 2, 300,304,305);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (69, 60000, 0, 0, false, '2024-12-14 12:00:00', 0, 0, 2, 400,401,402);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (70, 60000, 0, 0, false, '2024-12-14 12:10:00', 0, 0, 2, 400,404,405);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (71, 60000, 0, 0, false, '2024-12-14 12:20:00', 0, 0, 2, 100,103,104);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (72, 60000, 0, 0, false, '2024-12-14 12:30:00', 0, 0, 2, 200,202,203);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (73, 60000, 0, 0, false, '2024-12-14 12:40:00', 0, 0, 2, 200,206,207);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (74, 60000, 0, 0, false, '2024-12-14 12:50:00', 0, 0, 2, 300,303,304);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_hall,fk_category,  fk_team_home, fk_team_guest) VALUES (75, 60000, 0, 0, false, '2024-12-14 13:00:00', 0, 0, 2, 400,403,404);
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


-- night


INSERT INTO categories (id,name,remark,state,type,fk_parent_category, show_on_display) VALUES (1000,'Night Plausch',NULL,0,2,NULL, false);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category, show_on_display) VALUES (2000,'Night Plausch Winner',NULL,5,0,1000, false);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category, show_on_display) VALUES (3000,'Night Plausch Loser',NULL,5,0,1000, false);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category, show_on_display) VALUES (5000,'Night Guuggenmusigen und Firmen',NULL,1,0,NULL, false);
INSERT INTO categories (id,name,remark,state,type,fk_parent_category, show_on_display) VALUES (8000,'Night Mixed',NULL,1,0,NULL, false);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1001,'Chäpslifrönde',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1002,'Chöbeljäger Lozärn',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1003,'Ab is Chränzli!',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1004,'Gummibierbande',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1005,'Soigöue-Hunters',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1006,'Poor Scousers',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1007,'Die VirtuosenVorstandsVünf',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1008,'Pantteri Salmiakki',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1009,'Souuhuufe',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1010,'Töffliclub Schongau',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1011,'Göttis',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1012,'(G)OLDIES',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1013,'Jägers',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1014,'Crazy Parents',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1015,'Gummibierbande 2',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1016,'Cafe',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1017,'Hopfenhelden',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1018,'Berbuer Birni Boys',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1019,'mer luege',false,false,0,1000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (1020,'TCH World Tour',false,false,0,1000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5001,'Tätschgeili Borgis',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5002,'Märebrätscher Sämpech',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5003,'die volle tolle Borgis',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5004,'Rhythmus Rammler Schlierbach',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5005,'Stägis',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5006,'Steil esch Geil !',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5007,'Bäribrommer',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5008,'Backhand Heroes TCH',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5009,'Bodelosi Borgis',false,false,0,5000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (5010,'Lüütertüter Höudisriede',false,false,0,5000);

INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8001,'Team Europaweg',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8002,'Gaumatschus',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8003,'Diddy Party',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8004,'Schinkliverteidiger',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8005,'SchinkliverteidigerInne',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8006,'midnight shot',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8007,'Figarokings',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8008,'Chogufesch Gäng',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8009,'Sportpromillos',false,false,0,8000);
INSERT INTO teams (id,name,flag_paid,flag_placeholder,rank,fk_category) VALUES (8010,'Fehlend',false,false,0,8000);

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

-- INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (9999, 'Pause', true, true, 0, null);


-- Yetis Cup Together Stage 1 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1001,60000,0,0,FALSE,'2024-12-14 15:40:00',0,0,1000,1,1003,1001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1002,60000,0,0,FALSE,'2024-12-14 15:50:00',0,0,1000,1,1002,1004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1003,60000,0,0,FALSE,'2024-12-14 16:00:00',0,0,1000,1,1010,1006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1004,60000,0,0,FALSE,'2024-12-14 16:10:00',0,0,1000,1,1007,1009);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1005,60000,0,0,FALSE,'2024-12-14 16:20:00',0,0,1000,1,1005,1008);
-- Cat C Stage 1 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1006,60000,0,0,FALSE,'2024-12-14 16:30:00',0,0,8000,1,8001,8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1007,60000,0,0,FALSE,'2024-12-14 16:40:00',0,0,8000,1,8003,8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1008,60000,0,0,FALSE,'2024-12-14 16:50:00',0,0,8000,1,8005,8006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (1009,60000,0,0,FALSE,'2024-12-14 17:00:00',0,0,8000,1,8007,8008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10010,60000,0,0,FALSE,'2024-12-14 17:10:00',0,0,8000,1,8009,8010);
-- Yetis Cup Together Stage 2 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10011,60000,0,0,FALSE,'2024-12-14 17:20:00',0,0,1000,1,1011,1012);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10012,60000,0,0,FALSE,'2024-12-14 17:30:00',0,0,1000,1,1018,1013);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10013,60000,0,0,FALSE,'2024-12-14 17:40:00',0,0,1000,1,1014,1015);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10014,60000,0,0,FALSE,'2024-12-14 17:50:00',0,0,1000,1,1019,1020);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10015,60000,0,0,FALSE,'2024-12-14 18:00:00',0,0,1000,1,1016,1017);
-- Cat C Stage 2 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10016,60000,0,0,FALSE,'2024-12-14 18:10:00',0,0,8000,1,8010,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10017,60000,0,0,FALSE,'2024-12-14 18:20:00',0,0,8000,1,8002,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10018,60000,0,0,FALSE,'2024-12-14 18:30:00',0,0,8000,1,8004,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10019,60000,0,0,FALSE,'2024-12-14 18:40:00',0,0,8000,1,8006,8007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10020,60000,0,0,FALSE,'2024-12-14 18:50:00',0,0,8000,1,8008,8009);
-- Yetis Cup Winner Stage 1 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10021,60000,0,0,TRUE ,'2024-12-14 19:00:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10022,60000,0,0,TRUE ,'2024-12-14 19:10:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10023,60000,0,0,TRUE ,'2024-12-14 19:20:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10024,60000,0,0,TRUE ,'2024-12-14 19:30:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10025,60000,0,0,TRUE ,'2024-12-14 19:40:00',0,0,2000,1,2900,2900);
-- Cat B Stage 3 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10026,60000,0,0,FALSE,'2024-12-14 19:50:00',0,0,5000,1,5001,5008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10027,60000,0,0,FALSE,'2024-12-14 20:00:00',0,0,5000,1,5003,5010);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10028,60000,0,0,FALSE,'2024-12-14 20:10:00',0,0,5000,1,5005,5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10029,60000,0,0,FALSE,'2024-12-14 20:20:00',0,0,5000,1,5007,5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10030,60000,0,0,FALSE,'2024-12-14 20:30:00',0,0,5000,1,5009,5006);
-- Yetis Cup Looser Stage 2 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10031,60000,0,0,TRUE ,'2024-12-14 20:40:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10032,60000,0,0,TRUE ,'2024-12-14 20:50:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10033,60000,0,0,TRUE ,'2024-12-14 21:00:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10034,60000,0,0,TRUE ,'2024-12-14 21:10:00',0,0,3000,1,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10035,60000,0,0,TRUE ,'2024-12-14 21:20:00',0,0,3000,1,3900,3900);
-- Cat B Stage 4 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10095,60000,0,0,FALSE,'2024-12-14 21:30:00',0,0,8000,1,8006,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10096,60000,0,0,FALSE,'2024-12-14 21:40:00',0,0,8000,1,8008,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10097,60000,0,0,FALSE,'2024-12-14 21:50:00',0,0,8000,1,8010,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10098,60000,0,0,FALSE,'2024-12-14 22:00:00',0,0,8000,1,8002,8007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10099,60000,0,0,FALSE,'2024-12-14 22:10:00',0,0,8000,1,8004,8009);
-- Yetis Cup Winner Stage 3 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10041,60000,0,0,TRUE ,'2024-12-14 22:20:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10042,60000,0,0,TRUE ,'2024-12-14 22:30:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10043,60000,0,0,TRUE ,'2024-12-14 22:40:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10044,60000,0,0,TRUE ,'2024-12-14 22:50:00',0,0,2000,1,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10045,60000,0,0,TRUE ,'2024-12-14 23:00:00',0,0,2000,1,2900,2900);
-- Cat B Stage 5 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10046,60000,0,0,FALSE,'2024-12-14 23:10:00',0,0,5000,1,5004,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10047,60000,0,0,FALSE,'2024-12-14 23:20:00',0,0,5000,1,5006,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10048,60000,0,0,FALSE,'2024-12-14 23:30:00',0,0,5000,1,5008,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10049,60000,0,0,FALSE,'2024-12-14 23:40:00',0,0,5000,1,5010,5007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10050,60000,0,0,FALSE,'2024-12-14 23:50:00',0,0,5000,1,5002,5009);
-- semis inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10051,60000,0,0,TRUE ,'2024-12-15 00:00:00',0,1,2000,1,2901,2902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10052,60000,0,0,TRUE ,'2024-12-15 00:10:00',0,1,3000,1,3901,3902);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10053,60000,0,0,TRUE ,'2024-12-15 00:20:00',0,1,5000,1,5901,5904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10054,60000,0,0,TRUE ,'2024-12-15 00:30:00',0,1,8000,1,8901,8904);
-- pause
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_guest,fk_team_home) VALUES (10055,60000,0,0,TRUE ,'2024-12-15 00:40:00',1,0,null,1,9999,9999);
-- finals inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10056,60000,0,0,TRUE ,'2024-12-15 00:50:00',0,2,2000,1,2905,2906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10057,60000,0,0,TRUE ,'2024-12-15 01:00:00',0,2,3000,1,3905,3906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10058,60000,0,0,TRUE ,'2024-12-15 01:10:00',0,2,5000,1,5905,5906);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10059,60000,0,0,TRUE ,'2024-12-15 01:20:00',0,2,8000,1,8905,8906);
-- Yetis Cup Together Stage 1 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10060,60000,0,0,FALSE,'2024-12-14 15:40:00',0,0,1000,2,1013,1011);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10061,60000,0,0,FALSE,'2024-12-14 15:50:00',0,0,1000,2,1012,1014);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10062,60000,0,0,FALSE,'2024-12-14 16:00:00',0,0,1000,2,1020,1016);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10063,60000,0,0,FALSE,'2024-12-14 16:10:00',0,0,5000,2,5007,5008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10064,60000,0,0,FALSE,'2024-12-14 16:20:00',0,0,1000,2,1015,1018);
-- Cat B Stage 1 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10065,60000,0,0,FALSE,'2024-12-14 16:30:00',0,0,5000,2,5001,5002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10066,60000,0,0,FALSE,'2024-12-14 16:40:00',0,0,5000,2,5003,5004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10067,60000,0,0,FALSE,'2024-12-14 16:50:00',0,0,5000,2,5005,5006);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10068,60000,0,0,FALSE,'2024-12-14 17:00:00',0,0,1000,2,1017,1019);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10069,60000,0,0,FALSE,'2024-12-14 17:10:00',0,0,5000,2,5009,5010);
-- Yetis Cup Together Stage 2 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10070,60000,0,0,FALSE,'2024-12-14 17:20:00',0,0,1000,2,1001,1002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10071,60000,0,0,FALSE,'2024-12-14 17:30:00',0,0,1000,2,1006,1007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10072,60000,0,0,FALSE,'2024-12-14 17:40:00',0,0,1000,2,1004,1005);
--exception grusigi lüütertüter
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10073,60000,0,0,FALSE,'2024-12-14 17:50:00',0,0,5000,2,5006,5007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10074,60000,0,0,FALSE,'2024-12-14 18:00:00',0,0,1000,2,1008,1003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10075,60000,0,0,FALSE,'2024-12-14 18:10:00',0,0,1000,2,1009,1010);
-- Cat B Stage 2 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10076,60000,0,0,FALSE,'2024-12-14 18:20:00',0,0,5000,2,5010,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10077,60000,0,0,FALSE,'2024-12-14 18:30:00',0,0,5000,2,5002,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10078,60000,0,0,FALSE,'2024-12-14 18:40:00',0,0,5000,2,5004,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10079,60000,0,0,FALSE,'2024-12-14 18:50:00',0,0,5000,2,5008,5009);
-- Yetis Cup Looser Stage 1 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10080,60000,0,0,TRUE ,'2024-12-14 19:00:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10081,60000,0,0,TRUE ,'2024-12-14 19:10:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10082,60000,0,0,TRUE ,'2024-12-14 19:20:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10083,60000,0,0,TRUE ,'2024-12-14 19:30:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10084,60000,0,0,TRUE ,'2024-12-14 19:40:00',0,0,3000,2,3900,3900);
-- Cat C Stage 3 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10085,60000,0,0,FALSE,'2024-12-14 19:50:00',0,0,8000,2,8001,8008);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10086,60000,0,0,FALSE,'2024-12-14 20:00:00',0,0,8000,2,8003,8010);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10087,60000,0,0,FALSE,'2024-12-14 20:10:00',0,0,8000,2,8005,8002);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10088,60000,0,0,FALSE,'2024-12-14 20:20:00',0,0,8000,2,8007,8004);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10089,60000,0,0,FALSE,'2024-12-14 20:30:00',0,0,8000,2,8009,8006);
-- Yetis Cup Winner Stage 2 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10090,60000,0,0,TRUE ,'2024-12-14 20:40:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10091,60000,0,0,TRUE ,'2024-12-14 20:50:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10092,60000,0,0,TRUE ,'2024-12-14 21:00:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10093,60000,0,0,TRUE ,'2024-12-14 21:10:00',0,0,2000,2,2900,2900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10094,60000,0,0,TRUE ,'2024-12-14 21:20:00',0,0,2000,2,2900,2900);
-- Cat C Stage 4 Matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10036,60000,0,0,FALSE,'2024-12-14 21:30:00',0,0,5000,2,5006,5001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10037,60000,0,0,FALSE,'2024-12-14 21:40:00',0,0,5000,2,5008,5003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10038,60000,0,0,FALSE,'2024-12-14 21:50:00',0,0,5000,2,5010,5005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10039,60000,0,0,FALSE,'2024-12-14 22:00:00',0,0,5000,2,5002,5007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (10040,60000,0,0,FALSE,'2024-12-14 22:10:00',0,0,5000,2,5004,5009);
-- Yetis Cup Looser Stage 3 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100100,60000,0,0,TRUE ,'2024-12-14 22:20:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100101,60000,0,0,TRUE ,'2024-12-14 22:30:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100102,60000,0,0,TRUE ,'2024-12-14 22:40:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100103,60000,0,0,TRUE ,'2024-12-14 22:50:00',0,0,3000,2,3900,3900);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100104,60000,0,0,TRUE ,'2024-12-14 23:00:00',0,0,3000,2,3900,3900);
-- Cat B Stage 5 Inpuls
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100105,60000,0,0,FALSE,'2024-12-14 23:10:00',0,0,8000,2,8004,8001);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100106,60000,0,0,FALSE,'2024-12-14 23:20:00',0,0,8000,2,8006,8003);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100107,60000,0,0,FALSE,'2024-12-14 23:30:00',0,0,8000,2,8008,8005);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100108,60000,0,0,FALSE,'2024-12-14 23:40:00',0,0,8000,2,8010,8007);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100109,60000,0,0,FALSE,'2024-12-14 23:50:00',0,0,8000,2,8002,8009);
-- semis matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100110,60000,0,0,TRUE ,'2024-12-15 00:00:00',0,1,2000,2,2903,2904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100111,60000,0,0,TRUE ,'2024-12-15 00:10:00',0,1,3000,2,3903,3904);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100112,60000,0,0,TRUE ,'2024-12-15 00:20:00',0,1,5000,2,5902,5903);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100113,60000,0,0,TRUE ,'2024-12-15 00:30:00',0,1,8000,2,8902,8903);
-- pause
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100114,60000,0,0,TRUE ,'2024-12-15 00:40:00',1,0,null,2,9999,9999);
-- finals matte
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100115,60000,0,0,TRUE ,'2024-12-15 00:50:00',0,2,2000,2,2908,2907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100116,60000,0,0,TRUE ,'2024-12-15 01:00:00',0,2,3000,2,3908,3907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100117,60000,0,0,TRUE ,'2024-12-15 01:10:00',0,2,5000,2,5908,5907);
INSERT INTO games (id,duration,goals_team_guest,goals_team_home,flag_placeholder,start_date,state,type,fk_category,fk_hall,fk_team_home,fk_team_guest) VALUES (100118,60000,0,0,TRUE ,'2024-12-15 01:20:00',0,2,8000,2,8908,8907);