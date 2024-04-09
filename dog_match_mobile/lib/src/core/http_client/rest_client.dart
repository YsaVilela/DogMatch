import 'package:equatable/equatable.dart';

abstract class RestClient {
  Future<HttpResponse> get({
    required String url,
    Map<String, String>? header,
  });
  Future<HttpResponse> post({
    required String url,
    Map<String, String>? header,
    required Map<String, dynamic> body,
  });
  Future<HttpResponse> put({
    required String url,
    Map<String, String>? header,
    required Map<String, dynamic> body,
  });
}

class HttpResponse extends Equatable {
  final dynamic data;
  final int statusCode;

  const HttpResponse({required this.data, required this.statusCode});

  @override
  List<Object?> get props => [data, statusCode];
}
