alter table purchase_item add if not exists brand varchar(32) not null default '';
alter table purchase_item add if not exists category varchar(32) not null default '';