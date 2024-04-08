import 'package:dog_match_mobile/src/core/constants/image_constants.dart';
import 'package:dog_match_mobile/src/core/styles/fonts.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const DogMatch());
}

class DogMatch extends StatelessWidget {
  const DogMatch({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'DogMatch',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.deepPurple,
        ),
        useMaterial3: true,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: CustomText.h3(
            text: "Texty",
            italic: false,
          ),
        ),
        body: Image.asset(bannerPrincipal),
      ),
    );
  }
}
