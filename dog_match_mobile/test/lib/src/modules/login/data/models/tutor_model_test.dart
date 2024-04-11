import 'package:dog_match_mobile/src/modules/login/imports/login_imports.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../../../../../fixtures/fixtures.dart';
import '../../mock/tutor_entity_mock.dart';
import '../../mock/tutor_model_model.dart';

void main() {
  test('Verifica se o retorno do toMap esta de acordo com a entidade', () {
    final result = tutorModelMock.toMap();

    expect(result, jsonDecode(fixture('cadastro_json_mock.json')));
  });

  test('Verifica se o retorno do fromEntity se esta de acordo com a model', () {
    final result = TutorModel.fromEntity(tutorEntityMock);

    expect(result, tutorModelMock);
  });
}
