import 'package:dog_match_mobile/src/core/constants/chaves_conversao.dart';
import 'package:dog_match_mobile/src/modules/login/data/models/endereco_model.dart';
import 'package:dog_match_mobile/src/modules/login/domain/entities/tutor_entity.dart';

class TutorModel extends TutorEntity {
  const TutorModel({
    required String nome,
    required String sobrenome,
    required String dataDeNascimento,
    required String genero,
    required String cpf,
    required String telefone,
    required String email,
    required String senha,
    required EnderecoModel endereco,
  }) : super(
          nome: nome,
          sobrenome: sobrenome,
          dataDeNascimento: dataDeNascimento,
          genero: genero,
          cpf: cpf,
          telefone: telefone,
          email: email,
          senha: senha,
          endereco: endereco,
        );

  factory TutorModel.fromEntity(TutorEntity entity) {
    return TutorModel(
      nome: entity.nome,
      sobrenome: entity.sobrenome,
      dataDeNascimento: entity.dataDeNascimento,
      genero: entity.genero,
      cpf: entity.cpf,
      telefone: entity.telefone,
      email: entity.email,
      senha: entity.senha,
      endereco: EnderecoModel.fromEntity(entity.endereco),
    );
  }

  Map<String, dynamic> toMap() {
    return {
      TutorConstante.nome: nome,
      TutorConstante.sobrenome: sobrenome,
      TutorConstante.dataDeNascimento: dataDeNascimento,
      TutorConstante.genero: genero,
      TutorConstante.cpf: cpf,
      TutorConstante.telefone: telefone,
      TutorConstante.email: email,
      TutorConstante.endereco: endereco.toMap(),
      TutorConstante.senha: senha,
    };
  }
}
