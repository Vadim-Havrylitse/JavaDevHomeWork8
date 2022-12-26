create table manufacturers (
    id varchar(255) not null,
    name varchar(255),
    primary key (id)
)
engine=InnoDB;

create table products (
    id varchar(255) not null,
    name varchar(255), price integer,
    manufacturers_id varchar(255),
    primary key (id)
)
engine=InnoDB;

create table user_table (
    id varchar(255) not null,
    username varchar(255) not null,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    role varchar(255),
    primary key (id)
)
engine=InnoDB;

alter table user_table add constraint UK_en3wad7p8qfu8pcmh62gvef6v unique (username);