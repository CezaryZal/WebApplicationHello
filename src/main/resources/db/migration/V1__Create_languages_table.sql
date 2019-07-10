create table languages (
    id bigint unsigned primary key auto_increment,
    welcomeMsg varchar(100) not null,
    code varchar (3) not null
);