 
import 'package:dog_match_mobile/src/core/constants/default_typedef.dart';
 
import 'package:dog_match_mobile/src/modules/login/domain/entities/tutor_entity.dart';

abstract class LoginRepository {
  FutureEitherFailureString cadastrarUsuario(TutorEntity tutorEntity);
}
