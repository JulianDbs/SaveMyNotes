
create table users(
	id serial not null,
	username varchar(21) not null,
	email varchar(40) unique not null,
	password varchar(100) not null
);

create table user_info(
	id serial not null,
	email varchar(40) not null unique,
	creation_date DATE DEFAULT CURRENT_DATE not null,
	note_count NUMERIC not null,
	account_expired BOOLEAN DEFAULT FALSE not null,
	account_locked BOOLEAN DEFAULT FALSE not null,
	creadentials_expired BOOLEAN DEFAULT FALSE not null,
	account_enabled BOOLEAN DEFAULT TRUE not null,
	constraint fk_email
		foreign key (email)
			references users(email)
);

create table notes(
	id serial not null,
	email varchar(40) not null,
	creation_date DATE DEFAULT CURRENT_DATE not null,
	last_access TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP not null,
	code varchar(200) not null unique,
	title varchar(30) not null,
	content text[] not null,
	constraint fk_email
		foreign key (email)
			references user_info(email)
);
