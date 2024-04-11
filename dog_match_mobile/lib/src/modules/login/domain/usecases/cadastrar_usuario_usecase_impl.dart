import 'package:dartz/dartz.dart';
import 'package:dog_match_mobile/src/core/error/failure.dart';
import 'package:dog_match_mobile/src/core/usecase/usecase.dart';
import 'package:dog_match_mobile/src/modules/login/data/repositories/login_repository.dart';
import 'package:dog_match_mobile/src/modules/login/domain/entities/tutor_entity.dart';

class CadastrarUsuarioUsecaseImpl implements Usecase<String, TutorEntity> {
  final LoginRepository repository;

  CadastrarUsuarioUsecaseImpl(this.repository);

  @override
  Future<Either<Failure, String>> call(TutorEntity params) async {
    return await repository.cadastrarUsuario(params);
  }
}
