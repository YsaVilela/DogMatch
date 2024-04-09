import 'package:equatable/equatable.dart';

class ErrorResponse extends Equatable {
  final String errorMessage;

  const ErrorResponse({required this.errorMessage});

  factory ErrorResponse.fromMap(Map<String, dynamic> map) {
    return ErrorResponse(
      errorMessage: map['mensagem'],
    );
  }
  @override
  List<Object?> get props => [errorMessage];
}
