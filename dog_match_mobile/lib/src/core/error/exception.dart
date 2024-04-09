import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:equatable/equatable.dart';

class ServerHttpStatusException extends Equatable implements Exception {
  final HttpResponse response;

  const ServerHttpStatusException({required this.response});

  @override
  List<Object?> get props => [response];
}

class UserHttpStatusException extends Equatable implements Exception {
  final HttpResponse response;

  const UserHttpStatusException({required this.response});

  @override
  List<Object?> get props => [response];
}

Exception throwExceptionByResponse(HttpResponse response) {
  if (response.statusCode >= 400 && response.statusCode < 500) {
    throw UserHttpStatusException(response: response);
  } else {
    throw ServerHttpStatusException(response: response);
  }
}
