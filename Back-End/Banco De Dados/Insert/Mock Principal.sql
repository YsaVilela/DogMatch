insert into TB_ENDERECO (CEP, LOGRADOURO, NUMERO, BAIRRO, CIDADE, UF )
values
('63020-605','Rua Expedito Ramos da Silva','2','Aeroporto','Juazeiro do Norte','CE'),
('89252-500','Rua Francisco Piermann','863','Vila Lenzi','Jaraguá do Sul','SC'),
('69073-524','Avenida Rodrigo Otávio','840','São Lázaro','Manaus','AM'),
('41510-175','Rua Tiradentes','638','São Cristóvão','Salvador','BA'),
('68906-814','Avenida Norte Sul','171','Cabralzinho','Macapá','AP'),
('65031-510','Rua Domingos Celso Carneiro','767','Cidade Nova','São Luís','MA'),
('58401-722','Rua Doutor Vasconcelos','419','Alto Branco','Campina Grande','PB'),
('58085-100','Rua Dois','580','Cruz das Armas','João Pessoa','PB'),
('58706-350','Rua Francisca Maria da Luz','519','São Sebastião','Patos','PB'),
('58015-815','Travessa Tenente Pedro Fernandes Filho','275','Jaguaribe','João Pessoa','PB'),
('58310-000','Rua José Marcelino de Souza','390','Jardim Manguinhos','Cabedelo','PB')

insert into TB_TUTOR (NOME,SOBRENOME,DATA_NASCIMENTO,GENERO,CPF,EMAIL,TELEFONE,FK_ENDERECO,STATUS)
values
('Guilherme ','Muller','01/01/2002','Masculino','803.356.660-43','g@hotmail.com','(69) 3721-3128',1,TRUE),
('Maria','Costa','12/08/1982','Feminino','675.833.270-59','m@hotmail.com','(31) 3884-6343',2,TRUE),
('José','Vilela','14/03/1999','Masculino','530.533.850-66','j@hotmail.com','(63) 3580-7815',3,TRUE),
('Angela','Silva','01/04/2000','Feminino','146.721.430-23','a@hotmail.com','(71) 3086-5526',4,TRUE),
('Ysadora','Santos','31/01/1974','Feminino','492.521.750-08','y@hotmail.com','(28) 3982-1717',5,TRUE),
('Bruno','Fernandes','26/11/1997','Masculino','663.906.980-62','b@hotmail.com','(51) 3484-0353',6,TRUE),
('Carol','Oliveira','23/11/1976','Feminino','264.921.330-21','c@hotmail.com','(21) 2308-7863',7,TRUE),
('Luis','Moreira','14/05/1970','Masculino','580.854.700-80','l@hotmail.com','(95) 3365-3116',8,TRUE),
('Renata','Medeiros','10/09/1961','Feminino','590.765.950-54','r@hotmail.com','(84) 2415-4367',9,TRUE),
('Daniela','Nunes','23/04/1964','Feminino','784.952.190-00','d@hotmail.com','(48) 3834-9852',10,TRUE),
('Eduardo','Carvalho','05/08/1981','Masculino','104.092.940-04','e@hotmail.com','(84) 3608-3802',11,TRUE)

