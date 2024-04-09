import 'dart:convert';

import 'package:dartz/dartz.dart';
import 'package:dog_match_mobile/src/core/constants/default_typedef.dart';
import 'package:dog_match_mobile/src/core/error/exception.dart';
import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';

abstract class LoginRemoteDatasource {
  FutureEitherExceptionString cadastrarUsuario(Map<String, dynamic> map);
}

class LoginRemoteDatasourceImpl extends LoginRemoteDatasource {
  late RestClient _restClient;

  LoginRemoteDatasourceImpl(RestClient restClient) {
    _restClient = restClient;
  }

  @override
  FutureEitherExceptionString cadastrarUsuario(Map<String, dynamic> map) async {
    final response = await _restClient.post(url: '', body: map);

    if (response.statusCode >= 200 && response.statusCode < 300) {
      return Right(jsonDecode(response.data['message']));
    }

    return Left(throwExceptionByResponse(response));
  }
}
