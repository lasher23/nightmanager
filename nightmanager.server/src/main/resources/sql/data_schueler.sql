--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: application_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- Already inserted in data_nicht.sql
-- INSERT INTO public.application_user (id, password, username) VALUES (1, '$2a$10$uom/HJvZ3hqs9LdSA4QPoeRgx1sWijQGEK7OkzwEMLmP1r4Tb3TFC', 'night');


--
-- Name: application_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.application_user_id_seq', 2, false);


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

INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1001, 'Totenkopf', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1002, 'Totechopfkracherotenkopf', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1003, 'Storm', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1004, 'Balljäger', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1005, 'Jugi Schötz 2', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1006, 'Jugi Schötz 3', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1007, 'Jugi Schötz 4', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1008, 'Wer will Fisch 2.0', false, false, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2001, 'Pinga 3', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2002, 'Höudikracher', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2003, 'Einhorn United', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2004, 'Verrückete Gang', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2005, 'Torjäger', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2006, 'Gommistefupilote', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2007, 'Maishäxler', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2008, 'D''Nossgepfu', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2009, 'Team Shark', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2010, 'Jugi Schötz 1', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2011, 'Uncle Ben''s Reiskörner', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2012, 'Die geilen Chipmunks', false, false, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3001, 'Hürlimaaaaaa', false, false, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3002, 'Spezbuebe', false, false, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3003, 'Ueli Vuitton', false, false, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3004, 'Oral B Genius', false, false, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3005, '#kä plan', false, false, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3006, 'Pinga 1', false, false, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3007, 'Pinga 2', false, false, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1100, 'HF Kat. 1 (1.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2100, 'HF Kat. 2 (1.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3100, 'HF Kat. 3 (1.)', true, true, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1101, 'HF Kat. 1 (4.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2101, 'HF Kat. 2 (4.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3101, 'HF Kat. 3 (4.)', true, true, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1200, 'Final Kat. 1 (Sieger HF 1.-4.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2200, 'Final Kat. 2 (Sieger HF 1.-4.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3300, 'Final Kat. 3 (Sieger HF 1.-4.)', true, true, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1201, 'Final Kat. 1  (Sieger HF 2.-3.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2201, 'Final Kat. 2  (Sieger HF 2.-3.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3301, 'Final Kat. 3  (Sieger HF 2.-3.)', true, true, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1102, 'HF Kat. 1 (2.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2102, 'HF Kat. 2 (2.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3102, 'HF Kat. 3 (2.)', true, true, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1103, 'HF Kat. 1 (3.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2103, 'HF Kat. 2 (3.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3103, 'HF Kat. 3 (3.)', true, true, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1202, 'KF Kat. 1 (Verlierer HF 1.-4.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2202, 'KF Kat. 2 (Verlierer HF 1.-4.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3302, 'KF Kat. 3 (Verlierer HF 1.-4.)', true, true, 0, 3000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (1203, 'KF Kat. 1 (Verlierer HF 2.-3.)', true, true, 0, 1000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (2203, 'KF Kat. 2 (Verlierer HF 2.-3.)', true, true, 0, 2000);
INSERT INTO public.teams (id, name, flag_paid, flag_placeholder, rank, fk_category) VALUES (3303, 'KF Kat. 3 (Verlierer HF 2.-3.)', true, true, 0, 3000);


--
-- Data for Name: games; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (1, 72000, 0, 0, false, '2018-12-08 08:00:00', 0, 0, 3000, 1, 3005, 3001);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (2, 72000, 0, 0, false, '2018-12-08 08:12:00', 0, 0, 1000, 1, 1005, 1001);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (3, 72000, 0, 0, false, '2018-12-08 08:24:00', 0, 0, 1000, 1, 1006, 1002);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (4, 72000, 0, 0, false, '2018-12-08 08:36:00', 0, 0, 2000, 1, 2002, 2001);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (5, 72000, 0, 0, false, '2018-12-08 08:48:00', 0, 0, 3000, 1, 3006, 3002);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (6, 72000, 0, 0, false, '2018-12-08 09:00:00', 0, 0, 2000, 1, 2005, 2003);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (7, 72000, 0, 0, false, '2018-12-08 09:12:00', 0, 0, 2000, 1, 2006, 2004);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (8, 72000, 0, 0, false, '2018-12-08 09:24:00', 0, 0, 1000, 1, 1003, 1008);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (9, 72000, 0, 0, false, '2018-12-08 09:36:00', 0, 0, 3000, 1, 3001, 3004);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (10, 72000, 0, 0, false, '2018-12-08 09:48:00', 0, 0, 1000, 1, 1004, 1005);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (11, 72000, 0, 0, false, '2018-12-08 10:00:00', 0, 0, 2000, 1, 2003, 2002);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (12, 72000, 0, 0, false, '2018-12-08 10:12:00', 0, 0, 2000, 1, 2004, 2005);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (13, 72000, 0, 0, false, '2018-12-08 10:24:00', 0, 0, 3000, 1, 3007, 3003);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (14, 72000, 0, 0, false, '2018-12-08 10:36:00', 0, 0, 2000, 1, 2001, 2006);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (15, 72000, 0, 0, false, '2018-12-08 10:48:00', 0, 0, 1000, 1, 1007, 1001);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (16, 72000, 0, 0, false, '2018-12-08 11:00:00', 0, 0, 1000, 1, 1008, 1002);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (17, 72000, 0, 0, false, '2018-12-08 11:12:00', 0, 0, 3000, 1, 3002, 3005);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (18, 72000, 0, 0, false, '2018-12-08 11:24:00', 0, 0, 2000, 1, 2011, 2007);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (19, 72000, 0, 0, false, '2018-12-08 11:36:00', 0, 0, 2000, 1, 2012, 2008);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (20, 72000, 0, 0, false, '2018-12-08 11:48:00', 0, 0, 2000, 1, 2010, 2009);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (21, 72000, 0, 0, false, '2018-12-08 12:00:00', 0, 0, 3000, 1, 3004, 3007);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (22, 72000, 0, 0, false, '2018-12-08 12:12:00', 0, 0, 1000, 1, 1003, 1006);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (23, 72000, 0, 0, false, '2018-12-08 12:24:00', 0, 0, 1000, 1, 1004, 1007);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (24, 72000, 0, 0, false, '2018-12-08 12:36:00', 0, 0, 2000, 1, 2007, 2010);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (25, 72000, 0, 0, false, '2018-12-08 12:48:00', 0, 0, 3000, 1, 3003, 3006);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (26, 72000, 0, 0, false, '2018-12-08 13:00:00', 0, 0, 2000, 1, 2009, 2012);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (27, 72000, 0, 0, false, '2018-12-08 13:12:00', 0, 0, 2000, 1, 2008, 2011);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (28, 72000, 0, 0, true, '2018-12-08 13:36:00', 0, 1, 1000, 1, 1101, 1100);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (29, 72000, 0, 0, true, '2018-12-08 13:48:00', 0, 1, 2000, 1, 2101, 2100);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (30, 72000, 0, 0, true, '2018-12-08 14:00:00', 0, 1, 3000, 1, 3101, 3100);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (31, 72000, 0, 0, true, '2018-12-08 14:24:00', 0, 2, 1000, 1, 1201, 1200);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (32, 72000, 0, 0, true, '2018-12-08 14:36:00', 0, 2, 2000, 1, 2201, 2200);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (33, 72000, 0, 0, true, '2018-12-08 14:48:00', 0, 2, 3000, 1, 3301, 3300);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (34, 72000, 0, 0, false, '2018-12-08 08:00:00', 0, 0, 3000, 2, 3002, 3007);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (35, 72000, 0, 0, false, '2018-12-08 08:12:00', 0, 0, 1000, 2, 1007, 1003);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (36, 72000, 0, 0, false, '2018-12-08 08:24:00', 0, 0, 1000, 2, 1008, 1004);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (37, 72000, 0, 0, false, '2018-12-08 08:36:00', 0, 0, 2000, 2, 2008, 2007);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (38, 72000, 0, 0, false, '2018-12-08 08:48:00', 0, 0, 3000, 2, 3005, 3003);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (39, 72000, 0, 0, false, '2018-12-08 09:00:00', 0, 0, 2000, 2, 2011, 2009);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (40, 72000, 0, 0, false, '2018-12-08 09:12:00', 0, 0, 2000, 2, 2012, 2010);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (41, 72000, 0, 0, false, '2018-12-08 09:24:00', 0, 0, 1000, 2, 1001, 1006);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (42, 72000, 0, 0, false, '2018-12-08 09:36:00', 0, 0, 3000, 2, 3007, 3005);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (43, 72000, 0, 0, false, '2018-12-08 09:48:00', 0, 0, 1000, 2, 1002, 1007);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (44, 72000, 0, 0, false, '2018-12-08 10:00:00', 0, 0, 2000, 2, 2009, 2008);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (45, 72000, 0, 0, false, '2018-12-08 10:12:00', 0, 0, 2000, 2, 2001, 2011);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (46, 72000, 0, 0, false, '2018-12-08 10:24:00', 0, 0, 3000, 2, 3006, 3004);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (47, 72000, 0, 0, false, '2018-12-08 10:36:00', 0, 0, 2000, 2, 2007, 2012);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (48, 72000, 0, 0, false, '2018-12-08 10:48:00', 0, 0, 1000, 2, 1005, 1003);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (49, 72000, 0, 0, false, '2018-12-08 11:00:00', 0, 0, 1000, 2, 1006, 1004);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (50, 72000, 0, 0, false, '2018-12-08 11:12:00', 0, 0, 3000, 2, 3001, 3006);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (51, 72000, 0, 0, false, '2018-12-08 11:24:00', 0, 0, 2000, 2, 2005, 2001);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (52, 72000, 0, 0, false, '2018-12-08 11:36:00', 0, 0, 2000, 2, 2006, 2012);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (53, 72000, 0, 0, false, '2018-12-08 11:48:00', 0, 0, 2000, 2, 2004, 2003);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (54, 72000, 0, 0, false, '2018-12-08 12:00:00', 0, 0, 3000, 2, 3003, 3001);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (55, 72000, 0, 0, false, '2018-12-08 12:12:00', 0, 0, 1000, 2, 1001, 1008);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (56, 72000, 0, 0, false, '2018-12-08 12:24:00', 0, 0, 1000, 2, 1002, 1005);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (57, 72000, 0, 0, false, '2018-12-08 12:36:00', 0, 0, 2000, 2, 2011, 2004);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (58, 72000, 0, 0, false, '2018-12-08 12:48:00', 0, 0, 3000, 2, 3004, 3002);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (59, 72000, 0, 0, false, '2018-12-08 13:00:00', 0, 0, 2000, 2, 2003, 2006);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (60, 72000, 0, 0, false, '2018-12-08 13:12:00', 0, 0, 2000, 2, 2002, 2005);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (61, 72000, 0, 0, true, '2018-12-08 13:36:00', 0, 1, 1000, 2, 1103, 1102);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (62, 72000, 0, 0, true, '2018-12-08 13:48:00', 0, 1, 2000, 2, 2103, 2102);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (63, 72000, 0, 0, true, '2018-12-08 14:00:00', 0, 1, 3000, 2, 3103, 3102);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (64, 72000, 0, 0, true, '2018-12-08 14:24:00', 0, 2, 1000, 2, 1203, 1202);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (65, 72000, 0, 0, true, '2018-12-08 14:36:00', 0, 2, 2000, 2, 2203, 2202);
INSERT INTO public.games (id, duration, goals_team_guest, goals_team_home, flag_placeholder, start_date, state, type, fk_category, fk_hall, fk_team_guest, fk_team_home) VALUES (66, 72000, 0, 0, true, '2018-12-08 14:48:00', 0, 2, 3000, 2, 3303, 3302);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- PostgreSQL database dump complete
--