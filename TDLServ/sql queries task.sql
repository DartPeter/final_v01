-- q1
select distinct(status) from tasks;

-- q2
select project_id, count(*) from tasks
group by project_id order by count(*) desc;

-- q3
select projects.name, count(*) from tasks, projects 
where projects.id = tasks.project_id 
group by projects.name order by projects.name;

-- q4
select tasks.name from tasks, projects 
where projects.id = tasks.project_id and projects.name like "N%";

-- q5
select projects.name, count(*) from tasks, projects 
where projects.id = tasks.project_id and projects.name like "%a%"
group by projects.name order by projects.name;

-- q6
select name from tasks group by name having count(*) > 1;

-- q7
select tasks.name from tasks, projects 
where projects.id = tasks.project_id and projects.name = "Garage" 
group by tasks.name, status
having count(*) > 1;


-- q8
select projects.name from projects, tasks 
where projects.id = tasks.project_id and status = true 
group by status
having count(*) > 10;


