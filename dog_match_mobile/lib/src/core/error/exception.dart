import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';
import 'package:equatable/equatable.dart';

abstract class GenericException extends Equatable implements Exception {}

class ServerHttpStatusException extends Equatable implements GenericException {
  final HttpResponse response;

  const ServerHttpStatusException({required this.response});

  @override
  List<Object?> get props => [response];
}

class UserHttpStatusException extends Equatable implements GenericException {
  final HttpResponse response;

  const UserHttpStatusException({required this.response});

  @override
  List<Object?> get props => [response];
}

GenericException throwExceptionByResponse(HttpResponse response) {
  if (response.statusCode >= 400 && response.statusCode < 500) {
    return UserHttpStatusException(response: response);
  } else {
    return ServerHttpStatusException(response: response);
  }
}
