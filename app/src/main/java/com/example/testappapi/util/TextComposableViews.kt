package com.example.testappapi.util

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign,
    color: Color,
    fontWeight: FontWeight,
    fontSize: TextUnit
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}

@Composable
fun SubTitleText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign,
    color: Color,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    fontFamily: FontFamily = FontFamily.SansSerif,
    lineHeight: TextUnit
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = fontSize,
        fontFamily = fontFamily,
        lineHeight = lineHeight
    )
}
