import 'package:flutter/material.dart';

class CustomText extends StatelessWidget {
  final String text;
  final TextStyle style;
  const CustomText({
    Key? key,
    required this.text,
    required this.style,
  }) : super(key: key);

  factory CustomText._base(
    String text,
    TextStyle base,
  ) {
    return CustomText(
      text: text,
      style: base,
    );
  }

  FontStyle getFontStyle(bool italic) {
    return italic ? FontStyle.italic : FontStyle.normal;
  }

  factory CustomText.h1({
    required String text,
    required bool italic,
    Color? color,
    FontWeight? fontWeight,
    TextAlign? textAlign,
  }) {
    TextStyle base = TextStyle(
      fontFamily: "Amaranth",
      fontWeight: fontWeight,
      fontSize: 36,
      fontStyle: italic ? FontStyle.italic : FontStyle.normal,
      color: color,
    );
    return CustomText._base(
      text,
      base,
    );
  }
  factory CustomText.h2({
    required String text,
    required bool italic,
    Color? color,
    FontWeight? fontWeight,
    TextAlign? textAlign,
  }) {
    TextStyle base = TextStyle(
      fontFamily: "Amaranth",
      fontWeight: fontWeight,
      fontSize: 24,
      fontStyle: italic ? FontStyle.italic : FontStyle.normal,
      color: color,
    );
    return CustomText._base(
      text,
      base,
    );
  }
  factory CustomText.h3({
    required String text,
    required bool italic,
    Color? color,
    FontWeight? fontWeight,
    TextAlign? textAlign,
  }) {
    TextStyle base = TextStyle(
      fontFamily: "Amaranth",
      fontWeight: fontWeight,
      fontSize: 18,
      fontStyle: italic ? FontStyle.italic : FontStyle.normal,
      color: color,
    );
    return CustomText._base(
      text,
      base,
    );
  }
  factory CustomText.h4({
    required String text,
    required bool italic,
    Color? color,
    FontWeight? fontWeight,
    TextAlign? textAlign,
  }) {
    TextStyle base = TextStyle(
      fontFamily: "Amaranth",
      fontWeight: fontWeight,
      fontSize: 16,
      fontStyle: italic ? FontStyle.italic : FontStyle.normal,
      color: color,
    );
    return CustomText._base(
      text,
      base,
    );
  }
  factory CustomText.h5({
    required String text,
    required bool italic,
    Color? color,
    FontWeight? fontWeight,
    TextAlign? textAlign,
  }) {
    TextStyle base = TextStyle(
      fontFamily: "Amaranth",
      fontWeight: fontWeight,
      fontSize: 14,
      fontStyle: italic ? FontStyle.italic : FontStyle.normal,
      color: color,
    );
    return CustomText._base(
      text,
      base,
    );
  }
  factory CustomText.h6({
    required String text,
    required bool italic,
    Color? color,
    FontWeight? fontWeight,
    TextAlign? textAlign,
  }) {
    TextStyle base = TextStyle(
      fontFamily: "Amaranth",
      fontWeight: fontWeight,
      fontSize: 12,
      fontStyle: italic ? FontStyle.italic : FontStyle.normal,
      color: color,
    );
    return CustomText._base(
      text,
      base,
    );
  }
  factory CustomText.h7({
    required String text,
    required bool italic,
    Color? color,
    FontWeight? fontWeight,
    TextAlign? textAlign,
  }) {
    TextStyle base = TextStyle(
      fontFamily: "Amaranth",
      fontWeight: fontWeight,
      fontSize: 10,
      fontStyle: italic ? FontStyle.italic : FontStyle.normal,
      color: color,
    );
    return CustomText._base(
      text,
      base,
    );
  }
  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: style,
    );
  }
}
