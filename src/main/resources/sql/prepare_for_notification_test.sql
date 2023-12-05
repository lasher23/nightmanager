update teams set phone_number = null;

with team_update_nici_cat_1000 as (
    select  * from teams where fk_category = 1000 and phone_number is null limit 1
)

update teams set phone_number = '+41799271947' from team_update_nici_cat_1000 where teams.id = team_update_nici_cat_1000.id;

with team_update_nici_cat_5000 as (
    select  * from teams where fk_category = 5000 and phone_number is null limit 1
)

update teams set phone_number = '+41799271947' from team_update_nici_cat_5000 where teams.id = team_update_nici_cat_5000.id;

with team_update_nici_cat_8000 as (
    select  * from teams where fk_category = 8000 and phone_number is null limit 1
)

update teams set phone_number = '+41799271947' from team_update_nici_cat_8000 where teams.id = team_update_nici_cat_8000.id