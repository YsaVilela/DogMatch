import 'package:dog_match_mobile/src/core/http_client/http_client_impl.dart';
import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

import '../../../../fixtures/fixtures.dart';

class HttpClientImplMock extends Mock implements HttpClientImp {}

void main() {
  late RestClient restClient;
  setUp(() {
    restClient = HttpClientImplMock();
  });

  test(
    "validar retorno http client método post em caso de sucesso",
    () async {
      when(
        () => restClient.post(
          url: any(named: 'url'),
          header: any(named: 'header'),
          body: any(named: 'body'),
        ),
      ).thenAnswer(
        (_) async => HttpResponse(
            data: fixture('token_response_json_mock.json'), statusCode: 200),
      );

      final result = await restClient.post(
        url: 'www.google.com',
        header: {"token": "kaismck29am2"},
        body: {"teste": "teste"},
      );

      expect(result, isA<HttpResponse>());
      expect(result.statusCode, 200);
      expect(result.data, fixture('token_response_json_mock.json'));
      verify(() => restClient.post(
          url: any(named: 'url'),
          header: any(named: 'header'),
          body: any(named: 'body'))).called(1);
    },
  );
  test(
    "validar retorno http client método post",
    () async {
      when(
        () => restClient.post(
            url: any(named: 'url'),
            header: any(named: 'header'),
            body: any(named: 'body')),
      ).thenAnswer(
        (_) async =>
            HttpResponse(data: fixture('error_response.json'), statusCode: 400),
      );

      final result = await restClient.post(
        url: 'www.google.com',
        header: {"token": "kaismck29am2"},
        body: {"teste": "teste"},
      );

      expect(result, isA<HttpResponse>());
      expect(result.statusCode, 400);
      expect(result.data, fixture('error_response.json'));
      verify(() => restClient.post(
          url: any(named: 'url'),
          header: any(named: 'header'),
          body: any(named: 'body'))).called(1);
    },
  );
}
