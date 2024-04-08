create table TB_ESTADO(
	ID primary key,
	NOME varchar (25) not null,
	UF char (2) not null,
);


create table TB_CIDADE(
	ID serial primary key,
	FK_ESTADO integer,
		foreign key (FK_ESTADO)
		references TB_ESTADO(ID),
	NOME varchar (255) not null	
);