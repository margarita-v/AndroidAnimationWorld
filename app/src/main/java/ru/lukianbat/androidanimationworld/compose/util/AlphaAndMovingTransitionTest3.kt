package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@Composable
fun AlphaAndMovingTransitionTest3() {
    // TODO 3 Запуск анимаций alpha и перемещения c помощью объекта Transition

    var startAnimation by remember { mutableStateOf(false) }
    val transition = updateTransition(startAnimation, label = "")

    val yOffset by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = "yOffsetAnimation"
    ) { if (it) 300F else 0F }

    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = "alphaAnimation"
    ) { if (it) 1F else 0F }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "firstIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffset.dp)
            .alpha(alpha)
    )

    Button(
        onClick = {
            startAnimation = startAnimation.not()
        },
        Modifier.offset(y = 300.dp)
    ) {
        Text("Change startAnimation state")
    }
}