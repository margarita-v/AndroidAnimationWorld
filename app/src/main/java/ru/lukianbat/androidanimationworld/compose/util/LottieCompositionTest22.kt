package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ru.lukianbat.androidanimationworld.R

@Composable
fun LottieCompositionTest22() {
    // TODO 22 запуск Lottie анимаций
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.dog_anim))
    LottieAnimation(
        composition = composition,
        isPlaying = true,
        iterations = 20,
        modifier = Modifier
            .size(500.dp)
            .clickable {}
    )
}