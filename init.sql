drop table holiday;

drop table employee;

create table employee (
  id serial primary key,
  lname varchar(50),
  fname varchar(50),
  email varchar(50),
  phone varchar(50) unique,
  salary float,
  post varchar(50),
  role varchar(50),
  solde integer default 25
);

create table holiday (
  id serial primary key,
  eid integer references employee (id) on delete cascade on update cascade,
  startdate date,
  enddate date,
  type varchar(50)
);
