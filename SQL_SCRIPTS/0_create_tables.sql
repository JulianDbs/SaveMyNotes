
create table users(
	id serial not null,
	username varchar(21) not null,
	email varchar(40) unique not null,
	password varchar(100) not null
);
