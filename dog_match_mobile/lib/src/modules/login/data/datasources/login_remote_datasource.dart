import 'package:dog_match_mobile/src/core/constants/default_typedef.dart';
import 'package:dog_match_mobile/src/core/http_client/rest_client.dart';

abstract class LoginRemoteDatasource {
  EitherHttpResponse cadastrarUsuario(Map<String, dynamic> map);
}

class LoginRemoteDatasourceImpl extends LoginRemoteDatasource {
  late RestClient _restClient;

  LoginRemoteDatasourceImpl(RestClient restClient) {
    _restClient = restClient;
  }

  @override
  EitherHttpResponse cadastrarUsuario(Map<String, dynamic> map) {
    // TODO: implement cadastrarUsuario
    throw UnimplementedError();
  }
}
