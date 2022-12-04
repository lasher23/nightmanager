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

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (111,	'Bananas'                  , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (112,	'Die fünf Blitze'               , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (113,	'Die wilden Kerle'                 , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (114,	'Blue Tomato'                      , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (115,	'UH-Flitzer'               , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (116,	'Unihockey-Geischter'               , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (117,	'Die blauen Blitze'           , false, false, 0, 1000);

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (211,	'Red Chilis', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (212,	'Big Monsters', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (213,	'Es esch so wies esch', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (214,	'Fireballs', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (215,	'The big five', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (216,	'Nedis Schwafelweiber', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (217,	'MONTE', false, false, 0, 2000);

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  311,	'The Banana Gang'     ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  312,	'Cannibals 2'       ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  313,	'UnstoppableBoys'     ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  314,	'MACHER'   ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  315,	'Cannibals 1'   ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  316,	'Prime Höudi'   ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  317,	'Coca Cola'   ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  318,	'Crem schnitte'   ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  319,	'Die fetten Fischforellen'   ,         false, false, 0, 3000);



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


--
-- Data for Name: games; Type: TABLE DATA; Schema: public; Owner: postgres                                               221	223

INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (1 , 72000, 0, 0, false, '2022-12-10 08:00:00', 0, 0, 1000, 1,  111,	115);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (2 , 72000, 0, 0, false, '2022-12-10 08:12:00', 0, 0, 1000, 1,  112,	116);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (3 , 72000, 0, 0, false, '2022-12-10 08:24:00', 0, 0, 1000, 1,  113,	117);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (4 , 72000, 0, 0, false, '2022-12-10 08:36:00', 1, 0, 3000, 1,  311,	315);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (5 , 72000, 0, 0, false, '2022-12-10 08:48:00', 0, 0, 3000, 1,  312,	316 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (6 , 72000, 0, 0, false, '2022-12-10 09:00:00', 0, 0, 1000, 1,  114,	111);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (7 , 72000, 0, 0, false, '2022-12-10 09:12:00', 0, 0, 1000, 1,  115,	112);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (8 , 72000, 0, 0, false, '2022-12-10 09:24:00', 0, 0, 1000, 1,  116,	113);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (9 , 72000, 0, 0, false, '2022-12-10 09:36:00', 0, 0, 3000, 1,  313,	317);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (10, 72000, 0, 0, false, '2022-12-10 09:48:00', 0, 0, 3000, 1,  314,	318);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (11, 72000, 0, 0, false, '2022-12-10 10:00:00', 0, 0, 1000, 1,  117,	114);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (12, 72000, 0, 0, false, '2022-12-10 10:12:00', 0, 0, 2000, 1,  211,	213);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (13, 72000, 0, 0, false, '2022-12-10 10:24:00', 0, 0, 3000, 1,  315,	319);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (14, 72000, 0, 0, false, '2022-12-10 10:36:00', 0, 0, 3000, 1,  316,	311);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (15, 72000, 0, 0, false, '2022-12-10 10:48:00', 0, 0, 2000, 1,  215,	217);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (16, 72000, 0, 0, false, '2022-12-10 11:00:00', 0, 0, 2000, 1,  212,	214);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (17, 72000, 0, 0, false, '2022-12-10 11:12:00', 0, 0, 2000, 1,  216,	211);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (18, 72000, 0, 0, false, '2022-12-10 11:24:00', 1, 0, 3000, 1,  317,	312 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (19, 72000, 0, 0, false, '2022-12-10 11:36:00', 1, 0, 3000, 1,  318,	313 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (20, 72000, 0, 0, false, '2022-12-10 11:48:00', 0, 0, null, 1,  9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (21, 72000, 0, 0, false, '2022-12-10 12:00:00', 0, 0, null, 1,  9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (22, 72000, 0, 0, false, '2022-12-10 12:12:00', 0, 0, null, 1,  9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (23, 72000, 0, 0, false, '2022-12-10 12:24:00', 0, 0, 2000, 1,  213,	215);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (24, 72000, 0, 0, false, '2022-12-10 12:36:00', 0, 0, 2000, 1,  217,	212);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (25, 72000, 0, 0, false, '2022-12-10 12:48:00', 0, 0, 2000, 1,  214,	216);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (26, 72000, 0, 0, false, '2022-12-10 13:00:00', 0, 0, 3000, 1,  319,	314);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (27, 72000, 0, 0, false, '2022-12-10 13:12:00', 1, 0, null, 1,  9999,	9999 );

INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (28, 72000, 0, 0, false, '2022-12-10 13:24:00',0, 1, 1000, 1, 1100, 1101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (29, 72000, 0, 0, false, '2022-12-10 13:36:00',0, 1, 2000, 1, 2100, 2101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (30, 72000, 0, 0, false, '2022-12-10 13:48:00',0, 1, 3000, 1, 3100, 3101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (31, 72000, 0, 0, false, '2022-12-10 14:00:00',1, 0, null, 1, 9999, 9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (32, 72000, 0, 0, false, '2022-12-10 14:12:00',0, 2, 1000, 1, 1200, 1201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (33, 72000, 0, 0, false, '2022-12-10 14:24:00',0, 2, 2000, 1, 2200, 2201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (34, 72000, 0, 0, false, '2022-12-10 14:36:00',0, 2, 3000, 1, 3300, 3301);


INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (41, 72000, 0, 0, false, '2022-12-10 08:00:00', 0, 0, 2000, 2, 211,	215);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (42, 72000, 0, 0, false, '2022-12-10 08:12:00', 0, 0, 2000, 2, 212,	216);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (43, 72000, 0, 0, false, '2022-12-10 08:24:00', 0, 0, 2000, 2, 213,	217);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (44, 72000, 0, 0, false, '2022-12-10 08:36:00', 1, 0, 3000, 2, 318,	319);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (45, 72000, 0, 0, false, '2022-12-10 08:48:00', 1, 0, 3000, 2, 313,	314);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (46, 72000, 0, 0, false, '2022-12-10 09:00:00', 0, 0, 2000, 2, 214,	211);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (47, 72000, 0, 0, false, '2022-12-10 09:12:00', 0, 0, 2000, 2, 215,	212);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (48, 72000, 0, 0, false, '2022-12-10 09:24:00', 0, 0, 2000, 2, 216,	213);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (49, 72000, 0, 0, false, '2022-12-10 09:36:00', 0, 0, 3000, 2, 311,	312);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (50, 72000, 0, 0, false, '2022-12-10 09:48:00', 0, 0, 3000, 2, 315,	316);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (51, 72000, 0, 0, false, '2022-12-10 10:00:00', 1, 0, 1000, 2, 111,	113);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (52, 72000, 0, 0, false, '2022-12-10 10:12:00', 1, 0, 2000, 2, 217,	214);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (53, 72000, 0, 0, false, '2022-12-10 10:24:00', 0, 0, 3000, 2, 317,	318);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (54, 72000, 0, 0, false, '2022-12-10 10:36:00', 0, 0, 3000, 2, 312,	313);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (55, 72000, 0, 0, false, '2022-12-10 10:48:00', 0, 0, 1000, 2, 115,	117);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (56, 72000, 0, 0, false, '2022-12-10 11:00:00', 0, 0, 1000, 2, 112,	114);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (57, 72000, 0, 0, false, '2022-12-10 11:12:00', 0, 0, 1000, 2, 116,	111);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (58, 72000, 0, 0, false, '2022-12-10 11:24:00', 0, 0, 3000, 2, 319,	311);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (59, 72000, 0, 0, false, '2022-12-10 11:36:00', 0, 0, 3000, 2, 314,	315);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (60, 72000, 0, 0, false, '2022-12-10 11:48:00', 0, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (61, 72000, 0, 0, false, '2022-12-10 12:00:00', 1, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (62, 72000, 0, 0, false, '2022-12-10 12:12:00', 0, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (63, 72000, 0, 0, false, '2022-12-10 12:24:00', 0, 0, 1000, 2, 113,	115);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (64, 72000, 0, 0, false, '2022-12-10 12:36:00', 0, 0, 1000, 2, 117,	112);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (65, 72000, 0, 0, false, '2022-12-10 12:48:00', 0, 0, 1000, 2, 114,	116);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (66, 72000, 0, 0, false, '2022-12-10 13:00:00', 0, 0, 1000, 2, 316,	317);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (67, 72000, 0, 0, false, '2022-12-10 13:12:00', 0, 0, null, 2, 9999,	9999  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (75, 72000, 0, 0, true , '2022-12-10 13:24:00', 0, 1, 1000, 2, 1103, 1102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (76, 72000, 0, 0, true , '2022-12-10 13:36:00', 0, 1, 2000, 2, 2103, 2102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (77, 72000, 0, 0, true , '2022-12-10 13:48:00', 0, 1, 3000, 2, 3103, 3102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (67, 72000, 0, 0, false, '2022-12-10 14:00:00', 0, 0, null, 2, 9999,	9999  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (78, 72000, 0, 0, true , '2022-12-10 14:12:00', 0, 2, 1000, 2, 1203, 1202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (79, 72000, 0, 0, true , '2022-12-10 14:24:00', 0, 2, 2000, 2, 2203, 2202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (80, 72000, 0, 0, true , '2022-12-10 14:36:00', 0, 2, 3000, 2, 3303, 3302);

