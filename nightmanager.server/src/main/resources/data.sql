copy halls(id,name)
From './Hall.csv' DELIMITER ',' CSV HEADER;

copy categories(id,name,state,type)
From './Kategorie.csv' DELIMITER ',' CSV HEADER;

copy teams(id,name,flag_placeholder,fk_category,rank)
From './Team.csv' DELIMITER ',' CSV HEADER;;

copy games(ID,fk_team_home,fk_team_guest,goals_team_home,goals_team_guest,state,fk_hall,fk_category,start_date,flag_placeholder,type,duration)
From './Match.csv' DELIMITER ',' CSV HEADER;;