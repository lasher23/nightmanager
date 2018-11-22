--copy halls(id,name)
--From 'C:\projects\nightmanager\nightmanager.server\src\main\resources\Hall.csv' DELIMITER ',' CSV HEADER;

--copy categories(id,name,state,type)
--From 'C:\projects\nightmanager\nightmanager.server\src\main\resources\Kategorie.csv' DELIMITER ',' CSV HEADER;

--copy teams(id,name,flag_placeholder,fk_category,rank)
--From 'C:\projects\nightmanager\nightmanager.server\src\main\resources\Team.csv' DELIMITER ',' CSV HEADER;;

--copy games(ID,fk_team_home,fk_team_guest,goals_team_home,goals_team_guest,state,fk_hall,fk_category,start_date,flag_placeholder,type,duration)
--From 'C:\projects\nightmanager\nightmanager.server\src\main\resources\Match.csv' DELIMITER ',' CSV HEADER;;