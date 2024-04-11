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


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category) VALUES (1000, 'Kategorie 1', NULL, 1, 0, NULL);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category) VALUES (2000, 'Kategorie 2', NULL, 1, 0, NULL);
INSERT INTO public.categories (id, name, remark, state, type, fk_parent_category) VALUES (3000, 'Kategorie 3', NULL, 1, 0, NULL);


--
-- Data for Name: halls; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.halls (id, name) VALUES (1, 'Inpuls');
INSERT INTO public.halls (id, name) VALUES (2, 'Matte');


--
-- Data for Name: teams; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (111,	'Die wilden Kerle'                  , false, false, 0, 1000,'+41792173542');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (112,	'Hockey Gangster'               , false, false, 0, 1000,'+41787718508');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (113,	'Sonic'                 , false, false, 0, 1000,'+41794827902');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (114,	'Wilde Geister '                      , false, false, 0, 1000,'+41787718113');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (115,	'Stocktornados'               , false, false, 0, 1000,'+41415302853');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (116,	'Mega Spagetthis'               , false, false, 0, 1000,'+41786154856');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (117,	'Die 5 Freunde'           , false, false, 0, 1000,'+41788099787');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (118,	'die schnellsten Fünf'           , false, false, 0, 1000,'+41797673882');

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (211,	'Die fünf Blitze', false, false, 0, 2000, '+41788148161');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (212,	'UHC Pinguine Schötz', false, false, 0, 2000, '+41799679059');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (213,	'Red Chili', false, false, 0, 2000, '+41793794192');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (214,	'Green Mambas', false, false, 0, 2000, '+41786028118');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (215,	'Baubu chäpsler', false, false, 0, 2000, '+41793145848');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (216,	'Die 5 Gaming Kings ', false, false, 0, 2000, '+41796518785');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (217,	'Rainer Wiiber', false, false, 0, 2000, '+41797673882');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (218,	'Power-Girls'   ,         false, false, 0, 2000, '+41763303279');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (219,	'UHC Tacos'   ,         false, false, 0, 2000, '+41788420704');

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  310,	'UHC Baubu'     ,         false, false, 0, 3000,'+41793145848');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  311,	'Junioren Ballwil'       ,         false, false, 0, 3000,'+41793145848');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  312,	'Fuultier'     ,         false, false, 0, 3000,'+41791940849');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  313,	'Die illegalen Nachtarbeiter'  ,         false, false, 0, 3000,'+41774168793');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  314,	E'Besser z\'spot als nie'   ,         false, false, 0, 3000,'+41772685387');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  315,	'Nike pro Lovers'   ,         false, false, 0, 3000,'+41796266883');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  316,	'schlemmer god emmer'   ,         false, false, 0, 3000,'+41791940849');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  317,	'Die coolen Sempacher'   ,         false, false, 0, 3000,'+41787951828');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  318,	'Die Legalen Nachtarbeiter'   ,         false, false, 0, 3000,'+41796618298');
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category, phone_number) VALUES (  319,	'Floor-Boys'   ,         false, false, 0, 3000,'+41796618298');


INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1100, 'HF Kat. 1 (1.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2100, 'HF Kat. 2 (1.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3100, 'HF Kat. 3 (1.)', true, true, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1101, 'HF Kat. 1 (4.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2101, 'HF Kat. 2 (4.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3101, 'HF Kat. 3 (4.)', true, true, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1200, 'Final Kat. 1 (Sieger HF 1.-4.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2200, 'Final Kat. 2 (Sieger HF 1.-4.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3300, 'Final Kat. 3 (Sieger HF 1.-4.)', true, true, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1201, 'Final Kat. 1  (Sieger HF 2.-3.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2201, 'Final Kat. 2  (Sieger HF 2.-3.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3301, 'Final Kat. 3  (Sieger HF 2.-3.)', true, true, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1102, 'HF Kat. 1 (2.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2102, 'HF Kat. 2 (2.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3102, 'HF Kat. 3 (2.)', true, true, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1103, 'HF Kat. 1 (3.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2103, 'HF Kat. 2 (3.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3103, 'HF Kat. 3 (3.)', true, true, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1202, 'KF Kat. 1 (Verlierer HF 1.-4.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2202, 'KF Kat. 2 (Verlierer HF 1.-4.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3302, 'KF Kat. 3 (Verlierer HF 1.-4.)', true, true, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1203, 'KF Kat. 1 (Verlierer HF 2.-3.)', true, true, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2203, 'KF Kat. 2 (Verlierer HF 2.-3.)', true, true, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3303, 'KF Kat. 3 (Verlierer HF 2.-3.)', true, true, 0, 3000);

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (9999, 'Pause', true, true, 0, 3000);





INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (1 , 60000, 0, 0, false, '2023-12-09 08:00:00', 0, 0, 2000, 1, 211, 212);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (2 , 60000, 0, 0, false, '2023-12-09 08:10:00', 0, 0, 2000, 1, 213, 214);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (3 , 60000, 0, 0, false, '2023-12-09 08:20:00', 0, 0, 2000, 1, 215, 216);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (4 , 60000, 0, 0, false, '2023-12-09 08:30:00', 0, 0, 2000, 1, 217, 218);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (5 , 60000, 0, 0, false, '2023-12-09 08:40:00', 0, 0, 2000, 1, 219, 211);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (6 , 60000, 0, 0, false, '2023-12-09 08:50:00', 0, 0, 1000, 1, 111, 115);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (7 , 60000, 0, 0, false, '2023-12-09 09:00:00', 0, 0, 1000, 1, 112, 116);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (8 , 60000, 0, 0, false, '2023-12-09 09:10:00', 0, 0, 2000, 1, 216, 217);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (9 , 60000, 0, 0, false, '2023-12-09 09:20:00', 0, 0, 2000, 1, 212, 213);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (10, 60000, 0, 0, false, '2023-12-09 09:30:00', 0, 0, 2000, 1, 214, 215);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (11, 60000, 0, 0, false, '2023-12-09 09:40:00', 0, 0, 2000, 1, 218, 219);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (12, 60000, 0, 0, false, '2023-12-09 09:50:00', 0, 0, 3000, 1, 312, 319);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (13, 60000, 0, 0, false, '2023-12-09 10:00:00', 0, 0, 1000, 1, 113, 118);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (14, 60000, 0, 0, false, '2023-12-09 10:10:00', 0, 0, 1000, 1, 114, 117);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (15, 60000, 0, 0, false, '2023-12-09 10:20:00', 0, 0, 3000, 1, 314, 311);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (16, 60000, 0, 0, false, '2023-12-09 10:30:00', 0, 0, 3000, 1, 316, 313);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (17, 60000, 0, 0, false, '2023-12-09 10:40:00', 0, 0, 3000, 1, 310, 317);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (18, 60000, 0, 0, false, '2023-12-09 10:50:00', 0, 0, 3000, 1, 318, 315);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (19, 60000, 0, 0, false, '2023-12-09 11:00:00', 0, 0, 1000, 1, 117, 111);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (20, 60000, 0, 0, false, '2023-12-09 11:10:00', 0, 0, 1000, 1, 118, 112);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (21, 60000, 0, 0, false, '2023-12-09 11:20:00', 0, 0, 3000, 1, 317, 312);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (22, 60000, 0, 0, false, '2023-12-09 11:30:00', 0, 0, 3000, 1, 315, 310);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (23, 60000, 0, 0, false, '2023-12-09 11:40:00', 0, 0, 3000, 1, 311, 316);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (24, 60000, 0, 0, false, '2023-12-09 11:50:00', 0, 0, 3000, 1, 313, 318);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (25, 60000, 0, 0, false, '2023-12-09 12:00:00', 0, 0, 1000, 1, 116, 113);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (26, 60000, 0, 0, false, '2023-12-09 12:10:00', 0, 0, 1000, 1, 115, 114);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (27, 60000, 0, 0, false, '2023-12-09 12:20:00', 0, 0, 3000, 1, 315, 312);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (28, 60000, 0, 0, false, '2023-12-09 12:30:00', 0, 0, 3000, 1, 317, 314);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (29, 60000, 0, 0, false, '2023-12-09 12:40:00', 0, 0, 3000, 1, 313, 310);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (30, 60000, 0, 0, false, '2023-12-09 12:50:00', 0, 0, 3000, 1, 319, 316);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (31, 60000, 0, 0, false, '2023-12-09 13:00:00', 0, 0, 3000, 1, 311, 318);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (32, 60000, 0, 0, false, '2023-12-09 13:10:00', 0, 0, 3000, 1, 319, 314);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (33, 60000, 0, 0, false, '2023-12-09 13:20:00', 0, 0, 2000, 1, 215, 213);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (34, 60000, 0, 0, false, '2023-12-09 13:30:00', 0, 0, 2000, 1, 212, 218);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (35, 60000, 0, 0, false, '2023-12-09 13:40:00', 0, 0, 2000, 1, 217, 219);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (36, 60000, 0, 0, false, '2023-12-09 13:50:00', 0, 0, 2000, 1, 214, 216);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (37, 60000, 0, 0, false, '2023-12-09 14:00:00', 0, 0, 2000, 1, 213, 211);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (38, 60000, 0, 0, true , '2023-12-09 14:10:00', 0, 1, 3000, 1, 3100, 3101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (39, 60000, 0, 0, true , '2023-12-09 14:20:00', 0, 1, 1000, 1, 1100, 1101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (40, 60000, 0, 0, true , '2023-12-09 14:30:00', 0, 1, 2000, 1, 2100, 2101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (41, 60000, 0, 0, false, '2023-12-09 14:40:00', 1, 0, null, 1, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (42, 60000, 0, 0, true , '2023-12-09 14:50:00', 0, 2, 3000, 1, 3300, 3301);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (43, 60000, 0, 0, true , '2023-12-09 15:00:00', 0, 2, 1000, 1, 1200, 1201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (44, 60000, 0, 0, true , '2023-12-09 15:10:00', 0, 2, 2000, 1, 2200, 2201);


INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (45, 60000, 0, 0, false, '2023-12-09 08:00:00', 0, 0, 3000, 2, 310, 311);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (46, 60000, 0, 0, false, '2023-12-09 08:10:00', 0, 0, 3000, 2, 312, 313);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (47, 60000, 0, 0, false, '2023-12-09 08:20:00', 0, 0, 3000, 2, 314, 315);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (48, 60000, 0, 0, false, '2023-12-09 08:30:00', 0, 0, 3000, 2, 316, 317);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (49, 60000, 0, 0, false, '2023-12-09 08:40:00', 0, 0, 3000, 2, 318, 319);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (50, 60000, 0, 0, false, '2023-12-09 08:50:00', 0, 0, 1000, 2, 113, 117);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (51, 60000, 0, 0, false, '2023-12-09 09:00:00', 0, 0, 1000, 2, 114, 118);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (52, 60000, 0, 0, false, '2023-12-09 09:10:00', 0, 0, 3000, 2, 311, 312);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (53, 60000, 0, 0, false, '2023-12-09 09:20:00', 0, 0, 3000, 2, 319, 310);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (54, 60000, 0, 0, false, '2023-12-09 09:30:00', 0, 0, 3000, 2, 313, 314);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (55, 60000, 0, 0, false, '2023-12-09 09:40:00', 0, 0, 3000, 2, 315, 316);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (56, 60000, 0, 0, false, '2023-12-09 09:50:00', 0, 0, 2000, 2, 213, 216);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (57, 60000, 0, 0, false, '2023-12-09 10:00:00', 0, 0, 3000, 2, 317, 318);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (58, 60000, 0, 0, false, '2023-12-09 10:10:00', 0, 0, 1000, 2, 111, 116);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (59, 60000, 0, 0, false, '2023-12-09 10:20:00', 0, 0, 1000, 2, 112, 115);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (60, 60000, 0, 0, false, '2023-12-09 10:30:00', 0, 0, 2000, 2, 211, 214);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (61, 60000, 0, 0, false, '2023-12-09 10:40:00', 0, 0, 2000, 2, 215, 218);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (62, 60000, 0, 0, false, '2023-12-09 10:50:00', 0, 0, 2000, 2, 217, 212);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (63, 60000, 0, 0, false, '2023-12-09 11:00:00', 0, 0, 1000, 2, 115, 113);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (64, 60000, 0, 0, false, '2023-12-09 11:10:00', 0, 0, 1000, 2, 116, 114);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (65, 60000, 0, 0, false, '2023-12-09 11:20:00', 0, 0, 2000, 2, 216, 219);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (66, 60000, 0, 0, false, '2023-12-09 11:30:00', 0, 0, 2000, 2, 214, 217);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (67, 60000, 0, 0, false, '2023-12-09 11:40:00', 0, 0, 2000, 2, 212, 215);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (68, 60000, 0, 0, false, '2023-12-09 11:50:00', 0, 0, 2000, 2, 218, 211);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (69, 60000, 0, 0, false, '2023-12-09 12:00:00', 0, 0, 1000, 2, 118, 111);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (70, 60000, 0, 0, false, '2023-12-09 12:10:00', 0, 0, 1000, 2, 117, 112);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (71, 60000, 0, 0, false, '2023-12-09 12:20:00', 0, 0, 2000, 2, 219, 215);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (72, 60000, 0, 0, false, '2023-12-09 12:30:00', 0, 0, 2000, 2, 211, 217);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (73, 60000, 0, 0, false, '2023-12-09 12:40:00', 0, 0, 2000, 2, 216, 212);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (74, 60000, 0, 0, false, '2023-12-09 12:50:00', 0, 0, 2000, 2, 218, 214);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (75, 60000, 0, 0, false, '2023-12-09 13:00:00', 0, 0, 2000, 2, 219, 213);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (76, 60000, 0, 0, false, '2023-12-09 13:10:00', 0, 0, 1000, 2, 111, 112);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (77, 60000, 0, 0, false, '2023-12-09 13:20:00', 0, 0, 1000, 2, 117, 118);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (78, 60000, 0, 0, false, '2023-12-09 13:30:00', 0, 0, 1000, 2, 115, 116);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (79, 60000, 0, 0, false, '2023-12-09 13:40:00', 0, 0, 1000, 2, 113, 114);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (80, 60000, 0, 0, false, '2023-12-09 13:50:00', 1, 0, null, 2, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (81, 60000, 0, 0, false, '2023-12-09 14:00:00', 1, 0, null, 2, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (82, 60000, 0, 0, true , '2023-12-09 14:10:00', 0, 1, 3000, 2, 3102, 3103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (83, 60000, 0, 0, true , '2023-12-09 14:20:00', 0, 1, 1000, 2, 1102, 1103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (84, 60000, 0, 0, true , '2023-12-09 14:30:00', 0, 1, 2000, 2, 2102, 2103);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (85, 60000, 0, 0, false, '2023-12-09 14:40:00', 1, 0, null, 2, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (86, 60000, 0, 0, true , '2023-12-09 14:50:00', 0, 2, 3000, 2, 3302, 3303);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (87, 60000, 0, 0, true , '2023-12-09 15:00:00', 0, 2, 1000, 2, 1202, 1203);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (88, 60000, 0, 0, true , '2023-12-09 15:10:00', 0, 2, 2000, 2, 2202, 2203);