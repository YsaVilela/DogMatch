import 'package:dartz/dartz.dart';
import 'package:dog_match_mobile/src/core/error/failure.dart';

typedef FutureEitherExceptionString = Future<Either<Exception, String>>;
typedef FutureEitherFailureString = Future<Either<Failure, String>>;
