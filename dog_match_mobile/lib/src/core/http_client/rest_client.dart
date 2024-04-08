abstract class RestClient {
  Future<HttpResponse> get(String url, Map<String, String> header);
  Future<HttpResponse> post(String url, Map<String, String> header, body);
  Future<HttpResponse> put(String url, Map<String, String> header, body);
}

class HttpResponse {
  final dynamic data;
  final int statusCode;
  HttpResponse({required this.data, required this.statusCode});
}
