import 'package:dartz/dartz.dart';
import 'package:dog_match_mobile/src/core/error/failure.dart';

abstract class Usecase<Type, Params> {
  Future<Either<Failure, Type>> call(Params params);
}
