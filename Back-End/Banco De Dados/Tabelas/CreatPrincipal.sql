create table TB_ENDERECO(
	ID serial primary key,
	CEP varchar (9) not null,
	LOGRADOURO varchar (255) not null,
	NUMERO varchar (20) not null,
	COMPLEMENTO varchar (100),
	BAIRRO varchar(100) not null,
	CIDADE varchar(100) not null,
	UF char (2) not null
);

create table TB_TUTOR(
	ID serial primary key,
	NOME varchar (100) not null,	
	SOBRENOME varchar (255) not null,
	DATA_NASCIMENTO varchar (10) not null,
	GENERO varchar (10) not null,
	CPF varchar (14) not null unique,
	EMAIL varchar (255) not null unique,
	TELEFONE varchar (14) not null,	
	FK_ENDERECO integer not null unique,
		foreign key (FK_ENDERECO)
		references TB_ENDERECO(ID),
	STATUS boolean not null
);

create table TB_PET(
	ID serial primary key,
	NOME_USUARIO varchar (100) not null unique,	
	NOME varchar (100) not null,	
	SOBRENOME varchar (255) not null,
	DATA_NASCIMENTO varchar (10) not null,
	GENERO varchar (10) not null,
	RACA varchar (255) not null,
	COR varchar (255) not null,
	PORTE varchar (20) not null,
	CARTEIRA_VACINACAO text not null,
	FOTO_PERFIL text not null,
	DESCRICAO text not null,
	INTERESSE varchar (20) not null,
	CASTRADO boolean not null,
	FK_TUTOR integer not null,
		foreign key (FK_TUTOR)
		references TB_TUTOR(ID),
	STATUS boolean not null
);

create table TB_PEDIGREE(
	ID serial primary key,
	RG varchar (12) not null,	
	DATA_EMISSAO varchar (10) not null,
	DOCUMENTO text not null,
	FK_PET integer not null unique,
		foreign key (FK_PET)
		references TB_PET(ID)
);

create table TB_FOTO(
	ID serial primary key,
	FOTO text not null,
	LEGENDA text,
	DATA_PUBLICACAO timestamp not null,
	FK_PET integer not null,
		foreign key (FK_PET)
		references TB_PET(ID)
);

create table TB_SOLICITACAO(
	ID serial primary key,
	STATUS varchar (25) not null, 
	FK_SOLICITANTE integer not null,
		foreign key (FK_SOLICITANTE)
		references TB_PET(ID),
	FK_SOLICITADO integer not null,
		foreign key (FK_SOLICITADO)
		references TB_PET(ID),
	DATA_SOLICITACAO timestamp not null
);

create table TB_MATCH(
	ID serial primary key,
	FK_PET1 integer not null,
		foreign key (FK_PET1)
		references TB_PET(ID),
	FK_PET2 integer not null,
		foreign key (FK_PET2)
		references TB_PET(ID)
);

create table TB_MENSAGEM(
	ID serial primary key,
	CONTEUDO text not null,
	DATA_ENVIO date not null,
	STATUS varchar (30) not null,
	FK_MATCH integer not null,
		foreign key (FK_MATCH)
		references TB_MATCH(ID),
	FK_PET integer not null,
		foreign key (FK_PET)
		references TB_PET(ID)
);

create table TB_USUARIO(
	ID serial primary key,
	LOGIN varchar (255) not null unique,	
	SENHA text not null,
	FK_TUTOR integer not null unique,
		foreign key (FK_TUTOR)
		references TB_TUTOR(ID)
);

create table TB_RECUSA(
	ID serial primary key,
	FK_PET_RECUSA integer not null,
		foreign key (FK_PET_RECUSA)
		references TB_PET(ID),
	FK_PET_RECUSADO integer not null,
		foreign key (FK_PET_RECUSADO)
		references TB_PET(ID)
);

create table TB_RECUSA_PERMANENTE(
	ID serial primary key,
	FK_PET_RECUSA integer not null,
		foreign key (FK_PET_RECUSA)
		references TB_PET(ID),
	FK_PET_RECUSADO integer not null,
		foreign key (FK_PET_RECUSADO)
		references TB_PET(ID)
);

drop table TB_RECUSA_PERMANENTE,TB_RECUSA,TB_MENSAGEM, TB_MATCH, TB_SOLICITACAO,TB_FOTO,TB_PEDIGREE,TB_PET, TB_TUTOR, TB_ENDERECO, TB_USUARIO;


