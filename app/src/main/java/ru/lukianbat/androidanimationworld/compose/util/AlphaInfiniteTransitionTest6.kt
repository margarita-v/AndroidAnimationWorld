package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@Composable
fun AlphaInfiniteTransitionTest6() {
    // TODO 6 Бесконечная анимация альфы с помощью rememberInfiniteTransition

    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 0F,
        targetValue = 1F,
        animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1500),
            repeatMode = RepeatMode.Reverse
        ),
    )

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "secondIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .alpha(alpha)
    )
}