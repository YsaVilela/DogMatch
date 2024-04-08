import 'package:equatable/equatable.dart';

abstract class DefaultState extends Equatable {}

class LoadingState extends DefaultState {
  @override
  List<Object?> get props => [];
}

class InitialState extends DefaultState {
  @override
  List<Object?> get props => [];
}
