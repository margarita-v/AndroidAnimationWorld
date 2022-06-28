package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.FloatExponentialDecaySpec
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
fun DecayAnimationTest8() {
    // TODO 8 Анимация перемещения броском с помощью DecayAnimation
    var startAnimation by remember { mutableStateOf(false) }

    var offset by remember { mutableStateOf(0F) }

    val anim = remember {
        DecayAnimation(
            initialValue = 0F,
            animationSpec = FloatExponentialDecaySpec(),
            initialVelocity = 2350F
        )
    }
    var playTime by remember { mutableStateOf(0L) }

    LaunchedEffect(startAnimation) {
        val startTime = withFrameNanos { it }
        do {
            playTime = withFrameNanos { it } - startTime
            offset = anim.getValueFromNanos(playTime)
        } while (!anim.isFinishedFromNanos(playTime))
    }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "secondIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = offset.dp)
    )

    Button(
        onClick = {
            startAnimation = startAnimation.not()
        },
        Modifier.offset(y = 600.dp)
    ) {
        Text("Change startAnimation state")
    }
}