SET @x = '2021-05-10';
SET @y = '2021-05-15';

SELECT v.* from vehicles v
left join
(
SELECT distinct v.* FROM reservations r
JOIN vehicles v
ON r.car_licPlate = v.licPlate
WHERE (@x >= r.start_date AND @x <= r.end_date) OR (@y >= r.start_date AND @y <= r.end_date)
) reservedcars
on v.licPlate = reservedcars.licPlate
WHERE reservedcars.licPlate IS NULL;