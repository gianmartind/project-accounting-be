create table if not exists store (
    uuid varchar(36) primary key,
    name varchar(32) not null,
    address varchar(255),
    notes varchar(255)
)