import 'package:dog_match_mobile/src/modules/login/imports/login_imports.dart';

class LoginRepositoryImpl extends LoginRepository {
  late LoginRemoteDatasource _loginRemoteDatasource;

  LoginRepositoryImpl(LoginRemoteDatasource loginRemoteDatasource) {
    _loginRemoteDatasource = loginRemoteDatasource;
  }

  @override
  FutureEitherFailureString cadastrarUsuario(tutorEntity) async {
    try {
      final TutorModel tutorModel = TutorModel.fromEntity(tutorEntity);

      final result = await _loginRemoteDatasource.cadastrarUsuario(
        tutorModel.toMap(),
      );

      return result.fold(
        (exception) => throw exception,
        (success) => Right(success),
      );
    } on UserHttpStatusException catch (e) {
      var errorResponse = ErrorResponse.fromMap(jsonDecode(e.response.data));
      return Left(GenericFailure(errorMessage: errorResponse.errorMessage));
    } on ServerHttpStatusException catch (e) {
      var errorResponse = ErrorResponse.fromMap(jsonDecode(e.response.data));
      return Left(GenericFailure(errorMessage: errorResponse.errorMessage));
    } on SocketException catch (_) {
      return Left(
        GenericFailure(errorMessage: "Sem conexão com a internet no momento."),
      );
    } on TimeoutException catch (e) {
      return Left(
        GenericFailure(
          errorMessage: e.message!,
        ),
      );
    }
  }
}
