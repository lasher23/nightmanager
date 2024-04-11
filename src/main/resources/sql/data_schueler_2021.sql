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

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (111,	'Lattenknaller'                  , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (112,	'Die fünf Flitzer'               , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (113,	'Black Panthers'                 , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (114,	'Rasemäier'                      , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (115,	'STV Rickenbach 1'               , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (116,	'STV Rickenbach 2'               , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (121,	'Die Weihnachtsrocker'           , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (122,	'Schlierbach 2'                  , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (123,	'Jugi Schötz 1'                  , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (124,	'Jugi Schötz 2'                  , false, false, 0, 1000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (125,	'Jugi Schötz 3'                  , false, false, 0, 1000);

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (211,	'Schlierbach 1', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (212,	'UHC Busch-Hocker', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (213,	'Die wöude Fischstäbli', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (214,	'Schoggi Pingu', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (215,	'Ineleguan', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (216,	'Snakes', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (217,	'Gummistiefel Piloten', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (221,	'Fighting Girls', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (222,	'Red Chilis', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (223,	'DIY (DoItYourself)', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (224,	'Ballballerer', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (225,	'Die toten Idioten', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (226,	'STV Rickenbach 3', false, false, 0, 2000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (227,	'Team Sackstark', false, false, 0, 2000);

INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  311,	'Apri Kings'     ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  312,	'Brazzers'       ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  313,	'UHC Sursee'     ,         false, false, 0, 3000);
INSERT INTO teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (  314,	'Super Heroes'   ,         false, false, 0, 3000);









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

INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (1 , 72000, 0, 0, false, '2021-12-11 08:00:00', 0, 0, 1000, 1,  221,	223 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (2 , 72000, 0, 0, false, '2021-12-11 08:12:00', 0, 0, 1000, 1,  111,	112 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (3 , 72000, 0, 0, false, '2021-12-11 08:24:00', 0, 0, 1000, 1,  113,	115 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (4 , 72000, 0, 0, false, '2021-12-11 08:36:00', 1, 0, null, 1,  114,	116 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (5 , 72000, 0, 0, false, '2021-12-11 08:48:00', 0, 0, 2000, 1,  9999,	9999 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (6 , 72000, 0, 0, false, '2021-12-11 09:00:00', 0, 0, 2000, 1,  211,	215 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (7 , 72000, 0, 0, false, '2021-12-11 09:12:00', 0, 0, 2000, 1,  212,	216 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (8 , 72000, 0, 0, false, '2021-12-11 09:24:00', 0, 0, 3000, 1,  213,	217 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (9 , 72000, 0, 0, false, '2021-12-11 09:36:00', 0, 0, 3000, 1,  311,	312 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (10, 72000, 0, 0, false, '2021-12-11 09:48:00', 0, 0, 1000, 1,  313,	314 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (11, 72000, 0, 0, false, '2021-12-11 10:00:00', 0, 0, 1000, 1,  112,	113 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (12, 72000, 0, 0, false, '2021-12-11 10:12:00', 0, 0, 1000, 1,  115,	114 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (13, 72000, 0, 0, false, '2021-12-11 10:24:00', 0, 0, 1000, 1,  116,	111 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (14, 72000, 0, 0, false, '2021-12-11 10:36:00', 0, 0, 2000, 1,  121,	122 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (15, 72000, 0, 0, false, '2021-12-11 10:48:00', 0, 0, 2000, 1,  214,	211 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (16, 72000, 0, 0, false, '2021-12-11 11:00:00', 0, 0, 2000, 1,  215,	212 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (17, 72000, 0, 0, false, '2021-12-11 11:12:00', 0, 0, 2000, 1,  216,	213 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (18, 72000, 0, 0, false, '2021-12-11 11:24:00', 1, 0, null, 1,  9999,	9999 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (19, 72000, 0, 0, false, '2021-12-11 11:36:00', 1, 0, null, 1,  9999,	9999 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (20, 72000, 0, 0, false, '2021-12-11 11:48:00', 0, 0, 1000, 1,  124,	125 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (21, 72000, 0, 0, false, '2021-12-11 12:00:00', 0, 0, 2000, 1,  122,	123 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (22, 72000, 0, 0, false, '2021-12-11 12:12:00', 0, 0, 2000, 1,  225,	227 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (23, 72000, 0, 0, false, '2021-12-11 12:24:00', 0, 0, 2000, 1,  222,	224 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (24, 72000, 0, 0, false, '2021-12-11 12:36:00', 0, 0, 3000, 1,  226,	221 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (25, 72000, 0, 0, false, '2021-12-11 12:48:00', 0, 0, 3000, 1,  312,	313 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (26, 72000, 0, 0, false, '2021-12-11 13:00:00', 0, 0, 3000, 1,  314,	311 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (27, 72000, 0, 0, false, '2021-12-11 13:12:00', 1, 0, null, 1,  9999,	9999 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (28, 72000, 0, 0, false, '2021-12-11 13:24:00', 1, 0, null, 1,  9999,	9999 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (29, 72000, 0, 0, false, '2021-12-11 13:36:00', 0, 0, 1000, 1,  125,	121 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (30, 72000, 0, 0, false, '2021-12-11 13:48:00', 0, 0, 1000, 1,  123,	124 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (31, 72000, 0, 0, false, '2021-12-11 14:00:00', 0, 0, 2000, 1,  223,	225 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (32, 72000, 0, 0, false, '2021-12-11 14:12:00', 0, 0, 2000, 1,  227,	222 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (33, 72000, 0, 0, false, '2021-12-11 14:24:00', 0, 0, 2000, 1,  224,	226 );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (34, 72000, 0, 0, false, '2021-12-11 14:36:00', 0, 0, 2000, 1,  217,	214 );

INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (35, 72000, 0, 0, true , '2021-12-11 14:48:00', 0, 1, 1000, 1, 1100, 1101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (36, 72000, 0, 0, true , '2021-12-11 15:00:00', 0, 1, 2000, 1, 2100, 2101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (37, 72000, 0, 0, true , '2021-12-11 15:12:00', 0, 1, 3000, 1, 3100, 3101);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (38, 72000, 0, 0, true , '2021-12-11 15:36:00', 0, 2, 1000, 1, 1200, 1201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (39, 72000, 0, 0, true , '2021-12-11 15:48:00', 0, 2, 2000, 1, 2200, 2201);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (40, 72000, 0, 0, true , '2021-12-11 16:00:00', 0, 2, 3000, 1, 3300, 3301);


INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (41, 72000, 0, 0, false, '2021-12-11 08:00:00', 0, 0, 2000, 2, 227,	224);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (42, 72000, 0, 0, false, '2021-12-11 08:12:00', 0, 0, 1000, 2, 121,	124);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (43, 72000, 0, 0, false, '2021-12-11 08:24:00', 0, 0, 1000, 2, 122,	125);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (44, 72000, 0, 0, false, '2021-12-11 08:36:00', 1, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (45, 72000, 0, 0, false, '2021-12-11 08:48:00', 1, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (46, 72000, 0, 0, false, '2021-12-11 09:00:00', 0, 0, 2000, 2, 221,	225);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (47, 72000, 0, 0, false, '2021-12-11 09:12:00', 0, 0, 2000, 2, 222,	226);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (48, 72000, 0, 0, false, '2021-12-11 09:24:00', 0, 0, 2000, 2, 223,	227);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (49, 72000, 0, 0, false, '2021-12-11 09:36:00', 0, 0, 1000, 2, 123,	121);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (50, 72000, 0, 0, false, '2021-12-11 09:48:00', 0, 0, 1000, 2, 124,	122);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (51, 72000, 0, 0, false, '2021-12-11 10:00:00', 1, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (52, 72000, 0, 0, false, '2021-12-11 10:12:00', 1, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (53, 72000, 0, 0, false, '2021-12-11 10:24:00', 0, 0, 2000, 2, 224,	221);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (54, 72000, 0, 0, false, '2021-12-11 10:36:00', 0, 0, 2000, 2, 225,	222);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (55, 72000, 0, 0, false, '2021-12-11 10:48:00', 0, 0, 2000, 2, 226,	223);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (56, 72000, 0, 0, false, '2021-12-11 11:00:00', 0, 0, 3000, 2, 314,	312);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (57, 72000, 0, 0, false, '2021-12-11 11:12:00', 0, 0, 3000, 2, 311,	313);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (58, 72000, 0, 0, false, '2021-12-11 11:24:00', 0, 0, 1000, 2, 111,	115);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (59, 72000, 0, 0, false, '2021-12-11 11:36:00', 0, 0, 1000, 2, 112,	116);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (60, 72000, 0, 0, false, '2021-12-11 11:48:00', 0, 0, 1000, 2, 113,	114);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (61, 72000, 0, 0, false, '2021-12-11 12:00:00', 1, 0, null, 2, 9999,	9999);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (62, 72000, 0, 0, false, '2021-12-11 12:12:00', 0, 0, 2000, 2, 215,	217);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (63, 72000, 0, 0, false, '2021-12-11 12:24:00', 0, 0, 2000, 2, 212,	214);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (64, 72000, 0, 0, false, '2021-12-11 12:36:00', 0, 0, 2000, 2, 216,	211);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (65, 72000, 0, 0, false, '2021-12-11 12:48:00', 0, 0, 2000, 2, 125,	123);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (66, 72000, 0, 0, false, '2021-12-11 13:00:00', 0, 0, 1000, 2, 114,	111);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (67, 72000, 0, 0, false, '2021-12-11 13:12:00', 0, 0, 1000, 2, 116,	113  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (68, 72000, 0, 0, false, '2021-12-11 13:24:00', 0, 0, 1000, 2, 115,	112  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (69, 72000, 0, 0, false, '2021-12-11 13:36:00', 0, 0, 2000, 2, 213,	215  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (70, 72000, 0, 0, false, '2021-12-11 13:48:00', 0, 0, 2000, 2, 217,	212  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (71, 72000, 0, 0, false, '2021-12-11 14:00:00', 0, 0, 2000, 2, 214,	216  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (72, 72000, 0, 0, false, '2021-12-11 14:12:00', 0, 0, 3000, 2, 312,	311  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (73, 72000, 0, 0, false, '2021-12-11 14:24:00', 0, 0, 3000, 2, 313,	314  );
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (74, 72000, 0, 0, false, '2021-12-11 14:36:00', 0, 0, 2000, 2, 211,	213  );

INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (75, 72000, 0, 0, true , '2021-12-11 14:48:00', 0, 1, 1000, 2, 1103, 1102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (76, 72000, 0, 0, true , '2021-12-11 15:00:00', 0, 1, 2000, 2, 2103, 2102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (77, 72000, 0, 0, true , '2021-12-11 15:12:00', 0, 1, 3000, 2, 3103, 3102);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (78, 72000, 0, 0, true , '2021-12-11 15:36:00', 0, 2, 1000, 2, 1203, 1202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (79, 72000, 0, 0, true , '2021-12-11 15:48:00', 0, 2, 2000, 2, 2203, 2202);
INSERT INTO games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_home, fk_team_guest) VALUES (80, 72000, 0, 0, true , '2021-12-11 16:00:00', 0, 2, 3000, 2, 3303, 3302);

