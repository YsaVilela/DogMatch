import 'package:dog_match_mobile/src/modules/login/data/models/endereco_model.dart';
import 'package:dog_match_mobile/src/modules/login/imports/login_imports.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../../../../../fixtures/fixtures.dart';
import '../../mock/endereco_entity_mock.dart';
import '../../mock/endereco_model_mock.dart';

void main() {
  test('Verifica se o retorno do toMap esta de acordo com a entidade', () {
    final result = enderecoModelMock.toMap();

    expect(result, jsonDecode(fixture('endereco_json_mock.json')));
  });

  test('Verifica se o retorno do fromEntity se esta de acordo com a model', () {
    final result = EnderecoModel.fromEntity(enderecoEntityMock);

    expect(result, enderecoModelMock);
  });
}
