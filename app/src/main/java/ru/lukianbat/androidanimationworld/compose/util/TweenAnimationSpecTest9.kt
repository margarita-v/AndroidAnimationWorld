package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@Composable
fun TweenAnimationSpecTest9() {
    // TODO 9 Анимация перемещения с прямолинейной скоростью с помощью TweenAnimationSpec и LinearEasing

    var startAnimation by remember { mutableStateOf(false) }

    val yOffsetAnimation: Float by animateFloatAsState(
        targetValue = if (startAnimation) 500f else 0f,
        animationSpec = tween(
            delayMillis = 300,
            durationMillis = 3000,
            easing = LinearEasing
        )
    )

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffsetAnimation.dp)
    )

    Button(onClick = {
        startAnimation = startAnimation.not()
    }, modifier = Modifier.offset(y = 600.dp)) {
        Text("Change startAnimation state")
    }
}