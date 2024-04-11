import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:dog_match_mobile/src/modules/login/domain/repositories/login_repository_impl.dart';
import 'package:dog_match_mobile/src/modules/login/imports/login_imports.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

import '../../../../../../fixtures/fixtures.dart';
import '../../mock/tutor_entity_mock.dart';

class LoginDataSourceMock extends Mock implements LoginRemoteDatasourceImpl {}

void main() {
  late LoginRepository loginRepository;
  late LoginRemoteDatasource loginRemoteDataSource;

  setUp(() {
    loginRemoteDataSource = LoginDataSourceMock();
    loginRepository = LoginRepositoryImpl(loginRemoteDataSource);
    registerFallbackValue(tutorEntityMock);
  });

  test(
      "Deve retornar uma mensagem de sucesso quando cadastrar um usuário corretamente",
      () async {
    when(() => loginRemoteDataSource.cadastrarUsuario(any())).thenAnswer(
      (_) async =>
          const Right<Exception, String>('Tutor cadastrado com sucesso'),
    );

    final result = await loginRepository.cadastrarUsuario(tutorEntityMock);

    expect(
      result,
      const Right<Failure, String>('Tutor cadastrado com sucesso'),
    );
    verify(() => loginRemoteDataSource.cadastrarUsuario(any())).called(1);
  });

  test(
      "Deve retornar uma GenericFailure quando ocorrer algum erro ao cadastrar um usuário",
      () async {
    when(() => loginRemoteDataSource.cadastrarUsuario(any())).thenAnswer(
      (_) async => Left<Exception, String>(
        UserHttpStatusException(
          response: HttpResponse(
              data: jsonDecode(fixture('error_response.json')),
              statusCode: 400),
        ),
      ),
    );

    final result = await loginRepository.cadastrarUsuario(tutorEntityMock);

    expect(
      result,
      Left<Failure, String>(GenericFailure(
          errorMessage: "Ocorreu um erro ao realizar a requisição")),
    );
    verify(() => loginRemoteDataSource.cadastrarUsuario(any())).called(1);
  });
  test(
      "Deve retornar uma GenericFailure quando ocorrer algum erro ao cadastrar um usuário",
      () async {
    when(() => loginRemoteDataSource.cadastrarUsuario(any())).thenAnswer(
      (_) async => Left<Exception, String>(
        ServerHttpStatusException(
          response: HttpResponse(
              data: jsonDecode(fixture('error_response.json')),
              statusCode: 400),
        ),
      ),
    );

    final result = await loginRepository.cadastrarUsuario(tutorEntityMock);

    expect(
      result,
      Left<Failure, String>(GenericFailure(
          errorMessage: "Ocorreu um erro ao realizar a requisição")),
    );
    verify(() => loginRemoteDataSource.cadastrarUsuario(any())).called(1);
  });

  test(
      "Deve retornar uma GenericFailure quando o usuario não possuir conexao com a internet",
      () async {
    when(() => loginRemoteDataSource.cadastrarUsuario(any())).thenAnswer(
      (_) async => const Left<Exception, String>(
        SocketException("Sem conexão com a internet!"),
      ),
    );

    final result = await loginRepository.cadastrarUsuario(tutorEntityMock);

    expect(
      result,
      Left<Failure, String>(GenericFailure(
          errorMessage: "Sem conexão com a internet no momento.")),
    );
    verify(() => loginRemoteDataSource.cadastrarUsuario(any())).called(1);
  });

  test(
      "Deve retornar uma GenericFailure com uma mensagem de tempo limite atingido quando a requisição do usuário demorar",
      () async {
    when(() => loginRemoteDataSource.cadastrarUsuario(any())).thenAnswer(
      (_) async => Left<Exception, String>(
        TimeoutException('Tempo limite expirado.'),
      ),
    );

    final result = await loginRepository.cadastrarUsuario(tutorEntityMock);

    expect(
        result,
        Left<Failure, String>(GenericFailure(
          errorMessage: 'Tempo limite expirado.',
        )));

    verify(() => loginRemoteDataSource.cadastrarUsuario(any())).called(1);
  });
}
