import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:dog_match_mobile/src/modules/login/domain/repositories/login_repository_impl.dart';
import 'package:dog_match_mobile/src/modules/login/imports/login_imports.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

import '../../mock/tutor_entity_mock.dart';

class LoginRemoteDatasourceMock extends Mock
    implements LoginRemoteDatasourceImpl {}

main() {
  late LoginRemoteDatasource loginRemoteDatasource;
  late LoginRepository loginRepository;
  setUp(() {
    loginRemoteDatasource = LoginRemoteDatasourceMock();
    loginRepository = LoginRepositoryImpl(loginRemoteDatasource);
  });

  test('Deve retornar uma mensagem de sucesso quando enviado os dados válidos',
      () async {
    when(() => loginRemoteDatasource.cadastrarUsuario(any())).thenAnswer(
      (_) async => const Right('Tutor cadastrado com sucesso'),
    );

    final result = await loginRepository.cadastrarUsuario(tutorEntity);

    expect(
      result,
      const Right<Failure, String>('Tutor cadastrado com sucesso'),
    );
    verify(() => loginRemoteDatasource.cadastrarUsuario(any())).called(1);
  });

  test('Deve retornar uma mensagem de erro quando enviado os dados inválidos',
      () async {
    when(() => loginRemoteDatasource.cadastrarUsuario(any())).thenAnswer(
      (_) async => const Left<Exception, String>(
        UserHttpStatusException(
          response: HttpResponse(
            data: """
            {
                "campo": "endereco.uf",
                "mensagem": "UF é obrigatório"
            }
          """,
            statusCode: 400,
          ),
        ),
      ),
    );

    final result = await loginRepository.cadastrarUsuario(tutorEntity);

    expect(
      result,
      Left<Failure, String>(GenericFailure(errorMessage: "UF é obrigatório")),
    );
    verify(() => loginRemoteDatasource.cadastrarUsuario(any())).called(1);
  });
}
