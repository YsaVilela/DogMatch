import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:equatable/equatable.dart';
import 'package:http/http.dart' as http;

class HttpClientImp extends Equatable implements RestClient {
  @override
  Future<HttpResponse> post({
    required String url,
    Map<String, String>? header,
    required Map<String, dynamic> body,
  }) async {
    final response =
        await http.post(Uri.parse(url), headers: header, body: body);
    return HttpResponse(data: response.body, statusCode: response.statusCode);
  }

  @override
  Future<HttpResponse> get({
    required String url,
    Map<String, String>? header,
  }) async {
    final response = await http.get(Uri.parse(url), headers: header);
    return HttpResponse(data: response.body, statusCode: response.statusCode);
  }

  @override
  Future<HttpResponse> put({
    required String url,
    Map<String, String>? header,
    required Map<String, dynamic> body,
  }) async {
    final response =
        await http.put(Uri.parse(url), headers: header, body: body);
    return HttpResponse(data: response.body, statusCode: response.statusCode);
  }

  @override
  List<Object?> get props => [];
}
