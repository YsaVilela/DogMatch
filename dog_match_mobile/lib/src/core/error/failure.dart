import 'package:equatable/equatable.dart';

abstract class Failure extends Equatable {}

class GenericFailure extends Failure {
  final String errorMessage;

  GenericFailure({required this.errorMessage});

  @override
  List<Object?> get props => [errorMessage];
}
