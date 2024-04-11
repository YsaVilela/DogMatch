import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:dog_match_mobile/src/modules/login/imports/login_imports.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

import '../../../../../../fixtures/fixtures.dart';
import '../../../../core/mock/token_valido_mock.dart';
import '../../mock/tutor_model_model.dart';

class RestClientMock extends Mock implements RestClient {}

main() {
  late LoginRemoteDatasource loginRemoteDatasource;
  late RestClientMock restClientMock;

  setUp(() {
    restClientMock = RestClientMock();
    loginRemoteDatasource = LoginRemoteDatasourceImpl(restClientMock);
  });

  test('Deve retornar um token válido quando a requisição for um sucesso',
      () async {
    when(
      () => restClientMock.post(
        url: any(named: ("url")),
        body: any(named: "body"),
        header: any(named: "header"),
      ),
    ).thenAnswer(
      (_) async => HttpResponse(
          data: fixture('token_response_json_mock.json'), statusCode: 200),
    );

    final result =
        await loginRemoteDatasource.cadastrarUsuario(tutorModelMock.toMap());

    expect(
      result,
      const Right<Exception, String>(
        tokenValidoMock,
      ),
    );
    verify(() => restClientMock.post(
          url: any(named: ("url")),
          body: any(named: "body"),
          header: any(named: "header"),
        )).called(1);
  });

  test(
      'Deve retornar uma "UserHttpStatusException" quando ocorrer um erro na requisição',
      () async {
    when(
      () => restClientMock.post(
        url: any(named: ("url")),
        body: any(named: "body"),
        header: any(named: "header"),
      ),
    ).thenAnswer(
      (_) async =>
          HttpResponse(data: fixture('error_response.json'), statusCode: 400),
    );

    final result =
        await loginRemoteDatasource.cadastrarUsuario(tutorModelMock.toMap());

    expect(
        result,
        Left<Exception, String>(UserHttpStatusException(
            response: HttpResponse(
          data: fixture('error_response.json'),
          statusCode: 400,
        ))));
    verify(() => restClientMock.post(
          url: any(named: ("url")),
          body: any(named: "body"),
          header: any(named: "header"),
        )).called(1);
  });
  test(
      'Deve retornar uma "ServerHttpStatusException" quando ocorrer um erro na requisição',
      () async {
    when(
      () => restClientMock.post(
        url: any(named: ("url")),
        body: any(named: "body"),
        header: any(named: "header"),
      ),
    ).thenAnswer(
      (_) async =>
          HttpResponse(data: fixture('error_response.json'), statusCode: 500),
    );

    final result =
        await loginRemoteDatasource.cadastrarUsuario(tutorModelMock.toMap());

    expect(
        result,
        Left<Exception, String>(ServerHttpStatusException(
            response: HttpResponse(
          data: fixture('error_response.json'),
          statusCode: 500,
        ))));
    verify(() => restClientMock.post(
          url: any(named: ("url")),
          body: any(named: "body"),
          header: any(named: "header"),
        )).called(1);
  });
}
