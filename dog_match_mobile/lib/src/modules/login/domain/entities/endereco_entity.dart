import 'package:dog_match_mobile/src/core/constants/chaves_conversao.dart';
import 'package:equatable/equatable.dart';

class EnderecoEntity extends Equatable {
  final String cep;
  final String logradouro;
  final String numero;
  final String complemento;
  final String bairro;
  final String cidade;
  final String uf;

  const EnderecoEntity({
    required this.cep,
    required this.logradouro,
    required this.numero,
    required this.complemento,
    required this.bairro,
    required this.cidade,
    required this.uf,
  });

  Map<String, dynamic> toMap() {
    return {
      EnderecoConstante.cep: cep,
      EnderecoConstante.logradouro: logradouro,
      EnderecoConstante.numero: numero,
      EnderecoConstante.complemento: complemento,
      EnderecoConstante.bairro: bairro,
      EnderecoConstante.cidade: cidade,
      EnderecoConstante.uf: uf,
    };
  }

  @override
  List<Object?> get props => [
        cep,
        logradouro,
        numero,
        complemento,
        bairro,
        cidade,
        uf,
      ];
}
