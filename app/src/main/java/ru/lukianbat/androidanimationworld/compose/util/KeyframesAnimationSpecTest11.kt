package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
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
fun KeyframesAnimationSpecTest11() {
    // TODO 11 Анимация перемещения с покадровой интерполяцией
    var startAnimation by remember { mutableStateOf(false) }

    val yOffsetAnimatable: Float by animateFloatAsState(
        targetValue = if (startAnimation) 500f else 0f,
        animationSpec = keyframes {
            durationMillis = 3000
            0f at 0 with LinearOutSlowInEasing
            0.5f at 1500 with FastOutSlowInEasing
        }
    )

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffsetAnimatable.dp)
    )

    Button(onClick = {
        startAnimation = startAnimation.not()
    }, modifier = Modifier.offset(y = 600.dp)) {
        Text("Change startAnimation state")
    }
}