import 'package:dog_match_mobile/src/modules/login/domain/entities/endereco_entity.dart';
import 'package:equatable/equatable.dart';

class TutorEntity extends Equatable {
  final String nome;
  final String sobrenome;
  final String dataDeNascimento;
  final String genero;
  final String cpf;
  final String telefone;
  final String email;
  final String senha;
  final EnderecoEntity endereco;

  const TutorEntity({
    required this.nome,
    required this.sobrenome,
    required this.dataDeNascimento,
    required this.genero,
    required this.cpf,
    required this.telefone,
    required this.email,
    required this.senha,
    required this.endereco,
  });

  @override
  List<Object?> get props => [
        nome,
        sobrenome,
        dataDeNascimento,
        genero,
        cpf,
        telefone,
        email,
        senha,
        endereco,
      ];
}
