 import 'package:dog_match_mobile/src/modules/login/domain/entities/endereco_entity.dart';

class EnderecoModel extends EnderecoEntity {
  const EnderecoModel({
    required String cep,
    required String logradouro,
    required String numero,
    required String complemento,
    required String bairro,
    required String cidade,
    required String uf,
  }) : super(
          cep: cep,
          logradouro: logradouro,
          numero: numero,
          complemento: complemento,
          bairro: bairro,
          cidade: cidade,
          uf: uf,
        );

  factory EnderecoModel.fromEntity(EnderecoEntity model) {
    return EnderecoModel(
      cep: model.cep,
      logradouro: model.logradouro,
      numero: model.numero,
      complemento: model.complemento,
      bairro: model.bairro,
      cidade: model.cidade,
      uf: model.uf,
    );
  }
}
