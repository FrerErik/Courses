select count(Lended_id) as Tomorrows_deliveries, Time
from lended_person
where time = "3/18/2021"
group by time
