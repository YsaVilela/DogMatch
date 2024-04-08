import 'package:dog_match_mobile/src/core/http_client/http_client_impl.dart';
import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

class HttpClientImplMock extends Mock implements HttpClientImp {}

void main() {
  late RestClient restClient;
  setUp(() {
    restClient = HttpClientImplMock();
  });

  test(
    "validar retorno http client método post",
    () async {
      when(
        () => restClient.post(any(), any(), any()),
      ).thenAnswer(
        (_) async => HttpResponse(data: '', statusCode: 200),
      );

      final result = await restClient.post(
        'www.google.com',
        {"token": "kaismck29am2"},
        {"teste": "teste"},
      );

      expect(result, isA<HttpResponse>());
      verify(() => restClient.post(any(), any(), any())).called(1);
    },
  );
  test(
    "validar retorno http client método post",
    () async {
      when(
        () => restClient.post(any(), any(), any()),
      ).thenAnswer(
        (_) async => HttpResponse(data: '', statusCode: 400),
      );

      final result = await restClient.post(
        'www.google.com',
        {"token": "kaismck29am2"},
        {"teste": "teste"},
      );

      expect(result, isA<HttpResponse>());
      expect(result.statusCode, 400);
      verify(() => restClient.post(any(), any(), any())).called(1);
    },
  );
}
