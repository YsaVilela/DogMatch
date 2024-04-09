import 'package:dog_match_mobile/src/modules/login/domain/entities/endereco_entity.dart';
import 'package:dog_match_mobile/src/modules/login/domain/entities/tutor_entity.dart';

TutorEntity tutorEntity = const TutorEntity(
  nome: "Ysadora",
  sobrenome: "Vilela",
  dataDeNascimento: "04/11/2004",
  genero: "Feminino",
  cpf: "146.471.315-48",
  telefone: "(11)97721-1298",
  email: "emailvalido@hotmail.com.br",
  senha: "A12345679*",
  endereco: EnderecoEntity(
    cep: "05727-100",
    logradouro: "Rua Banofe",
    numero: "168",
    complemento: "",
    bairro: "Jardim Santa Bananinha",
    cidade: 'São Paulo',
    uf: "SP",
  ),
);
