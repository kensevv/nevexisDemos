-- Vsichki gradove ot koito moga da se kacha:

SELECT distinct c.name 
FROM transition tran, city c, 
(
	SELECT trip_id, MAX(order_column) max_order FROM transition
	group by(trip_id)
) AS T1
WHERE tran.city_id = c.id AND tran.trip_id = T1.trip_id
AND tran.order_column < T1.max_order;

-- If i get on x where can i go

SET @x = 'Sofia';
select distinct c.name from transition tran, city c, trip t,
(
SELECT trip_id, t.order_column from transition t, city c
WHERE t.city_id = c.id
AND c.name = @x
) AS T1
WHERE tran.city_id = c.id AND tran.trip_id = t.id AND tran.trip_id = T1.trip_id
AND c.name != @x
AND tran.order_column > T1.order_column;

-- numbers from 0-100
SELECT distinct table1.val + table2.val*10 AS num
FROM
(SELECT 0 AS val union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) table1,
(SELECT 0 AS val union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) table2
order by num;

-- x to y dates?

SET @x = 'Sofia';
SET @y = 'Burgas';

SELECT result.trip_id, w.day_name, date_add(result.first_date, INTERVAL numbers.num WEEK) date, result.hour_time AS Poteglq, result.pristiga FROM week_days w,
(
SELECT T2.hour_time AS pristiga, tran.*, c.name, date(date_add(NOW(), INTERVAL (weekday_id - DAYOFWEEK(NOW())+1) DAY)) first_date FROM transition tran, city c,
(SELECT trip_id, t.order_column, c.name from transition t, city c WHERE t.city_id = c.id AND c.name = @x) AS T1,
(SELECT trip_id, t.order_column, hour_time from transition t, city c WHERE t.city_id = c.id AND c.name = @y) AS T2
WHERE tran.trip_id = T1.trip_id = tran.trip_id AND tran.city_id = c.id AND c.name = @x
) result,
(
SELECT distinct table1.val + table2.val*10 AS num FROM
(SELECT 0 AS val union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) table1,
(SELECT 0 AS val union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) table2
order by num
) AS numbers
WHERE w.id = result.weekday_id;