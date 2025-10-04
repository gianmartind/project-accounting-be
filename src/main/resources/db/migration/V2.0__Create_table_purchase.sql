create table if not exists purchase (
	uuid varchar(36) primary key,
	store_uuid varchar(36) not null,
	project_uuid varchar(36) not null,
	purchase_date date not null
);

create table if not exists purchase_item (
	uuid varchar(36) primary key,
	name varchar(32) not null,
	type varchar(32) not null,
	amount numeric not null,
	unit varchar(32),
	price numeric not null
);