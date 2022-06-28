package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorResourceTest21() {
    // TODO 21 запуск анимаций из xml ресурсов с помощью animatedVectorResource
    val animationImage = AnimatedImageVector.animatedVectorResource(R.drawable.animated_smile)
    var startAnimation by remember { mutableStateOf(false) }
    Image(
        painter = rememberAnimatedVectorPainter(
            animatedImageVector = animationImage,
            atEnd = startAnimation
        ),
        contentDescription = null,
        modifier = Modifier
            .size(400.dp, 400.dp)
            .clickable { startAnimation = startAnimation.not() }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy
                )
            ),
    )
}