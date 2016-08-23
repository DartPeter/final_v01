create table projects(
id int not null auto_increment,
name varchar(50) not null,
primary key (id)
);

insert into projects(name) values("qwerty zxczvb asfdasf");

select max(id) from projects;

select * from projects order by id;

update projects set name = "aoe akgja jjjjzxc jlkrkl" where id = 7;

delete from projects where id = 1;



create table tasks(
id int not null auto_increment,
name varchar(50) not null,
status boolean not null,
project_id int not null,
primary key (id)
);

insert into tasks(name, status, project_id) values ("aad ffzxv ewf", true, 1);

delete from tasks where id = 1;

update tasks set name = "aoe akgja jjjjzxc jlkrkl" where id = 7;