insert into TB_PET (NOME_USUARIO, NOME, SOBRENOME, DATA_NASCIMENTO, GENERO, RACA, COR, PORTE, CARTEIRA_VACINACAO, FOTO_PERFIL, DESCRICAO, INTERESSE, CASTRADO, FK_TUTOR, STATUS)
values
('Zac','Zac','Muller','09/05/2013','Macho','Pastor Alemão','Marrom','Grande','Carteira ','Perfil','Fofo','Amizade',TRUE,4,TRUE),
('Renèe','Renèe','Costa','11/08/2010','Femea','Maltês','Branco','Pequeno','Carteira ','Perfil','Fofo','Amizade',FALSE,2,TRUE),
('Dayton','Dayton','Vilela','29/09/2017','Macho','Lhasa','Branco e Preto','Pequeno','Carteira ','Perfil','Fofo','Relacionamento',FALSE,5,TRUE),
('Lee','Lee','Silva','07/04/2015','Femea','Shih-Tzu','Branco e Marrom','Pequeno','Carteira ','Perfil','Fofo','Relacionamento',TRUE,10,TRUE),
('Jordan','Jordan','Santos','04/08/2021','Macho','Maltês','Branco','Pequeno','Carteira ','Perfil','Fofo','Amizade',TRUE,11,TRUE),
('Percy','Percy','Fernandes','21/09/2007','Femea','Poodle','Branco','Pequeno','Carteira ','Perfil','Fofo','Amizade',FALSE,1,TRUE),
('Aydin','Aydin','Oliveira','22/11/2014','Macho','Yorkshire','Marrom','Pequeno','Carteira ','Perfil','Fofo','Relacionamento',FALSE,11,TRUE),
('Zaire','Zaire','Moreira','12/01/2021','Femea','Spitz Alemão','Branco','Pequeno','Carteira ','Perfil','Fofo','Relacionamento',TRUE,7,TRUE),
('Jodie','Jodie','Medeiros','18/12/2019','Macho','Vira-lata','Branco e Preto','Medio','Carteira ','Perfil','Fofo','Amizade',TRUE,7,TRUE),
('Maxime','Maxime','Nunes','17/03/2009','Femea','Labrador','Branco e Marrom','Grande','Carteira ','Perfil','Fofo','Amizade',FALSE,6,TRUE),
('Cream Puff','Cream Puff','Carvalho','09/10/2011','Macho','Dobermann','Branco','Grande','Carteira ','Perfil','Fofo','Relacionamento',FALSE,7,TRUE),
('Wynter','Wynter','Muller','12/04/2012','Femea','Golden Retriever','Branco','Grande','Carteira ','Perfil','Fofo','Relacionamento',TRUE,5,TRUE),
('Noe','Noe','Costa','08/05/2006','Macho','Poodle','Marrom','Medio','Carteira ','Perfil','Fofo','Amizade',TRUE,7,TRUE),
('Peyton','Peyton','Vilela','19/12/2008','Femea','Shih-Tzu','Branco','Pequeno','Carteira ','Perfil','Fofo','Amizade',FALSE,11,TRUE),
('Cameron','Cameron','Silva','10/05/2017','Macho','Golden Retriever','Branco e Preto','Grande','Carteira ','Perfil','Fofo','Relacionamento',FALSE,1,TRUE),
('Jaime','Jaime','Santos','26/11/2014','Femea','Shih-Tzu','Branco e Marrom','Medio','Carteira ','Perfil','Fofo','Relacionamento',TRUE,7,TRUE),
('Tristen','Tristen','Fernandes','18/04/2006','Macho','Dobermann','Branco','Grande','Carteira ','Perfil','Fofo','Amizade',TRUE,6,TRUE),
('Keagan','Keagan','Oliveira','01/02/2012','Femea','Poodle','Branco','Pequeno','Carteira ','Perfil','Fofo','Amizade',FALSE,8,TRUE),
('Taylor','Taylor','Moreira','15/05/2008','Macho','Yorkshire','Marrom','Pequeno','Carteira ','Perfil','Fofo','Relacionamento',FALSE,10,TRUE),
('Coffee','Coffee','Medeiros','28/09/2018','Femea','Golden Retriever','Branco','Grande','Carteira ','Perfil','Fofo','Relacionamento',TRUE,2,TRUE),
('Lennon','Lennon','Nunes','20/11/2011','Macho','Yorkshire','Branco e Preto','Pequeno','Carteira ','Perfil','Fofo','Amizade',TRUE,6,TRUE),
('Cody','Cody','Carvalho','02/02/2020','Femea','Dobermann','Branco e Marrom','Grande','Carteira ','Perfil','Fofo','Amizade',FALSE,3,TRUE),
('Kahlúa','Kahlúa','Muller','04/01/2016','Macho','Shih-Tzu','Branco','Pequeno','Carteira ','Perfil','Fofo','Relacionamento',FALSE,6,TRUE),
('Ash','Ash','Costa','21/10/2012','Femea','Pastor Alemão','Branco','Grande','Carteira ','Perfil','Fofo','Relacionamento',TRUE,5,TRUE),
('Berkeley','Berkeley','Vilela','15/04/2013','Macho','Poodle','Branco','Pequeno','Carteira ','Perfil','Fofo','Amizade',TRUE,5,TRUE),
('Tiger','Tiger','Silva','17/10/2022','Femea','Vira-lata','Caramelo','Medio','Carteira ','Perfil','Fofo','Amizade',FALSE,7,TRUE),
('Haven','Haven','Santos','02/11/2016','Macho','Maltês','Branco','Pequeno','Carteira ','Perfil','Fofo','Relacionamento',FALSE,4,TRUE)

insert into TB_PEDIGREE (RG, DATA_EMISSAO, DOCUMENTO, FK_PET)
values
('1','08/05/2006','Link documento',8),
('2','19/12/2008','Link documento',22),
('3','10/05/2017','Link documento',12),
('4','26/11/2014','Link documento',24),
('5','18/04/2006','Link documento',18),
('6','01/02/2012','Link documento',13),
('7','15/05/2008','Link documento',10),
('8','28/09/2018','Link documento',4),
('9','17/10/2022','Link documento',27)

insert into TB_USUARIO (LOGIN, SENHA, FK_TUTOR)
values
('g@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',1),
('m@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',2),
('j@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',3),
('a@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',4),
('y@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',5),
('b@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',6),
('c@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',7),
('l@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',8),
('r@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',9),
('d@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',10),
('e@hotmail.com','$2a$12$FuVBkHx.Ht8f0pXfeiewpuXpmNe6GXyHTZYR6DCyPFNfwcPnJudeq',11)