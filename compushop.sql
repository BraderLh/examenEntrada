create table if not exists usuarios(
	usuario_id serial primary key,
	username varchar(100)
);

create table if not exists articulos (
	articulo_id serial primary key,
	nombreart varchar(100),
	marca varchar(100),
	versionart integer,
	modelo varchar(100),
	usuario_id integer,
	isLoaned boolean,
	status boolean,
	FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
);

create table if not exists hardware (
	hardware_id serial PRIMARY KEY,
	articulo_id integer,
	material varchar(100),
	FOREIGN KEY (articulo_id) REFERENCES articulos(articulo_id)
);

create table if not exists software(
	software_id  serial PRIMARY key,
	tipoLic varchar(100),
	articulo_id integer,
	FOREIGN KEY (articulo_id) REFERENCES articulos(articulo_id)
);