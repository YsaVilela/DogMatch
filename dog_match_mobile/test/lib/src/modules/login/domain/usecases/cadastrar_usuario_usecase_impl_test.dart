import 'package:dog_match_mobile/src/core/usecase/usecase.dart';
import 'package:dog_match_mobile/src/modules/login/domain/usecases/cadastrar_usuario_usecase_impl.dart';
import 'package:dog_match_mobile/src/modules/login/imports/login_imports.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

import '../../../../core/mock/token_valido_mock.dart';
import '../../mock/tutor_entity_mock.dart';

class LoginRepositoryMock extends Mock implements LoginRepository {}

void main() {
  late Usecase usecase;
  late LoginRepository loginRepository;
  setUp(() {
    loginRepository = LoginRepositoryMock();
    usecase = CadastrarUsuarioUsecaseImpl(loginRepository);
    registerFallbackValue(tutorEntityMock);
  });

  test(
      'Deve retornar o uma mensagem com o token ao cadastrar um usuario com sucesso',
      () async {
    when(
      () => loginRepository.cadastrarUsuario(any()),
    ).thenAnswer((_) async => const Right<Failure, String>(tokenValidoMock));

    final result = await usecase(tutorEntityMock);

    expect(result, const Right(tokenValidoMock));

    verify(() => loginRepository.cadastrarUsuario(any())).called(1);
  });

  test('Deve retornar o uma GenericFailure quando ocorrer um erro', () async {
    when(
      () => loginRepository.cadastrarUsuario(any()),
    ).thenAnswer(
        (_) async => Left<Failure, String>(GenericFailure(errorMessage: '')));

    final result = await usecase(tutorEntityMock);

    expect(result, Left(GenericFailure(errorMessage: '')));

    verify(() => loginRepository.cadastrarUsuario(any())).called(1);
  });
}
