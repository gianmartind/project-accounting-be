create or replace view list_types as
select distinct type from purchase_item;

create or replace view list_units as
select distinct unit from purchase_item;

create or replace view list_brands as
select distinct brand from purchase_item;

create or replace view list_categories as
select distinct category from purchase_item;

