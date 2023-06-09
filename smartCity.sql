set serveroutput on;
select * from parking;
drop table parking;
CREATE TABLE parking (
  id NUMBER PRIMARY KEY,
  name VARCHAR2(100),
  capacity NUMBER,
  location_lat  number,
  location_long number,
  price_per_hour NUMBER
);
commit;
INSERT INTO parking (id, name, capacity, location_lat ,location_long, price_per_hour)
VALUES (1, 'Parcare Palas', 200, 47.1576079482375, 27.58894851517858, 7.5);

INSERT INTO parking (id, name, capacity,  location_lat ,location_long,price_per_hour)
VALUES (2, 'Parcare Ateneu', 300, 47.16546097595563, 27.608096498828985, 10.0);

INSERT INTO parking (id, name, capacity, location_lat ,location_long, price_per_hour)
VALUES (3, 'Parcare Capat Dacia', 200, 47.168951297345984, 27.54191205880036, 7.5);

INSERT INTO parking (id, name, capacity, location_lat ,location_long, price_per_hour)
VALUES (4, 'Moldova Mall Parking', 300, 47.159931649412805, 27.587182603210444, 8.0);

update parking set capacity=40 where id=2;