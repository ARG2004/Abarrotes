package com.example.abarrotes.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
data class Dimens (
    val borderNormal: Dp=4.dp,
    val buttonHeightNormal: Dp=56.dp,
    val iconSizeSmall: Dp=24.dp,
    val iconSizeNormal: Dp=36.dp,
    val paddingSmall:Dp=4.dp,
    val paddingNormal:Dp=8.dp,
    val paddingMedium:Dp=16.dp,
    val roundedShapeNormal:Dp=8.dp,
    val spacerSmall:Dp=4.dp,
    val spacerNormal:Dp=8.dp,
    val spacerMedium:Dp=16.dp,
    val spacerLarge:Dp=32.dp,

    )
val DefaultDimens = Dimens()
val TabletDimens = Dimens(
    buttonHeightNormal = 64.dp, // Paso directo del valor
    iconSizeSmall = 32.dp,       // Paso directo del valor
    iconSizeNormal = 48.dp,      // Paso directo del valor
    paddingSmall = 8.dp,       // Paso directo del valor
    paddingNormal = 16.dp,      // Paso directo del valor
    paddingMedium = 24.dp,     // Paso directo del valor
    roundedShapeNormal = 12.dp,  // Paso directo del valor
    spacerSmall = 8.dp,        // Paso directo del valor
    spacerNormal = 16.dp,       // Paso directo del valor
    spacerMedium = 24.dp,      // Paso directo del valor
    spacerLarge = 48.dp,       // Paso directo del valor
    borderNormal = 6.dp //Paso directo del valor.
)