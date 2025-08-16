create table if not exists project (
	uuid varchar(36) primary key,
	name varchar(32) not null,
	address varchar(255) not null,
	start_date date not null,
	end_date date,
	notes varchar(255)
);